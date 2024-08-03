package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.domain.*;
import help.lixin.starlink.plugin.jenkins.dto.setup.*;
import help.lixin.starlink.plugin.jenkins.convert.*;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupCommonService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/15 8:37 下午
 * @Description
 */
public class JenkinsPipelineSetupCommonService implements IJenkinsPipelineSetupCommonService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupMapper jenkinsPipelineSetupMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer save(JenkinsPipelineSetupCommonDTO jenkinsPipelineSetupCommonDTO) {
        Long id = jenkinsPipelineSetupCommonDTO.getId();
        jenkinsPipelineSetupCommonDTO.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());

        if (id == null){
            CommonServiceConvert mapper = Mappers.getMapper(CommonServiceConvert.class);
            JenkinsPipelineSetupCommon jenkinsPipelineSetupCommon = mapper.convert(jenkinsPipelineSetupCommonDTO);
            jenkinsPipelineSetupCommon.setCreateTime(new Date());
            jenkinsPipelineSetupCommon.setUpdateTime(new Date());
            jenkinsPipelineSetupMapper.insertSetup(jenkinsPipelineSetupCommon);

            if (jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupMavenDTO) {
                // maven
                MavenServiceConvert mavenServiceConvert = Mappers.getMapper(MavenServiceConvert.class);
                JenkinsPipelineSetupMaven maven = mavenServiceConvert.convert((JenkinsPipelineSetupMavenDTO) jenkinsPipelineSetupCommonDTO);
                maven.setId(jenkinsPipelineSetupCommon.getId());
                if (maven.getGoals().startsWith("mvn")) {
                    maven.setGoals(maven.getGoals().replaceFirst("mvn",""));
                }
                jenkinsPipelineSetupMapper.insertMaven(maven);

            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupShellDTO){
                // shell
                ShellServiceConvert shellServiceConvert = Mappers.getMapper(ShellServiceConvert.class);
                JenkinsPipelineSetupShell shell = shellServiceConvert.convert((JenkinsPipelineSetupShellDTO) jenkinsPipelineSetupCommonDTO);
                shell.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertShell(shell);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupGoDTO){
                // go
                GoServiceConvert goServiceConvert = Mappers.getMapper(GoServiceConvert.class);
                JenkinsPipelineSetupGo go = goServiceConvert.convert((JenkinsPipelineSetupGoDTO) jenkinsPipelineSetupCommonDTO);
                go.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertGo(go);

            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupGradleDTO){
                // gradle
                GradleServiceConvert gradleServiceConvert = Mappers.getMapper(GradleServiceConvert.class);
                JenkinsPipelineSetupGradle gradle = gradleServiceConvert.convert((JenkinsPipelineSetupGradleDTO) jenkinsPipelineSetupCommonDTO);
                gradle.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertGradle(gradle);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupPythonDTO){
                // python
                PythonServiceConvert pythonServiceConvert = Mappers.getMapper(PythonServiceConvert.class);
                JenkinsPipelineSetupPython python = pythonServiceConvert.convert((JenkinsPipelineSetupPythonDTO) jenkinsPipelineSetupCommonDTO);
                python.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertPython(python);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupNodejsDTO){
                // nodejs
                NodejsServiceConvert nodejsServiceConvert = Mappers.getMapper(NodejsServiceConvert.class);
                JenkinsPipelineSetupNodejs nodejs = nodejsServiceConvert.convert((JenkinsPipelineSetupNodejsDTO) jenkinsPipelineSetupCommonDTO);
                nodejs.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertNodejs(nodejs);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupAntDTO){
                // ant
                AntServiceConvert antServiceConvert = Mappers.getMapper(AntServiceConvert.class);
                JenkinsPipelineSetupAnt ant = antServiceConvert.convert((JenkinsPipelineSetupAntDTO) jenkinsPipelineSetupCommonDTO);
                ant.setId(jenkinsPipelineSetupCommon.getId());
                jenkinsPipelineSetupMapper.insertAnt(ant);
            }

        }else{
            JenkinsPipelineSetupCommon jenkinsPipelineSetupCommonDB = jenkinsPipelineSetupMapper.selectById(id);
            if (jenkinsPipelineSetupCommonDB == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupCommonDB.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
            jenkinsPipelineSetupCommonDB.setUpdateTime(new Date());
            jenkinsPipelineSetupMapper.updateById(jenkinsPipelineSetupCommonDB);

            if (jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupMavenDTO) {
                // maven
                MavenServiceConvert mavenServiceConvert = Mappers.getMapper(MavenServiceConvert.class);
                JenkinsPipelineSetupMaven maven = mavenServiceConvert.convert((JenkinsPipelineSetupMavenDTO) jenkinsPipelineSetupCommonDTO);
                maven.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateMaven(maven);

            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupShellDTO){
                // shell
                ShellServiceConvert shellServiceConvert = Mappers.getMapper(ShellServiceConvert.class);
                JenkinsPipelineSetupShell shell = shellServiceConvert.convert((JenkinsPipelineSetupShellDTO) jenkinsPipelineSetupCommonDTO);
                shell.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateShell(shell);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupShellDTO){
                // go
                GoServiceConvert goServiceConvert = Mappers.getMapper(GoServiceConvert.class);
                JenkinsPipelineSetupGo go = goServiceConvert.convert((JenkinsPipelineSetupGoDTO) jenkinsPipelineSetupCommonDTO);
                go.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateGo(go);

            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupGradleDTO){
                // gradle
                GradleServiceConvert gradleServiceConvert = Mappers.getMapper(GradleServiceConvert.class);
                JenkinsPipelineSetupGradle gradle = gradleServiceConvert.convert((JenkinsPipelineSetupGradleDTO) jenkinsPipelineSetupCommonDTO);
                gradle.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateGradle(gradle);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupPythonDTO){
                // python
                PythonServiceConvert pythonServiceConvert = Mappers.getMapper(PythonServiceConvert.class);
                JenkinsPipelineSetupPython python = pythonServiceConvert.convert((JenkinsPipelineSetupPythonDTO) jenkinsPipelineSetupCommonDTO);
                python.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updatePython(python);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupNodejsDTO){
                // nodejs
                NodejsServiceConvert nodejsServiceConvert = Mappers.getMapper(NodejsServiceConvert.class);
                JenkinsPipelineSetupNodejs nodejs = nodejsServiceConvert.convert((JenkinsPipelineSetupNodejsDTO) jenkinsPipelineSetupCommonDTO);
                nodejs.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateNodejs(nodejs);
            }else if(jenkinsPipelineSetupCommonDTO instanceof JenkinsPipelineSetupAntDTO){
                // ant
                AntServiceConvert antServiceConvert = Mappers.getMapper(AntServiceConvert.class);
                JenkinsPipelineSetupAnt ant = antServiceConvert.convert((JenkinsPipelineSetupAntDTO) jenkinsPipelineSetupCommonDTO);
                ant.setUpdateBy(jenkinsPipelineSetupCommonDTO.getCreateBy());
                jenkinsPipelineSetupMapper.updateAnt(ant);
            }
        }

        return 1;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupCommon jenkinsPipelineSetupCommon = jenkinsPipelineSetupMapper.selectById(id);
        if (jenkinsPipelineSetupCommon == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupCommon.setStatus(status);
        jenkinsPipelineSetupCommon.setUpdateBy(updateBy);
        jenkinsPipelineSetupCommon.setUpdateTime(new Date());

        return jenkinsPipelineSetupMapper.updateById(jenkinsPipelineSetupCommon);
    }

    @Override
    public JenkinsPipelineSetupCommon queryInfo(Long id) {
        Map result = jenkinsPipelineSetupMapper.querySetup(id);
        return mapToJenkinsPipelineSetupCommon(result);
    }

    protected JenkinsPipelineSetupCommon mapToJenkinsPipelineSetupCommon(Map v) {
        if (null != v && v.containsKey("setup_type")) {
            String setupType = (String) v.get("setup_type");
            if (setupType.equals("MAVEN")) {
                JenkinsPipelineSetupMaven maven = new JenkinsPipelineSetupMaven();
                maven.setId((Long) v.get("id"));
                maven.setGoals((String) v.get("script"));
                maven.setMavenId((Long) v.get("value_1"));
                maven.setJobId((Long) v.get("job_id"));
                maven.setSequence((Integer) v.get("sequence"));
                maven.setInstanceCode((String) v.get("instance_code"));
                maven.setSetupType((String) v.get("setup_type"));
                maven.setStatus((Integer) v.get("status"));
                maven.setUpdateBy((String) v.get("update_by"));
                maven.setCreateBy((String) v.get("create_by"));
                maven.setCreateTime((Date) v.get("create_time"));
                maven.setUpdateTime((Date) v.get("update_time"));
                return maven;
            } else if (setupType.equals("SHELL")) {
                JenkinsPipelineSetupShell shell = new JenkinsPipelineSetupShell();
                shell.setId((Long) v.get("id"));
                shell.setShellScript((String) v.get("script"));
                shell.setJobId((Long) v.get("job_id"));
                shell.setSequence((Integer) v.get("sequence"));
                shell.setInstanceCode((String) v.get("instance_code"));
                shell.setSetupType((String) v.get("setup_type"));
                shell.setStatus((Integer) v.get("status"));
                shell.setUpdateBy((String) v.get("update_by"));
                shell.setCreateBy((String) v.get("create_by"));
                shell.setCreateTime((Date) v.get("create_time"));
                shell.setUpdateTime((Date) v.get("update_time"));
                return shell;
            } else if (setupType.equals("ANT")) {
                JenkinsPipelineSetupAnt ant = new JenkinsPipelineSetupAnt();
                ant.setId((Long) v.get("id"));
                ant.setAntId((Long) v.get("value_1"));
                ant.setTargets((String) v.get("script"));
                ant.setJobId((Long) v.get("job_id"));
                ant.setSequence((Integer) v.get("sequence"));
                ant.setInstanceCode((String) v.get("instance_code"));
                ant.setSetupType((String) v.get("setup_type"));
                ant.setStatus((Integer) v.get("status"));
                ant.setUpdateBy((String) v.get("update_by"));
                ant.setCreateBy((String) v.get("create_by"));
                ant.setCreateTime((Date) v.get("create_time"));
                ant.setUpdateTime((Date) v.get("update_time"));
                return ant;
            } else if (setupType.equals("GO")) {
                JenkinsPipelineSetupGo go = new JenkinsPipelineSetupGo();
                go.setId((Long) v.get("id"));
                go.setScript((String) v.get("script"));
                go.setGoId((Long) v.get("value_1"));
                go.setJobId((Long) v.get("job_id"));
                go.setSequence((Integer) v.get("sequence"));
                go.setInstanceCode((String) v.get("instance_code"));
                go.setSetupType((String) v.get("setup_type"));
                go.setStatus((Integer) v.get("status"));
                go.setUpdateBy((String) v.get("update_by"));
                go.setCreateBy((String) v.get("create_by"));
                go.setCreateTime((Date) v.get("create_time"));
                go.setUpdateTime((Date) v.get("update_time"));
                return go;
            } else if (setupType.equals("PYTHON")) {
                JenkinsPipelineSetupPython python = new JenkinsPipelineSetupPython();
                python.setId((Long) v.get("id"));
                python.setScript((String) v.get("script"));
                python.setPythonId((Long) v.get("value_1"));
                python.setJobId((Long) v.get("job_id"));
                python.setSequence((Integer) v.get("sequence"));
                python.setInstanceCode((String) v.get("instance_code"));
                python.setSetupType((String) v.get("setup_type"));
                python.setStatus((Integer) v.get("status"));
                python.setUpdateBy((String) v.get("update_by"));
                python.setCreateBy((String) v.get("create_by"));
                python.setCreateTime((Date) v.get("create_time"));
                python.setUpdateTime((Date) v.get("update_time"));
                return python;
            } else if (setupType.equals("GRADLE")) {
                JenkinsPipelineSetupGradle gradle = new JenkinsPipelineSetupGradle();
                gradle.setId((Long) v.get("id"));
                gradle.setTask((String) v.get("script"));
                gradle.setGradleId((Long) v.get("value_1"));
                gradle.setJobId((Long) v.get("job_id"));
                gradle.setSequence((Integer) v.get("sequence"));
                gradle.setInstanceCode((String) v.get("instance_code"));
                gradle.setSetupType((String) v.get("setup_type"));
                gradle.setStatus((Integer) v.get("status"));
                gradle.setUpdateBy((String) v.get("update_by"));
                gradle.setCreateBy((String) v.get("create_by"));
                gradle.setCreateTime((Date) v.get("create_time"));
                gradle.setUpdateTime((Date) v.get("update_time"));
                return gradle;
            } else if (setupType.equals("NODE_JS")) {
                JenkinsPipelineSetupNodejs nodejs = new JenkinsPipelineSetupNodejs();
                nodejs.setId((Long) v.get("id"));
                nodejs.setScript((String) v.get("script"));
                nodejs.setNodejsId((Long) v.get("value_1"));
                nodejs.setJobId((Long) v.get("job_id"));
                nodejs.setSequence((Integer) v.get("sequence"));
                nodejs.setInstanceCode((String) v.get("instance_code"));
                nodejs.setSetupType((String) v.get("setup_type"));
                nodejs.setStatus((Integer) v.get("status"));
                nodejs.setUpdateBy((String) v.get("update_by"));
                nodejs.setCreateBy((String) v.get("create_by"));
                nodejs.setCreateTime((Date) v.get("create_time"));
                nodejs.setUpdateTime((Date) v.get("update_time"));
                return nodejs;
            }
        }
        return null;
    }


    @Override
    public Boolean saveList(List<JenkinsPipelineSetupCommonDTO> setups, Long jobId, String createBy) {

        for (JenkinsPipelineSetupCommonDTO setupDto : setups) {
            setupDto.setJobId(jobId);
            setupDto.setCreateBy(createBy);
            if (setupDto instanceof JenkinsPipelineSetupMavenDTO) {
                // maven
                JenkinsPipelineSetupMavenDTO maven = (JenkinsPipelineSetupMavenDTO) setupDto;
                save(maven);
            } else if (setupDto instanceof JenkinsPipelineSetupGoDTO) {
                // go
                JenkinsPipelineSetupGoDTO go = (JenkinsPipelineSetupGoDTO) setupDto;
                save(go);
            } else if (setupDto instanceof JenkinsPipelineSetupNodejsDTO) {
                // nodejs
                JenkinsPipelineSetupNodejsDTO nodejs = (JenkinsPipelineSetupNodejsDTO) setupDto;
                save(nodejs);
            } else if (setupDto instanceof JenkinsPipelineSetupShellDTO) {
                // shell
                JenkinsPipelineSetupShellDTO shell = (JenkinsPipelineSetupShellDTO) setupDto;
                save(shell);
            } else if (setupDto instanceof JenkinsPipelineSetupAntDTO) {
                // ant
                JenkinsPipelineSetupAntDTO ant = (JenkinsPipelineSetupAntDTO) setupDto;
                save(ant);
            } else if (setupDto instanceof JenkinsPipelineSetupGradleDTO) {
                // gradle
                JenkinsPipelineSetupGradleDTO gradle = (JenkinsPipelineSetupGradleDTO) setupDto;
                save(gradle);
            } else if (setupDto instanceof JenkinsPipelineSetupPythonDTO) {
                // python
                JenkinsPipelineSetupPythonDTO python = (JenkinsPipelineSetupPythonDTO) setupDto;
                save(python);
            }
        }

        return true;
    }

    @Override
    public List<JenkinsPipelineSetupCommon> querySetupList(Long jobId) {
        List<Map> maps = jenkinsPipelineSetupMapper.querySetupList(jobId);
        List<JenkinsPipelineSetupCommon> resultList = new ArrayList<>();
        maps.forEach(v -> {
            JenkinsPipelineSetupCommon setup = mapToJenkinsPipelineSetupCommon(v);
            if (null != setup) {
                resultList.add(setup);
            }
        });
        return resultList;
    }


    public JenkinsPipelineSetupCommonService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupMapper jenkinsPipelineSetupMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupMapper = jenkinsPipelineSetupMapper;
    }
}
