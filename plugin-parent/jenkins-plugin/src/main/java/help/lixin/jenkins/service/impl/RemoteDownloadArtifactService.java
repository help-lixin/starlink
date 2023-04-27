package help.lixin.jenkins.service.impl;

import com.cdancy.jenkins.rest.domain.job.Artifact;
import help.lixin.jenkins.action.entity.DownloadStrategy;
import help.lixin.jenkins.service.IDownloadArtifactService;
import help.lixin.jenkins.service.IJobService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public class RemoteDownloadArtifactService implements IDownloadArtifactService {
    private Logger logger = LoggerFactory.getLogger(RemoteDownloadArtifactService.class);

    private IJobService jobService;

    public RemoteDownloadArtifactService(IJobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void download(String localDiskArtifactPath, String jobName, int buildNumber, Artifact artifact, Consumer<String> consumer) throws IOException {
        // 在这里也只一个成品出来.
        String artifactFullPath = String.format("%s/%s/%s/%s", localDiskArtifactPath, jobName, buildNumber, artifact.fileName());
        // 先强制创建一下父目录
        FileUtils.forceMkdirParent(new File(artifactFullPath));
        if (logger.isDebugEnabled()) {
            logger.debug("start copy artifact:[{}] to disk:[{}]", artifact.fileName(), artifactFullPath);
        }

        // 通过jenkins api远程下载文件
        InputStream inputStream = jobService.artifact(null, jobName, buildNumber, artifact.relativePath());
        // 指定输出的位置
        IOUtils.copy(inputStream, new FileOutputStream(artifactFullPath));
        if (logger.isDebugEnabled()) {
            logger.debug("end copy artifact:[{}] to disk:[{}]", artifact.fileName(), artifactFullPath);
        }

        // 处理Callback
        if (null != consumer) {
            consumer.accept(artifactFullPath);
        }
    }

    @Override
    public DownloadStrategy support() {
        return DownloadStrategy.REMOTE;
    }
}
