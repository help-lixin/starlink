package help.lixin.harbor.service.impl;

import help.lixin.harbor.dto.CreateProject;
import help.lixin.harbor.model.Project;
import help.lixin.harbor.properties.HarborProperties;
import help.lixin.harbor.service.IProjectService;
import kong.unirest.*;

import java.util.List;

public class ProjectService implements IProjectService {
    private HarborProperties properties;
    private static final String RESOURCE = "/projects";
    private static final String PROJECT_ID = "/{project_id}";

    public ProjectService(HarborProperties properties) {
        this.properties = properties;
    }


    @Override
    public List<Project> query(int page, int pageSize) {
        if (page <= 0) {
            page = 1;
        }
        if (pageSize < 10) {
            pageSize = 10;
        }

        GetRequest request = Unirest.get(properties.getUrl() + RESOURCE)
                //
                .basicAuth(properties.getUserName(), properties.getPassword())
                //
                .queryString("page", page)
                //
                .queryString("page_size", pageSize);

        HttpResponse<String> response = request.asString();
        // 报文里居然有java关键字的参数.
        String body = response.getBody().replaceAll("public", "isPublic");
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        List<Project> projects = objectMapper.readValue(body, new GenericType<List<Project>>() {
        });
        return projects;
    }

    @Override
    public void create(CreateProject project) {
        HttpRequestWithBody requestWithBody =
                //
                Unirest.post(properties.getUrl() + RESOURCE)
                        //
                        .header("Content-Type", "application/json")
                        //
                        .basicAuth(properties.getUserName(), properties.getPassword());
        if (null != project) {
            ObjectMapper objectMapper = new JsonObjectMapper();
            String jsonProject = objectMapper.writeValue(project)
                    //
                    .replaceAll("isPublic", "public");
            HttpResponse<String> response = requestWithBody.body(jsonProject).asString();
            if (response.getStatus() != 201) {
                // TODO lixin
                // Exception
            }
        }
    }

    @Override
    public void delete(Integer projectId) {
        HttpRequestWithBody httpRequestWithBody =
                //
                Unirest.delete(properties.getUrl() + RESOURCE + PROJECT_ID)
                        //
                        .basicAuth(properties.getUserName(), properties.getPassword())
                        //
                        .routeParam("project_id", String.valueOf(projectId));
        HttpResponse<String> response = httpRequestWithBody.asString();
        if (response.getStatus() != 200) {
            // TODO lixin
        }
    }
}
