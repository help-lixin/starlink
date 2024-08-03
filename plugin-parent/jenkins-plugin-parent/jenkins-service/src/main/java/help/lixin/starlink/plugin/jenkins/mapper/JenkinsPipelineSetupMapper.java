package help.lixin.starlink.plugin.jenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jenkins.domain.*;

import java.util.List;
import java.util.Map;

public interface JenkinsPipelineSetupMapper extends BaseMapper<JenkinsPipelineSetupCommon> {

    int insertSetup(JenkinsPipelineSetupCommon entity);

    List<Map> querySetupList(Long jobId);

    Map querySetup(Long id);


    /*setup insert*/
    Integer insertAnt(JenkinsPipelineSetupAnt jenkinsPipelineSetupAnt);

    Integer insertGo(JenkinsPipelineSetupGo jenkinsPipelineSetupGo);

    Integer insertGradle(JenkinsPipelineSetupGradle jenkinsPipelineSetupGradle);

    Integer insertMaven(JenkinsPipelineSetupMaven jenkinsPipelineSetupMaven);

    Integer insertNodejs(JenkinsPipelineSetupNodejs jenkinsPipelineSetupNodejs);

    Integer insertPython(JenkinsPipelineSetupPython jenkinsPipelineSetupPython);

    Integer insertShell(JenkinsPipelineSetupShell jenkinsPipelineSetupShell);

    /*setup update*/
    Integer updateAnt(JenkinsPipelineSetupAnt jenkinsPipelineSetupAnt);
    Integer updateGo(JenkinsPipelineSetupGo jenkinsPipelineSetupGo);
    Integer updateGradle(JenkinsPipelineSetupGradle jenkinsPipelineSetupGradle);
    Integer updateMaven(JenkinsPipelineSetupMaven jenkinsPipelineSetupMaven);
    Integer updateNodejs(JenkinsPipelineSetupNodejs jenkinsPipelineSetupNodejs);
    Integer updatePython(JenkinsPipelineSetupPython jenkinsPipelineSetupPython);
    Integer updateShell(JenkinsPipelineSetupShell jenkinsPipelineSetupShell);
}