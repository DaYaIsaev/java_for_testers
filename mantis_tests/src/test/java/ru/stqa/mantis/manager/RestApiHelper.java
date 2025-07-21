package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.stqa.mantis.model.IssueDate;
import ru.stqa.mantis.model.UserDate;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));

    }


    public void createIssue(IssueDate issueDate) {
        Issue issue = new Issue();
        issue.setSummary(issueDate.summary());
        issue.setDescription(issueDate.description());
        var projectId = new Identifier();
        projectId.setId(issueDate.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueDate.category());
        issue.setCategory(categoryId);
        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public void createUser (UserDate user) {
        UserApi userApi = new UserApi();
        User body = new User(); // User | The user to add.
        body.setUsername(user.username());
        body.setEmail(user.email());
        body.setPassword(user.password());
        body.setProtected(false);
        body.setEnabled(true);
        body.setRealName("name5");
        //body.setAccessLevel();
        try {
            UserAddResponse result = userApi.userAdd(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userAdd");
            e.printStackTrace();
        }
    }



}
