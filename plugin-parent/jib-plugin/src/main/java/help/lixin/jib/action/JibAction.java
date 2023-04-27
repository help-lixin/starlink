package help.lixin.jib.action;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.tools.jib.api.*;
import com.google.cloud.tools.jib.api.buildplan.AbsoluteUnixPath;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.jib.action.entity.*;
import help.lixin.jib.service.JibFaceService;
import help.lixin.jib.action.entity.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class JibAction implements Action {
    private Logger logger = LoggerFactory.getLogger(JibAction.class);

    public static final String IMAGE_BUILD_ACTION = "oci";

    private JibFaceService jibFaceService;

    public JibAction(JibFaceService jibFaceService) {
        this.jibFaceService = jibFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        }

        HashMap<String, Object> tmpCtx = new HashMap<>();
        tmpCtx.putAll(ctx.getVars());

        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        JibActionParams actionParams = mapper.readValue(stageParams, JibActionParams.class);


        // 待处理模型
        FromImage from = actionParams.getFrom();
        TargetImage to = actionParams.getTo();
        String workDir = actionParams.getWorkDir();
        String user = actionParams.getUser();
        Map<String, String> envs = actionParams.getEnvs();
        List<Copy> copys = actionParams.getCopys();
        List<String> entrypoints = actionParams.getEntrypoints();
        Map<String, String> labels = actionParams.getLabels();
        Set<Port> exposedPorts = actionParams.getExposedPorts();
        List<String> volumes = actionParams.getVolumes();
        List<String> args = actionParams.getArgs();

        // 先处理环境变量部份
        Map<String, String> evnsMap = new HashMap<>();
        if (null != envs) {
            // 先把那些很明确的变量抽出来,因为:有可能在这里的变量又依赖另一个变量.
            String express = "$";
            Iterator<Map.Entry<String, String>> iterator = envs.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                if (!key.startsWith(express) && !value.startsWith(express)) {
                    tmpCtx.put(key, value);
                }
            }
            evnsMap = processEnvs(envs, tmpCtx);
            tmpCtx.putAll(evnsMap);
        }

        RegistryImage fromRegistryImage = registryImage(from, tmpCtx);
        RegistryImage toRegistryImage = registryImage(to, tmpCtx);

        JibContainerBuilder container = Jib.from(fromRegistryImage);
        container.setEnvironment(evnsMap);

        if (null != workDir) {
            String workFullPath = expression(workDir, tmpCtx);
            container.setWorkingDirectory(AbsoluteUnixPath.get(workFullPath));
        }

        if (null != user) {
            String userTmp = expression(user, tmpCtx);
            container.setUser(userTmp);
        }

        if (!copys.isEmpty()) {
            List<Copy> copyList = processCopys(copys, tmpCtx);
            for (Copy copy : copyList) {
                List<Path> paths = copy.getFiles().stream().map(i -> {
                    return Path.of(i);
                }).collect(Collectors.toList());
                String pathInContainer = copy.getPathInContainer();
                container.addLayer(paths, AbsoluteUnixPath.get(pathInContainer));
            }
        }

        if (!entrypoints.isEmpty()) {
            List<String> entrypointList = processEntrypoints(entrypoints, tmpCtx);
            container.setEntrypoint(entrypointList);
        }

        if (!labels.isEmpty()) {
            Map<String, String> labelMap = processLabels(labels, tmpCtx);
            container.setLabels(labelMap);
        }

        if (!exposedPorts.isEmpty()) {
            Set<com.google.cloud.tools.jib.api.buildplan.Port> ports = exposedPorts.stream().map(p -> {
                return com.google.cloud.tools.jib.api.buildplan.Port.parseProtocol(p.getPort(), p.getProtocol());
            }).collect(Collectors.toSet());
            container.setExposedPorts(ports);
        }

        if (!volumes.isEmpty()) {
            Set<AbsoluteUnixPath> volumeList = processVolumes(volumes, tmpCtx);
            container.setVolumes(volumeList);
        }

        if (!args.isEmpty()) {
            List<String> argsList = processArgs(args, tmpCtx);
            container.setProgramArguments(argsList);
        }

        // 生成目标镜像
        container.containerize(Containerizer.to(toRegistryImage) //
                .setAllowInsecureRegistries(true));
        logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        return true;
    }


    protected List<String> processArgs(List<String> args, Map<String, Object> ctx) {
        List<String> argsList = args.stream().map(i -> {
            return expression(i, ctx);
        }).collect(Collectors.toList());
        return argsList;
    }

    protected Set<AbsoluteUnixPath> processVolumes(List<String> volumes, Map<String, Object> ctx) {
        return volumes.stream().map(i -> {
            return expression(i, ctx);
        }).map(i -> {
            return AbsoluteUnixPath.get(i);
        }).collect(Collectors.toSet());
    }

    protected Map<String, String> processLabels(Map<String, String> labels, Map<String, Object> ctx) {
        Map<String, String> res = new HashMap<>(labels.size());

        Iterator<Map.Entry<String, String>> iterator = labels.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = expression(next.getKey(), ctx);
            String value = expression(next.getValue(), ctx);

            res.put(key, value);
        }
        return res;
    }

    protected List<String> processEntrypoints(List<String> entrypoints, Map<String, Object> ctx) {
        return entrypoints.stream() //
                .map(i -> {
                    return expression(i, ctx);
                }).collect(Collectors.toList());
    }

    protected List<Copy> processCopys(List<Copy> copys, Map<String, Object> ctx) {
        List<Copy> result = copys.stream().map(copy -> {
            List<String> files = copy.getFiles().stream().map(i -> {
                return expression(i, ctx);
            }).collect(Collectors.toList());
            String pathInContainer = expression(copy.getPathInContainer(), ctx);

            Copy tmpCopy = new Copy();
            tmpCopy.setFiles(files);
            tmpCopy.setPathInContainer(pathInContainer);
            return tmpCopy;
        }).collect(Collectors.toList());
        return result;
    }


    protected Map<String, String> processEnvs(Map<String, String> envs, Map<String, Object> ctx) {
        Map<String, String> res = new HashMap<>();
        Iterator<Map.Entry<String, String>> iterator = envs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = expression(entry.getKey(), ctx);
            String value = expression(entry.getValue(), ctx);
            res.put(key, value);
            ctx.put(key, value);
        }
        return res;
    }


    protected RegistryImage registryImage(TargetImage targetImage, Map<String, Object> ctx) throws Exception {
        RegistryImage registryFromImage = RegistryImage.named(expression(targetImage.getImage(), ctx));
        help.lixin.jib.action.entity.Credential credential = targetImage.getCredential();
        if (null != credential) {
            String username = expression(credential.getUsername(), ctx);
            String password = expression(credential.getPassword(), ctx);
            registryFromImage.addCredential(username, password);
        }
        return registryFromImage;
    }

    protected RegistryImage registryImage(FromImage from, Map<String, Object> ctx) throws Exception {
        RegistryImage registryFromImage = RegistryImage.named(expression(from.getImage(), ctx));
        Credential credential = from.getCredential();
        if (null != credential) {
            String username = expression(credential.getUsername(), ctx);
            String password = expression(credential.getPassword(), ctx);
            registryFromImage.addCredential(username, password);
        }
        return registryFromImage;
    }

    protected String expression(String template, Map<String, Object> ctx) {
        return jibFaceService.getExpressionService().prase(template, ctx);
    }

    @Override
    public String name() {
        return IMAGE_BUILD_ACTION;
    }
}