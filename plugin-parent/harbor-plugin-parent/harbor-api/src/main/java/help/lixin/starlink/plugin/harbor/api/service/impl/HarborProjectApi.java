package help.lixin.starlink.plugin.harbor.api.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.plugin.harbor.api.dto.CreateProject;
import help.lixin.starlink.plugin.harbor.api.dto.Project;
import help.lixin.starlink.plugin.harbor.api.dto.TotalPageDTO;
import help.lixin.starlink.plugin.harbor.api.dto.repository.ArtifactDTO;
import help.lixin.starlink.plugin.harbor.api.dto.repository.RepositoriesDTO;
import help.lixin.starlink.plugin.harbor.api.properties.HarborProperties;
import help.lixin.starlink.plugin.harbor.api.service.IHarborProjectApi;
import kong.unirest.*;

public class HarborProjectApi implements IHarborProjectApi {
    private HarborProperties properties;
    private static final String API_RESOURCE = "/api/v2.0";
    private static final String PROJECTS = API_RESOURCE + "/projects";
    private static final String STATISTICS = API_RESOURCE + "/statistics";

    private static final String PROJECT_ID = "/{project_id}";

    {
        Unirest.config().enableCookieManagement(false);
    }

    public HarborProjectApi(HarborProperties properties) {
        this.properties = properties;
    }

    /**
     * @Param projectName :
     * @Author: 伍岳林
     * @Date: 2023/6/1 4:09 下午
     * @Return: java.lang.Boolean
     * @Description 查询项目名称是否存在，存在则返回true
     */
    @Override
    public Boolean checkProjectNameIsExist(String projectName) {

        GetRequest request = Unirest.head(properties.getUrl() + PROJECTS)
            //
            .basicAuth(properties.getUserName(), properties.getPassword())
            //
            .queryString("project_name", projectName);
        HttpResponse<String> response = request.asString();
        return response.getStatus() == 200;
    }

    @Override
    public List<Project> query(int page, int pageSize, String name, Integer publicFlag) {
        page = page <= 0 ? 1 : page;
        pageSize = pageSize < 10 ? 10 : pageSize;
        GetRequest getRequest = Unirest.get(properties.getUrl() + PROJECTS);
        GetRequest request = getRequest
            //
            .basicAuth(properties.getUserName(), properties.getPassword())
            //
            .queryString("page", page)
            //
            .queryString("page_size", pageSize);

        if (StringUtils.isNotBlank(name)) {
            getRequest.queryString("name", name);
        }
        if (publicFlag != null) {
            getRequest.queryString("public", publicFlag);
        }
        HttpResponse<String> response = request.asString();
        // 报文里居然有java关键字的参数.
        String body = response.getBody().replaceAll("public", "isPublic");
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        List<Project> projects = objectMapper.readValue(body, new GenericType<List<Project>>() {});
        return projects;
    }

    @Override
    public TotalPageDTO totalPage() {
        GetRequest request =
            Unirest.get(properties.getUrl() + STATISTICS).basicAuth(properties.getUserName(), properties.getPassword());
        HttpResponse<String> response = request.asString();
        // 报文里居然有java关键字的参数.
        String body = response.getBody();
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        return objectMapper.readValue(body, new GenericType<TotalPageDTO>() {});
    }

    @Override
    public void create(CreateProject project) {
        if (checkProjectNameIsExist(project.getProject_name())) {
            throw new RuntimeException("项目名已存在");
        }
        HttpRequestWithBody requestWithBody =
            //
            Unirest.post(properties.getUrl() + PROJECTS)
                //
                .header("Content-Type", "application/json")
                //
                .basicAuth(properties.getUserName(), properties.getPassword());
        if (null != project) {
            JsonObjectMapper objectMapper = new JsonObjectMapper();
            String jsonProject = objectMapper.writeValue(project)
                //
                .replaceAll("isPublic", "public");
            HttpResponse<String> response = requestWithBody.body(jsonProject).asString();
            if (response.getStatus() != 201) {
                throw new RuntimeException("harbor创建项目出现异常:" + response.getBody());
            }
        }
    }

    @Override
    public void delete(Long projectId) {
        HttpRequestWithBody httpRequestWithBody =
            //
            Unirest.delete(properties.getUrl() + PROJECTS + PROJECT_ID)
                //
                .basicAuth(properties.getUserName(), properties.getPassword())
                //
                .routeParam("project_id", String.valueOf(projectId));
        HttpResponse<String> response = httpRequestWithBody.asString();
        if (response.getStatus() != 200) {
            throw new RuntimeException("harbor删除项目出现异常:" + response.getBody());
        }
    }

    @Override
    public List<RepositoriesDTO> queryRepositories(String projectName) {
        String url = properties.getUrl() + PROJECTS + "/" + projectName + "/repositories";
        HttpResponse<JsonNode> result = Unirest.get(url)
            //
            .basicAuth(properties.getUserName(), properties.getPassword())
            //
            .queryString("page_size", 100).queryString("page", 1).asJson();
        int status = result.getStatus();
        JsonNode jsonBody = result.getBody();
        if (status != 200) {
            throw new RuntimeException("HarborProeject-queryRepositories调用失败:" + result);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonBody.toString(), new TypeReference<List<RepositoriesDTO>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("HarborProeject-queryRepositories数据转换发生异常:" + result);
        }

    }

    @Override
    public List<ArtifactDTO> queryImages(String projectName, String repostoryName) {
        String url =
            properties.getUrl() + PROJECTS + "/" + projectName + "/repositories/" + repostoryName + "/artifacts";
        HttpResponse<JsonNode> result = Unirest.get(url)
            //
            .basicAuth(properties.getUserName(), properties.getPassword()).queryString("with_tag", true)
            .queryString("with_scan_overview", true).queryString("with_label", true).queryString("page_size", 100)
            .queryString("page", 1).asJson();
        int status = result.getStatus();
        JsonNode jsonBody = result.getBody();
        if (status != 200) {
            throw new RuntimeException("HarborProeject-queryImages调用失败:" + result);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonBody.toString(), new TypeReference<List<ArtifactDTO>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("HarborProeject-queryImages数据转换出现异常:" + result);
        }
    }

    @Override
    public Boolean changeAccessLevel(Long harborProjectId) {
        String url = properties.getUrl() + PROJECTS + "/" + harborProjectId;
        HttpResponse<JsonNode> result = Unirest.get(url)
            //
            .basicAuth(properties.getUserName(), properties.getPassword())
            //
            .asJson();
        int status = result.getStatus();
        if (status != 200) {
            throw new RuntimeException("HarborProeject-queryImages调用失败:" + result);
        }
        return true;
    }
}
