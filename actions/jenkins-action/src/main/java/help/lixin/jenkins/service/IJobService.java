package help.lixin.jenkins.service;

import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;

import java.io.InputStream;

public interface IJobService {
    /**
     * 查询所有的job
     *
     * @param folderPath
     * @return
     */
    JobList query(String folderPath);

    /**
     * 根据xml创建job
     *
     * @return
     */
    RequestStatus createJob(CreateJobContext context);


    /**
     * 删除job
     *
     * @param optionalFolderPath
     * @param jobName
     * @return
     */
    RequestStatus deleteJob(String optionalFolderPath,
                            String jobName);


    /**
     * 触发任务构建
     *
     * @return
     */
    IntegerResponse triggerBuild(TriggerBuildContext context);

    /**
     * 启用job
     *
     * @param optionalFolderPath
     * @param jobName
     * @return
     */
    boolean enable(String optionalFolderPath,
                   //
                   String jobName);

    /**
     * 禁用job
     *
     * @param optionalFolderPath
     * @param jobName
     * @return
     */
    boolean disable(String optionalFolderPath,
                    //
                    String jobName);

    /**
     * term build task
     *
     * @param optionalFolderPath
     * @param jobName
     * @param buildNumber
     * @return
     */
    RequestStatus term(String optionalFolderPath,
                       //
                       String jobName,
                       //
                       int buildNumber);

    /**
     * kill build task
     *
     * @param optionalFolderPath
     * @param jobName
     * @param buildNumber
     * @return
     */
    RequestStatus kill(String optionalFolderPath,
                       String jobName,
                       int buildNumber);

    /**
     * 查看任务job配置的xml
     *
     * @param optionalFolderPath
     * @param jobName
     * @return
     */
    String getConfigXml(String optionalFolderPath,
                        //
                        String jobName);


    /**
     * 根据jobName获取JobInfo(里面包含很详细的信息,比如:nextBuildNumber)
     *
     * @param optionalFolderPath
     * @param jobName
     * @return
     */
    JobInfo getJobInfo(String optionalFolderPath,
                       String jobName);


    /**
     * 获取构建后的信息
     *
     * @param optionalFolderPath
     * @param jobName
     * @param buildNumber
     * @return
     */
    BuildInfo getBuildInfo(String optionalFolderPath,
                           //
                           String jobName,
                           //
                           int buildNumber);


    /**
     * 取最后的成品
     * artifacts: [
     * {
     * displayPath: "spring-web-demo-1.1.0.jar",
     * fileName: "spring-web-demo-1.1.0.jar",
     * relativePath: "target/spring-web-demo-1.1.0.jar"
     * }
     * ]
     *
     * @param optionalFolderPath
     * @param jobName
     * @param buildNumber
     * @param relativeArtifactPath
     * @return
     */
    InputStream artifact(String optionalFolderPath,
                         String jobName,
                         int buildNumber,
                         String relativeArtifactPath);


    /**
     * 根据:jobName/buildNumber 获取这一次构建的详细日志
     * @param optionalFolderPath
     * @param jobName
     * @param buildNumber
     * @param start
     * @return
     */
    ProgressiveText lookBuildLog(String optionalFolderPath,
                                    String jobName,
                                    int buildNumber,
                                    int start);

}
