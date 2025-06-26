package reqres;

import models.*;
import retrofit2.Call;
import retrofit2.http.*;

import static reqres.ReqresAPI.*;

public interface ReqresServices {

    String BASE_URL = ReqresAPI.BASE_URL;

    @GET(USERS_SUFFIX)
    Call<UsersListResponse> getUsers(
            @Query("page") String page
    );

    @GET(USERS_SUFFIX + ID_SUFFIX)
    Call<GetUserResponse> getUser(
            @Path("id") String id
    );

    @POST(LOGIN_SUFFIX)
    Call<LoginResponse> login(
            @Body UserLoginRequest userLoginRequest
    );

    @PUT(USERS_SUFFIX + ID_SUFFIX)
    Call<UpdateUserResponse> updateUser(
            @Body GenericUserRequest genericUserRequest,
            @Path("id") String id
    );

    @DELETE(USERS_SUFFIX + ID_SUFFIX)
    Call<Void> deleteUser(
            @Path("id") String id
    );

    @POST(USERS_SUFFIX)
    Call<CreateUserResponse> createDelayedUser(
            @Body GenericUserRequest genericUserRequest,
            @Query("delay") String delay
    );

}
