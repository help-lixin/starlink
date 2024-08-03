package help.lixin.starlink.plugin.xxl.job.request.info;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.request.EnvRequest;
import help.lixin.starlink.plugin.xxl.job.api.model.ExecutorBlockStrategyEnum;
import help.lixin.starlink.plugin.xxl.job.api.model.ExecutorRouteStrategyEnum;
import help.lixin.starlink.plugin.xxl.job.api.model.MisfireStrategyEnum;
import help.lixin.starlink.plugin.xxl.job.api.model.ScheduleTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 4:59 下午
 * @Description
 */
public class JobInfoCreateVO {

    @Valid
    private EnvRequest envRequest;

    @NotNull(message = "执行器id不能为空")
    @ApiModelProperty(value = "执行器主键id")
    private int jobGroup; // 执行器主键ID

    @NotBlank(message = "任务描述不能为空")
    @ApiModelProperty(value = "任务描述")
    private String jobDesc;

    @NotBlank(message = "负责人 不能为空")
    @ApiModelProperty(value = "负责人")
    private String author; // 负责人

    @ApiModelProperty(value = "报警邮件")
    private String alarmEmail; // 报警邮件

    @NotBlank(message = "调度类型 不能为空")
    @ApiModelProperty(value = "调度类型")
    private String scheduleType = ScheduleTypeEnum.CRON.getDesc(); // 调度类型

    @NotBlank(message = "调度配置 不能为空")
    @ApiModelProperty(value = "调度配置")
    private String scheduleConf; // 调度配置，值含义取决于调度类型

    @ApiModelProperty(value = "调度过期策略")
    private String misfireStrategy = MisfireStrategyEnum.DO_NOTHING.getDesc(); // 调度过期策略

    @ApiModelProperty(value = "执行器路由策略")
    private String executorRouteStrategy = ExecutorRouteStrategyEnum.FIRST.getDesc(); // 执行器路由策略

    @NotBlank(message = "执行器 不能为空")
    @ApiModelProperty(value = "执行器名称")
    private String executorHandler; // 执行器，任务Handler名称

    @ApiModelProperty(value = "任务参数")
    private String executorParam; // 执行器，任务参数

    @NotBlank(message = "阻塞处理策略 不能为空")
    @ApiModelProperty(value = "阻塞处理策略")
    private String executorBlockStrategy = ExecutorBlockStrategyEnum.SERIAL_EXECUTION.getDesc(); // 阻塞处理策略

    @ApiModelProperty(value = "任务执行超时时间")
    private int executorTimeout = 60; // 任务执行超时时间，单位秒

    @ApiModelProperty(value = "失败重试次数")
    private int executorFailRetryCount = 3; // 失败重试次数

    @NotBlank(message = "运行模式 不能为空")
    @ApiModelProperty(value = "运行模式")
    private String glueType = "BEAN"; // GLUE类型 #com.xxl.job.core.glue.GlueTypeEnum

    @ApiModelProperty(value = "GLUE源代码")
    private String glueSource; // GLUE源代码

    @ApiModelProperty(value = "GLUE备注")
    private String glueRemark; // GLUE备注

    @ApiModelProperty(value = "子任务ID,多个逗号分隔")
    private String childJobId; // 子任务ID，多个逗号分隔

    public int getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(int jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleConf() {
        return scheduleConf;
    }

    public void setScheduleConf(String scheduleConf) {
        this.scheduleConf = scheduleConf;
    }

    public String getMisfireStrategy() {
        return misfireStrategy;
    }

    public void setMisfireStrategy(String misfireStrategy) {
        this.misfireStrategy = misfireStrategy;
    }

    public String getExecutorRouteStrategy() {
        return executorRouteStrategy;
    }

    public void setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
    }

    public int getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(int executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public int getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    public void setExecutorFailRetryCount(int executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public String getGlueType() {
        return glueType;
    }

    public void setGlueType(String glueType) {
        this.glueType = glueType;
    }

    public String getGlueSource() {
        return glueSource;
    }

    public void setGlueSource(String glueSource) {
        this.glueSource = glueSource;
    }

    public String getGlueRemark() {
        return glueRemark;
    }

    public void setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
    }

    public String getChildJobId() {
        return childJobId;
    }

    public void setChildJobId(String childJobId) {
        this.childJobId = childJobId;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }
}
