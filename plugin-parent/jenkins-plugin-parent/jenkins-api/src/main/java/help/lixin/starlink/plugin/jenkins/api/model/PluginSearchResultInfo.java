package help.lixin.starlink.plugin.jenkins.api.model;

public class PluginSearchResultInfo {
    // 插件名称
    // antisamy-markup-formatter
    private String name;
    // 插件版本
    // 162.v0e6ec0fcfcf6
    private String version;
    // 插件参考地址
    // https://plugins.jenkins.io/antisamy-markup-formatter
    private String wiki;
    // 插件显示名称
    // OWASP Markup Formatter
    private String displayName;

    // 插件标题
    // OWASP Markup Formatter
    private String title;

    // 插件详细信息
    //Uses the <a href=\"https://www.owasp.org/index.php/OWASP_Java_HTML_Sanitizer_Project\" target=\"_blank\" rel=\"nofollow noopener noreferrer\">OWASP Java HTML Sanitizer</a> to allow safe-seeming HTML markup to be entered in project descriptions and the like.
    private String excerpt;
    // 人气
    // 279844
    private Long popularity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }
}
