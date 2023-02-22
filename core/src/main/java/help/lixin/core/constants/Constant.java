package help.lixin.core.constants;

public class Constant {

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

    // 仓库信息
    public static class Repository {
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
