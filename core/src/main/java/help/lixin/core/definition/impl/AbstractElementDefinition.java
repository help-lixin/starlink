package help.lixin.core.definition.impl;

import help.lixin.core.definition.ElementDefinition;

public abstract class AbstractElementDefinition implements ElementDefinition {
    public static final String START = "_start";

    protected String id;
    protected String source;
    protected String target;

    protected String name;
    protected String clazz;
    protected String params;

    protected String plugin;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String getTarget() {
        return target;
    }


    @Override
    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String getParams() {
        return params;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }
}
