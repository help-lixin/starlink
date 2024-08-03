package help.lixin.starlink.plugin.harbor.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.enums.IsDelEnum;
import help.lixin.enums.StatusEnum;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.harbor.api.dto.CreateProject;
import help.lixin.starlink.plugin.harbor.api.dto.Metadata;
import help.lixin.starlink.plugin.harbor.api.dto.Project;
import help.lixin.starlink.plugin.harbor.api.dto.repository.ArtifactDTO;
import help.lixin.starlink.plugin.harbor.api.dto.repository.RepositoriesDTO;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborProjectApi;
import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.dto.*;
import help.lixin.starlink.plugin.harbor.mapper.HarborProjectMapper;
import help.lixin.starlink.plugin.harbor.service.IHarborProjectService;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/24 6:14 下午
 * @Description
 */
public class HarborHarborProjectService implements IHarborProjectService {

    private HarborProjectMapper projectMapper;

    private QueryTemplate queryTemplate;

    private final AbstractServiceFactory harborServiceFactory;

    public HarborHarborProjectService(AbstractServiceFactory harborServiceFactory, HarborProjectMapper projectMapper,
        QueryTemplate queryTemplate) {
        this.harborServiceFactory = harborServiceFactory;
        this.projectMapper = projectMapper;
        this.queryTemplate = queryTemplate;
    }

    @Override
    public int createProject(CreateProjectDTO createProjectDTO) {
        int res = -1;
        try {
            String projectName = createProjectDTO.getProjectName();
            QueryWrapper<HarborProject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_name", projectName).eq("is_del", IsDelEnum.NORMAL.getValue());
            HarborProject harborProjectInfo = projectMapper.selectOne(queryWrapper);
            if (harborProjectInfo != null) {
                throw new ServiceException("已存在相同项目名，请重命名后再提交");
            }

            HarborProjectApi harborProjectApi =
                harborServiceFactory.getInstance(createProjectDTO.getInstanceCode(), HarborProjectApi.class);
            CreateProject createProject = new CreateProject();
            createProject.setProject_name(projectName);
            createProject.setStorage_limit(Long.parseLong(createProjectDTO.getCapacity()));
            createProject.setRegistry_id(createProjectDTO.getRegistryId());
            Metadata metadata = new Metadata();
            metadata.setIsPublic(createProjectDTO.getIsPublic() == 1 ? "true" : "false");
            createProject.setMetadata(metadata);
            harborProjectApi.create(createProject);

            List<Project> query = harborProjectApi.query(1, 10, projectName, null);
            Project project = query.get(0);
            if (!projectName.equals(project.getName())) {
                throw new ServiceException("创建项目发生异常,无法根据项目名称获取数据");
            }
            // todo 需要讨论一下容量上限的问题

            HarborProject harborProject = new HarborProject();
            harborProject.setInstanceCode(createProjectDTO.getInstanceCode());
            harborProject.setCapacity(createProjectDTO.getCapacity());
            harborProject.setProjectName(projectName);
            harborProject.setIsPublic(createProjectDTO.getIsPublic());
            harborProject.setHarborProjectId(project.getProject_id());
            harborProject.setCreateBy(createProjectDTO.getCreateBy());
            harborProject.setUpdateBy(createProjectDTO.getCreateBy());
            res = projectMapper.insertSelective(harborProject);
        } catch (NumberFormatException e) {
            throw new ServiceException("容量数字无法正常转换");
        } catch (Exception e) {
            throw new ServiceException("创建项目发生异常");
        }
        return res;
    }

    @Override
    public List<HarborProject> queryProject(String projectName, String instanceCode) {
        return projectMapper.queryByProjectName(projectName, instanceCode);
    }

    @Override
    public PageResponse<HarborProject> pageList(PageListDTO pageListDTO) {
        HarborProjectApi harborProjectApi =
            harborServiceFactory.getInstance(pageListDTO.getInstanceCode(), HarborProjectApi.class);
        PageResponse execute = queryTemplate.execute(pageListDTO, () -> {
            projectMapper.pageList(pageListDTO);
        });
        execute.setTotal(harborProjectApi.totalPage().getTotal_project_count());
        return execute;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Boolean removeProject(Long projectId) {
        HarborProject harborProject = projectMapper.selectByPrimaryKey(projectId);
        if (harborProject == null) {
            throw new ServiceException("该项目不存在");
        }
        HarborProjectApi harborProjectApi =
            harborServiceFactory.getInstance(harborProject.getInstanceCode(), HarborProjectApi.class);
        projectMapper.removeById(projectId);
        harborProjectApi.delete(harborProject.getHarborProjectId());
        return true;
    }

    @Override
    public Boolean changeStatus(Long id, Integer status, String updateBy) {
        HarborProject harborProject = projectMapper.selectByPrimaryKey(id);
        if (harborProject == null) {
            throw new ServiceException("该项目不存在");
        }
        harborProject.setStatus(status);
        harborProject.setUpdateTime(new Date());
        harborProject.setUpdateBy(updateBy);
        boolean changeRes = projectMapper.updateById(harborProject) > 0;
        return changeRes;
    }

    @Override
    public List<RepositoryDTO> queryRepository(String projectName, String instanceCode) {
        HarborProjectApi harborProjectApi = harborServiceFactory.getInstance(instanceCode, HarborProjectApi.class);
        List<RepositoriesDTO> repositoriesDTOS = harborProjectApi.queryRepositories(projectName);
        List<RepositoryDTO> repositoryDTOList = new ArrayList<>();
        repositoriesDTOS.forEach(i -> {
            RepositoryDTO repositoryDTO = new RepositoryDTO();
            repositoryDTO.setId(i.getId());
            repositoryDTO.setName(i.getName());
            repositoryDTO.setProjectId(i.getProjectId());
            repositoryDTO.setCreateTime(i.getCreateTime());
            repositoryDTO.setUpdateTime(i.getUpdateTime());
            repositoryDTOList.add(repositoryDTO);
        });
        return repositoryDTOList;
    }

    @Override
    public Boolean projectNameIsExist(String projectName, String instanceCode) {
        HarborProject harborProject = projectMapper.checkProjectName(projectName, instanceCode);
        if (harborProject == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<IMageDTO> queryImages(String projectName, String repositoryName, String instanceCode) {
        HarborProjectApi harborProjectApi = harborServiceFactory.getInstance(instanceCode, HarborProjectApi.class);
        // 返回结果集
        List<IMageDTO> iMageDTOList = new ArrayList<>();
        // 查询镜像列表
        List<ArtifactDTO> artifactDTOS = harborProjectApi.queryImages(projectName, repositoryName);
        artifactDTOS.forEach(i -> {
            IMageDTO iMageDTO = new IMageDTO();
            iMageDTO.setId(i.getId());
            iMageDTO.setDigest(i.getDigest());
            Integer size = i.getSize();
            if (size != null && size > 0) {
                BigDecimal mbSize = new BigDecimal(size).divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_UP);
                iMageDTO.setSize(mbSize + "MB");
            }
            if (i.getTags().size() > 0) {
                iMageDTO.setTag(i.getTags().get(0).getName());
            }
            iMageDTO.setPullTime(i.getPullTime());
            iMageDTO.setPushTime(i.getPushTime());

            iMageDTOList.add(iMageDTO);
        });
        return iMageDTOList;
    }

    @Override
    public List<RepositoryNodeDTO> queryNodeList(String instanceCode, String projectName) {
        List<RepositoryNodeDTO> repositoryNodeDTOList = new ArrayList<>();

        // 查询项目仓库目录列表
        List<RepositoryDTO> repositoryDTOS = queryRepository(projectName, instanceCode);
        repositoryDTOS.forEach(i -> {
            // 把目录根据"/"切成两个元素，如：library/maven
            String[] names = i.getName().split("/");
            RepositoryNodeDTO repositoryNodeDTO = new RepositoryNodeDTO();
            repositoryNodeDTO.setId(i.getId().toString());
            repositoryNodeDTO.setName(i.getName());
            repositoryNodeDTO.setCreateTime(i.getCreateTime());
            if (names.length == 2) {
                // 查询镜像列表
                List<IMageDTO> iMageDTOList = queryImages(projectName, names[1], instanceCode);
                iMageDTOList.forEach(v -> {
                    RepositoryNodeDTO children = new RepositoryNodeDTO();
                    children.setParentId(i.getId());
                    children.setId(v.getId().toString());
                    children.setDigest(v.getDigest());
                    children.setName(v.getTag());
                    children.setTag(v.getTag());
                    children.setSize(v.getSize());
                    children.setPushTime(v.getPushTime());
                    children.setPullTime(v.getPullTime());
                    repositoryNodeDTOList.add(children);
                });
            }
            repositoryNodeDTOList.add(repositoryNodeDTO);
        });

        return repositoryNodeDTOList;
    }

    @Override
    public Boolean changeAccessLevel(Long projectId) {
        HarborProject harborProject = projectMapper.selectById(projectId);
        if (harborProject == null) {
            throw new ServiceException("该项目不存在");
        }
        harborProject.setIsPublic(harborProject.getIsPublic() == 1 ? 0 : 1);
        projectMapper.updateById(harborProject);

        HarborProjectApi harborProjectApi =
            harborServiceFactory.getInstance(harborProject.getInstanceCode(), HarborProjectApi.class);
        return harborProjectApi.changeAccessLevel(harborProject.getHarborProjectId());
    }

    @Override
    public List<HarborProjectOption> queryOptions(String instanceCode) {
        List<HarborProjectOption> results = new ArrayList<>(0);
        QueryWrapper<HarborProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("instance_code", instanceCode).eq("status", StatusEnum.NORMAL.getValue()).eq("is_del",
            IsDelEnum.NORMAL.getValue());
        List<HarborProject> harborProjects = projectMapper.selectList(queryWrapper);
        if (!harborProjects.isEmpty()) {
            results = harborProjects.stream().map(project -> {
                HarborProjectOption option = new HarborProjectOption();
                option.setLabel(project.getProjectName());
                option.setValue(String.valueOf(project.getId()));
                return option;
            }).collect(Collectors.toList());
        }
        return results;
    }

    @Override
    public HarborProject getHarborProject(Long id) {
        return projectMapper.selectByPrimaryKey(id);
    }
}
