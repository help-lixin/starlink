package help.lixin.core.constants;

public class Constant {

    public static class Pipeline {
        // 实例id(类似于zipkin中的traceId)
        public static final String PIPELINE_INSTANCE_ID = "PIPELINE_INSTANCE_ID";
        // 节点id(类似于zipkin中的spaceId)
        public static final String PIPELINE_NODE_ID = "PIPELINE_NODE_ID";

        public static final String SEQUENCE = "SEQUENCE";

        public static final String INPUT_JSON_PARAM = "_input_json_param";
    }

    // 代码仓库
    public static class CodeRepository {
        // gitlab/svn/git/github
        public static final String CODE_REPOSITORY = "CODE_REPOSITORY";
        public static final String BRANCH = "branch";
        public static final String URL = "url";
        // clone下载回来之后的,源代码目录
        public static final String WORKSPACE_DIR = "WORKSPACE_DIR";
    }

    public static class Common {
        // gitlab/jenkins/harbor/shell/k8s-deploy
        public static final String ACTION_NAME = "ACTION_NAME";

        // 年
        public static final String YEAR = "YEAR";
        // 月
        public static final String MONTH = "MONTH";
        // 日
        public static final String DAY = "DAY";
        // 小时
        public static final String HOUR = "HOUR";
        // 分
        public static final String MINUTE = "MINUTE";
        // 秒
        public static final String SECOND = "SECOND";
        // 全量
        public static final String DATETIME = "DATETIME";
    }

    public static class BuildInfo {
        // 触发构建时的唯一id
        public static final String BUILD_NUMBER = "BUILD_NUMBER";
    }


    // 成品库信息
    public static class Artifact {
        // 成品库位置(/Users/lixin/GitRepository/spring-web-demo/target)
        public static final String ARTIFACT_DIR = "ARTIFACT_DIR";
        // 成品库文件名
        // spring-web-demo-1.1.0.jar
        public static final String ARTIFACT_NAME = "ARTIFACT_NAME";
        // 成品库全名称
        // /Users/lixin/GitRepository/spring-web-demo/target/spring-web-demo-1.1.0.jar
        public static final String ARTIFACT_FULL_PATH = "ARTIFACT_FULL_PATH";
    }

    // 镜像仓库信息(比如:harbor)
    public static class ImageRepository {
        public static final String HTTP_REPOSITORY_URL = "HTTP_REPOSITORY_URL";
        // 仓库URL
        public static final String REPOSITORY_URL = "REPOSITORY_URL";
        // 仓库的用户名
        public static final String REPOSITORY_USERNAME = "REPOSITORY_USERNAME";
        // 仓库密码
        public static final String REPOSITORY_PASSWORD = "REPOSITORY_PASSWORD";

    }

    public static class Docker {
        // Dockerfile
        public static final String DOCKER_FILE = "DOCKER_FILE";
    }
}
