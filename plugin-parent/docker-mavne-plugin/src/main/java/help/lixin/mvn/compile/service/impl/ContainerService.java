package help.lixin.mvn.compile.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import help.lixin.mvn.compile.service.IContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ContainerService implements IContainerService {

    private Logger logger = LoggerFactory.getLogger(ContainerService.class);

    private DockerClient mavenDockerClient;

    public ContainerService(DockerClient mavenDockerClient) {
        this.mavenDockerClient = mavenDockerClient;
    }

    public String mvnCompile(
            // 容器唯一名称
            String containerUniqueName,
            // registry.cn-hangzhou.aliyuncs.com/acs/maven:latest
            String image,
            // /usr/src/mymaven
            String workingDir,
            // /tmp/583871138:/usr/src/mymaven
            Map<String, String> binds,
            // "mvn", "clean", "install" , "-X"
            List<String> cmds) throws Exception {


        List<Image> images = mavenDockerClient.listImagesCmd()
                //
                .withImageNameFilter(image)
                //
                .withShowAll(Boolean.TRUE)
                //
                .exec();
        if (images.isEmpty()) {
            logger.info("开始拉取镜像:[{}]", image);
            // 镜像不存的情况下,先拉取镜像.
            mavenDockerClient.pullImageCmd(image) //
                    .exec(new ResultCallback.Adapter<>() {
                        @Override
                        public void onNext(PullResponseItem object) {
                            if (null != object.getProgress()) {
                                logger.info(object.getProgress());
                            }
                        }
                    }) //
                    .awaitCompletion();
        }

        // 1. 先看下这个容器实例是否存在,如果存在,则先删除容器
        List<Container> containers = mavenDockerClient.listContainersCmd() //
                .withNameFilter(Collections.singletonList(containerUniqueName)) //
                .withShowAll(Boolean.TRUE) //
                .exec();
        if (!containers.isEmpty()) {
            for (Container container : containers) {
                String containerId = container.getId();
                try {
                    mavenDockerClient.stopContainerCmd(containerId).exec();
                } catch (Exception ignore) {
                }
                mavenDockerClient.removeContainerCmd(containerId).exec();
            }
        }

        // Bind处理
        List<Bind> bindList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = binds.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String source = entry.getKey();
            String destination = entry.getValue();
            Bind bind = new Bind(source, new Volume(destination));
            bindList.add(bind);
        }

        // 创建容器
        CreateContainerResponse exec = mavenDockerClient.createContainerCmd(image) //
                .withStdinOpen(Boolean.TRUE) //
                .withAttachStderr(Boolean.TRUE) //
                .withAttachStdin(Boolean.TRUE) //
                .withAttachStdout(Boolean.TRUE) //
                .withName(containerUniqueName)//
                .withBinds(bindList) //
                .withWorkingDir(workingDir) //
                .withCmd(cmds) //
                .exec();//
        // 获得容器id
        String containerId = exec.getId();
        // 启动容器
        mavenDockerClient.startContainerCmd(containerId).exec();


        // 查看容器日志
        mavenDockerClient.logContainerCmd(containerId) //
                .withTailAll() //
                .withFollowStream(Boolean.TRUE) //
                .withStdOut(Boolean.TRUE) //
                .withStdErr(Boolean.TRUE) //
                .exec(new ResultCallback.Adapter<>() {
                    private Frame item;

                    @Override
                    public void onNext(Frame item) {
                        this.item = item;
                        logger.info(new String(item.getPayload()));
                    }

                    @Override
                    public void onComplete() {
                        switch (item.getStreamType()) {
                            case STDIN:
                                break;
                            case STDERR:
                                break;
                            case STDOUT:
                                break;
                        }
                        super.onComplete();
                    }
                }) //
                .awaitCompletion();
        return containerId;
    }
}
