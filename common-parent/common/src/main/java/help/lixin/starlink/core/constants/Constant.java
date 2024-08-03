package help.lixin.starlink.core.constants;

public class Constant {
    public static class Project {
        // 项目名称
        public static final String PROJECT_NAME = "PROJECT_NAME";
        // 项目目录
        // public static final String PROJECT_WORKSPACE = "PROJECT_WORKSPACE";
    }

    // 代码仓库
    public static class SourceCodeRepository {
        // gitlab/svn/git/github
        public static final String REPOSITORY_TYPE = "REPOSITORY_TYPE";
        public static final String REPOSITORY_BRANCH = "BRANCH";
        public static final String REPOSITORY_URL = "URL";
        // clone下载回来之后的,源代码目录
        public static final String REPOSITORY_WORKSPACE = "REPOSITORY_WORKSPACE";
    }

    public static class Common {
        // 年
        public static final String YEAR = "year";
        // 月
        public static final String MONTH = "month";
        // 日
        public static final String DAY = "day";
        // 小时
        public static final String HOUR = "hour";
        // 分
        public static final String MINUTE = "minute";
        // 秒
        public static final String SECOND = "second";
        // 全量
        public static final String DATETIME = "datetime";
    }

    public static class BuildInfo {
        // 触发构建时的唯一id
        public static final String BUILD_NUMBER = "BUILD_NUMBER";
    }


    // 成品库信息
    @Deprecated
    public static class Artifact {
        // 成品库位置(/Users/lixin/GitRepository/spring-web-demo/target)
        @Deprecated
        public static final String ARTIFACT_DIR = "artifact_dir";
        // 成品库文件名
        // spring-web-demo-1.1.0.jar
        @Deprecated
        public static final String ARTIFACT_NAME = "artifact_name";
        // 成品库全名称
        // /Users/lixin/GitRepository/spring-web-demo/target/spring-web-demo-1.1.0.jar
        @Deprecated
        public static final String ARTIFACT_FULL_PATH = "artifact_full_path";
    }

    // 镜像仓库信息(比如:harbor)

    /**
     * 案例: registry.lixin.help/spring-web-demo/spring-web-demo:1.0.0
     */
    public static class ImageRepository {
        /**
         * 镜像前缀(registry.lixin.help)
         */

        public static final String IMAGE_REPOSITORY = "IMAGE_REPOSITORY";
        /**
         * 仓库的用户名
         */
        public static final String IMAGE_USERNAME = "IMAGE_USERNAME";
        /**
         * 仓库密码
         */
        public static final String IMAGE_PASSWORD = "IMAGE_PASSWORD";

        /**
         * 仓库密码是否加密
         */
        public static final String IMAGE_PASSWORD_IS_ENCRYPTION = "IMAGE_PASSWORD_IS_ENCRYPTION";
        public static final String YES = "Y";
        public static final String NO = "N";


        /**
         * 项目(spring-web-demo)
         */
        public static final String IMAGE_PROJECT = "IMAGE_PROJECT";


        /**
         * 目标镜像变量(registry.lixin.help/spring-web-demo/spring-web-demo:1.0.0)
         */
        public static final String TARGET_IMAGE = "TARGET_IMAGE";
    }

    public static class Docker {
        // Dockerfile
        public static final String DOCKER_FILE = "DOCKER_FILE";
    }
}
