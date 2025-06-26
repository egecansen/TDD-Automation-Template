package reqres;

import api_assured.ApiUtilities;
import api_assured.ResponsePair;
import api_assured.ServiceGenerator;
import models.*;
import retrofit2.Call;
import retrofit2.Response;

public class Reqres extends ApiUtilities {

    static ReqresServices reqresServices = new ServiceGenerator().generate(ReqresServices.class);

    public UsersListResponse getUserList(String page) {
        log.info("Acquiring list of users from page number " + page);
        Call<UsersListResponse> getUsersListCall = reqresServices.getUsers(page);
        return perform(getUsersListCall, true, true);
    }

    public GetUserResponse getUser(String id) {
        log.info("Acquiring the user with id " + id);
        Call<GetUserResponse> getUserCall = reqresServices.getUser(id);
        return perform(getUserCall, true, true);
    }

    public ResponsePair<Response<LoginResponse>, LoginErrorResponse> login(UserLoginRequest userLoginRequest) {
        log.info("Logging in with " + userLoginRequest.getEmail());
        Call<LoginResponse> registerCall = reqresServices.login(userLoginRequest);
        return getResponse(registerCall, false, true, LoginErrorResponse.class);
    }

    public UpdateUserResponse updateUser(GenericUserRequest genericUserRequest, String id) {
        log.info("Updating the user with id " + id);
        Call<UpdateUserResponse> updateUserCall = reqresServices.updateUser(genericUserRequest, id);
        return perform(updateUserCall, true, true);
    }

    public void deleteUser(String id) {
        log.info("Deleting the user with id " + id);
        Call<Void> deleteUserCall = reqresServices.deleteUser(id);
        perform(deleteUserCall, true, true);
    }

    public CreateUserResponse createDelayedUser(GenericUserRequest genericUserRequest, String delay) {
        log.info("Creating the user...");
        Call<CreateUserResponse> createUserCall = reqresServices.createDelayedUser(genericUserRequest, delay);
        return perform(createUserCall, true, true);
    }

}
