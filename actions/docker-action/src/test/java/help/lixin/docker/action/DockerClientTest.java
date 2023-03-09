package help.lixin.docker.action;

import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.AuthResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.command.PushImageResultCallback;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DockerClientTest extends BasicTest {

    @Test
    public void testDockerAuth() throws Exception {
        AuthResponse authResponse = dockerClient.authCmd().exec();
        Assert.assertEquals("Login Succeeded", authResponse.getStatus());
    }

    @Test
    public void testPullImage() throws Exception {
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
            @Override
            public void onNext(PullResponseItem item) {
                if (item.isPullSuccessIndicated()) {
                    System.out.println("success");
                } else {
                    System.out.println(item);
                }
            }
        };
        // String image = "103.215.125.86:3080/spring-web-demo/spring-web-demo:v34";
        String image = "nginx:latest";
        dockerClient.pullImageCmd(image).exec(pullImageResultCallback).awaitCompletion();
    }


    @Test
    public void testImageFilter() {
        // https://docs.docker.com/engine/api/v1.42/#tag/Image/operation/ImageList
        List<Image> images = dockerClient.listImagesCmd().withReferenceFilter("nginx:latest").exec();
        Assert.assertEquals(true, images.size() > 0);
    }

    @Test
    public void testTagImage() {
        String imageName = "nginx";
        String imageTag = "latest";
        String imageId = String.format("%s:%s", imageName, imageTag);

        // 103.215.125.86:3080/library/nginx:v1.0
        String tag = "v1.0";
        String imageNameWithRepository = "103.215.125.86:3080/library/" + imageName;
        dockerClient.tagImageCmd(imageId, imageNameWithRepository, tag).exec();
        System.out.println("");
    }


    @Test
    public void testPushImage() throws Exception {
        String imageId = "103.215.125.86:3080/library/nginx:v1.0";
        dockerClient.pushImageCmd(imageId).exec(new PushImageResultCallback()).awaitCompletion();
    }

    @Test
    public void testBuildImage() throws Exception {
        // 注意事项:
        // 如果指定:withBaseDirectory,则:代表工作目录,此时,要注意:DockerFile和成品都要在这个目录下.
        // 如果指定:dockerFile为具体的文件,则默认Dockerfile与成品须在一个目录下,否则,会有问题.
        // lixin-macbook:tmp lixin$ pwd
        // /Users/lixin/tmp
        // lixin-macbook:tmp lixin$ ll
        //   -rw-r--r--@   1 lixin  wheel       983  3  9 14:20 Dockerfile
        //   -rw-r--r--    1 lixin  staff  16653399  3  9 14:25 spring-web-demo-1.1.0.jar
        File dockerFile = new File("/Users/lixin/tmp/Dockerfile");
        // File baseDir = new File("/Users/lixin/tmp");
        String appFile = "spring-web-demo-1.1.0.jar";
        Set<String> tags = new HashSet<>();
        tags.add("103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0-1412");

        dockerClient.buildImageCmd(dockerFile)
                // .withBaseDirectory(baseDir)
                //
                .withBuildArg("APP_FILE", appFile)
                //
                .withTags(tags)
                //
                .exec(new BuildImageResultCallback()).awaitCompletion();
    }
}
