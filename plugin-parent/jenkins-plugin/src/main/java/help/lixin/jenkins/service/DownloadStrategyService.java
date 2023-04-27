package help.lixin.jenkins.service;

import com.cdancy.jenkins.rest.domain.job.Artifact;
import help.lixin.jenkins.action.entity.DownloadStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DownloadStrategyService {

    private Map<DownloadStrategy, IDownloadArtifactService> downloadArtifactServiceMap = new HashMap<>();

    public DownloadStrategyService(Map<DownloadStrategy, IDownloadArtifactService> downloadArtifactServiceMap) {
        this.downloadArtifactServiceMap = downloadArtifactServiceMap;
    }

    public void download(DownloadStrategy strategy, String artifactPath, String jobName, int buildNumber, Artifact artifact, Consumer<String> consumer) throws IOException {
        IDownloadArtifactService downloadArtifactService = downloadArtifactServiceMap.get(strategy);
        downloadArtifactService.download(artifactPath, jobName, buildNumber, artifact, consumer);
    }
}
