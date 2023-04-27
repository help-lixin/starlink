package help.lixin.jenkins.service.impl;

import help.lixin.core.exception.jenkins.JenkinsTemplateNotFoundException;
import help.lixin.jenkins.action.entity.JenkinsActionParams;
import help.lixin.jenkins.service.ILoadJenkinsTemplateService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DiskLoadJenkinsTemplateService implements ILoadJenkinsTemplateService {
    private ILoadJenkinsTemplateService parent;

    @Override
    public String load(JenkinsActionParams params) throws JenkinsTemplateNotFoundException {
        String template = null;
        File templateFile = new File(params.getTemplateFile());
        if (!templateFile.exists()) { // 文件不存在,抛出异常
            String msg = String.format("jenkins模板文件:[%s]不存在", templateFile.getName());
            throw new JenkinsTemplateNotFoundException(msg);
        } else if (templateFile.exists()) { // 文件存在,读取文件
            try {
                template = FileUtils.readFileToString(templateFile, "UTF-8");
            } catch (IOException e) {
                String msg = String.format("加载jenkins模板文件:[%s],出现错误,异常信息如下:[\n%s]", templateFile.getName(), e.getMessage());
                throw new JenkinsTemplateNotFoundException(msg);
            }
        }

        // 如果parent还存在,则继续委派
        if (null == template && null != parent) {
            template = parent.load(params);
        }
        return template;
    }

    @Override
    public void setParent(ILoadJenkinsTemplateService loadJenkinsTemplateService) {
        this.parent = loadJenkinsTemplateService;
    }
}
