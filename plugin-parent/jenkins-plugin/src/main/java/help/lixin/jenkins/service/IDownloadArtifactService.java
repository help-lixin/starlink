package help.lixin.jenkins.service;

import com.cdancy.jenkins.rest.domain.job.Artifact;
import help.lixin.jenkins.action.DownloadStrategy;

import java.io.IOException;
import java.util.function.Consumer;

public interface IDownloadArtifactService {
    void download(String artifactPath, String jobName, int buildNumber, Artifact artifact, Consumer<String> consumer) throws IOException;

    DownloadStrategy support();
}
