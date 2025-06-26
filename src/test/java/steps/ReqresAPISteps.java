package steps;

import api_assured.ResponsePair;
import context.ContextStore;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import models.*;
import org.junit.Assert;
import reqres.Reqres;
import retrofit2.Response;
import utils.Printer;
import utils.StringUtilities;
import utils.arrays.ArrayUtilities;
import java.util.Map;

public class ReqresAPISteps {

    Printer log = new Printer(ReqresAPISteps.class);
    Reqres reqres = new Reqres();

    @Given("Get user list from page {}")
    public void getUserList(String page) {
        UsersListResponse usersListResponse = reqres.getUserList(page);
        ContextStore.put("usersListResponse", usersListResponse);
        GetUserResponse.Data randomUser = ArrayUtilities.getRandomItemFrom(usersListResponse.getData());
        ContextStore.put("randomUserEmail", randomUser.getEmail());
        ContextStore.put("randomUserId", randomUser.getId().toString());
        log.success("Users list is acquired successfully");
    }

    @Given("Get user with id {}")
    public void getUser(String id) {
        id = StringUtilities.contextCheck(id);
        GetUserResponse getUserResponse = reqres.getUser(id);
        ContextStore.put("getUserResponse", getUserResponse);
        log.success("Users is acquired successfully");
    }

    @Given("Verify the page number is {} on the response")
    public void validatePage(String page) {
        UsersListResponse usersListResponse = ContextStore.get("usersListResponse");
        Assert.assertEquals("The page number does not page", usersListResponse.getPage().toString(), page);
        log.success("Users list page number is verified as " + page);
    }

    @Given("Login with the following user")
    public void loginUser(DataTable table) {
        Map<String, String> tableMap = table.asMap();
        String email = tableMap.get("email");
        String password = tableMap.get("password");
        email = StringUtilities.contextCheck(email);

        UserLoginRequest userLoginRequest = new UserLoginRequest(
                email,
               password
        );

        ResponsePair<Response<LoginResponse>, LoginErrorResponse> loginResponsePair = reqres.login(userLoginRequest);
        ContextStore.put("loginResponse", loginResponsePair.response().body());
        ContextStore.put("loginErrorResponse", loginResponsePair.errorBody());
        log.success("Successfully logged in");
    }

    @Given("Verify the login token is not null")
    public void verifyTheTokenIsNotNull() {
        LoginResponse loginResponse = ContextStore.get("loginResponse");
        Assert.assertNotNull("Login token is null!", loginResponse.getToken());
        log.success("The token is verified as not null");
        log.info("Token: " + loginResponse.getToken());
    }

    @Given("Update user with id {} with following")
    public void updateUser(String id, DataTable table) {
        id = StringUtilities.contextCheck(id);
        Map<String, String> tableMap = table.asMap();
        String name = tableMap.get("name");
        String job = tableMap.get("job");
        GenericUserRequest genericUserRequest = new GenericUserRequest(
                name,
                job
        );
        UpdateUserResponse updateUserResponse = reqres.updateUser(genericUserRequest, id);
        ContextStore.put("updateUserResponse", updateUserResponse);
        ContextStore.put("updateUserName", name);
        ContextStore.put("updateUserJob", job);
        log.success("Users is updated successfully");
    }

    @Given("Verify the update user response body")
    public void verifyUpdateResponse() {
        UpdateUserResponse updateUserResponse = ContextStore.get("updateUserResponse");
        Assert.assertEquals("The user name does not match", updateUserResponse.getName(), ContextStore.get("updateUserName"));
        log.success("The updated user name verified as " + ContextStore.get("updateUserName"));
        Assert.assertEquals("The user job does not match", updateUserResponse.getJob(), ContextStore.get("updateUserJob"));
        log.success("The updated user job verified as " + ContextStore.get("updateUserJob"));
        Assert.assertNotNull("updatedAt is null!", updateUserResponse.getUpdatedAt());
        log.success("The updatedAt verified as not null");
    }

    @Given("Delete user with id {}")
    public void deleteUser(String id) {
        id = StringUtilities.contextCheck(id);
        reqres.deleteUser(id);
        log.success("User is deleted successfully");
    }

    @Given("Create and verify user with {} seconds delay with following:")
    public void createDelayedUser(String seconds, DataTable table) {
        Map<String, String> tableMap = table.asMap();
        String name = tableMap.get("name");
        String job = tableMap.get("job");
        GenericUserRequest genericUserRequest = new GenericUserRequest(name, job);

        long start = System.currentTimeMillis();
        CreateUserResponse userResponseModel = reqres.createDelayedUser(genericUserRequest, seconds);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        log.info("Elapsed time: " + timeElapsed);

        log.success("User is created successfully");
        Assert.assertEquals("Name does not match!", userResponseModel.getName(), name);
        log.info("Name is verified as " + name);
        Assert.assertEquals("Job does not match!",userResponseModel.getJob(), job);
        log.info("Job is verified as " + job);

    }

    @Given("Verify the error message is: {}")
    public void verifyTheLoginError(String errorMessage) {
        LoginErrorResponse loginErrorResponse = ContextStore.get("loginErrorResponse");
        Assert.assertEquals("Error message does not match!", errorMessage, loginErrorResponse.getError());
        log.success("The errror message is verified as " + errorMessage);
    }

}
