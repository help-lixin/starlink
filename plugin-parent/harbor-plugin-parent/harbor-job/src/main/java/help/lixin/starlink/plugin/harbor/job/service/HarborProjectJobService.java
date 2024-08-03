package help.lixin.starlink.plugin.harbor.job.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.api.dto.Project;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborProjectApi;
import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.dto.TotalPageDTO;
import help.lixin.starlink.plugin.harbor.mapper.HarborProjectMapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/2 10:53 上午
 * @Description
 */
public class HarborProjectJobService {

    private HarborProjectMapper projectMapper;

    private final AbstractServiceFactory harborServiceFactory;

    public HarborProjectJobService(AbstractServiceFactory harborServiceFactory, HarborProjectMapper projectMapper) {
        this.harborServiceFactory = harborServiceFactory;
        this.projectMapper = projectMapper;
    }

    public void checkHarborProjectList() {
        // 获取插件列表
        Set<String> contextNames = harborServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            HarborProjectApi projectApi = harborServiceFactory.getInstance(instanceCode, HarborProjectApi.class);
            try {
                // 获取harbor内部project的列表总数据数量
                help.lixin.starlink.plugin.harbor.api.dto.TotalPageDTO resultTotalPage = projectApi.totalPage();
                TotalPageDTO totalPageDTO = new TotalPageDTO();
                totalPageDTO.setPrivate_project_count(resultTotalPage.getPrivate_project_count());
                totalPageDTO.setPrivate_repo_count(totalPageDTO.getPrivate_repo_count());
                totalPageDTO.setTotal_project_count(totalPageDTO.getTotal_project_count());
                totalPageDTO.setTotal_repo_count(totalPageDTO.getTotal_repo_count());
                totalPageDTO.setPublic_project_count(totalPageDTO.getPublic_project_count());
                totalPageDTO.setPublic_repo_count(totalPageDTO.getPublic_repo_count());

                // 获取project所有数据
                List<Project> projects = projectApi.query(1, totalPageDTO.getTotal_project_count(), null, null);
                // 转换成MAP<KEY:harborId,VALUE:project>集合，用户快速获取id对应的project数据
                Map<Long, Project> harborProjectIdAndProjectMap =
                    projects.stream().collect(Collectors.toMap(Project::getProject_id, v -> v));

                // 将harbor的project列表信息转换成id的集合
                List<Long> harborProjectIds =
                    projects.stream().map(Project::getProject_id).collect(Collectors.toList());
                // 根据上面的id列表在数据库中用 IN 查询
                List<HarborProject> dbProjects = projectMapper.queryProjectByHarborProjectIds(harborProjectIds);

                if (harborProjectIds.size() == dbProjects.size()) {
                    return;
                }

                // 将从数据库中获取的project列表信息转换成id列表
                List<Long> dbProjectIds =
                    dbProjects.stream().map(HarborProject::getHarborProjectId).collect(Collectors.toList());
                // 过滤出数据库中不存在的projectId
                List<Long> collect =
                    harborProjectIds.stream().filter(v -> !dbProjectIds.contains(v)).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(collect)) {
                    return;
                }

                // 将数据库中不存在的project数据插入
                for (Long harborProjectId : collect) {
                    // 根据上面harbor转换的projectMap获取project数据
                    Project project = harborProjectIdAndProjectMap.get(harborProjectId);

                    HarborProject harborProject = new HarborProject();
                    // 根据当前插件名称获取环境信息
                    // EnvDTO env = PluginUtils.envParse(instanceCode);
                    // if (env == null) {
                    // throw new ServiceException("插件名称格式异常");
                    // }

                    harborProject.setInstanceCode(instanceCode);
                    harborProject.setProjectName(project.getName());
                    harborProject.setHarborProjectId(project.getProject_id());
                    harborProject.setIsPublic(project.getMetadata().getIsPublic().equals("true") ? 1 : 0);
                    // harborProject.setCapacity();
                    // todo 这里有可能频繁连接数据库，探讨一下把所有sql语句串起来执行
                    projectMapper.insert(harborProject);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("定时任务发生异常:" + e.getMessage());
            }

        });

    }

}
