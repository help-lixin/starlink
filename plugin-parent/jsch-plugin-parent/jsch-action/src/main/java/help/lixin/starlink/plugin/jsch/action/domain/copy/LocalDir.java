package help.lixin.starlink.plugin.jsch.action.domain.copy;

public class LocalDir {
    // 本地目录
    private String localDir;
    // 包含哪些文件,可以是表达式
    private String includes;
    // 排除哪些文件,可以是表达式
    private String excludes;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        // 本地目录
        private String localDir;
        // 包含哪些文件,可以是表达式
        private String includes;
        // 排除哪些文件,可以是表达式
        private String excludes;

        public Builder withLocalDir(String localDir) {
            this.localDir = localDir;
            return this;
        }

        public Builder withIncludes(String includes) {
            this.includes = includes;
            return this;
        }

        public Builder withExcludes(String excludes) {
            this.excludes = excludes;
            return this;
        }

        public LocalDir build() {
            LocalDir res = new LocalDir();
            res.setLocalDir(localDir);
            res.setExcludes(excludes);
            res.setIncludes(includes);
            return res;
        }
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }
}
