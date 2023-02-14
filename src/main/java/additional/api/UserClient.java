package additional.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    private static final String POST_NEW_USER = "api/auth/register";
    private static final String POST_LOG_USER = "api/auth/login";
    private static final String DELETE_USER = "api/auth/user";

    public Response createNewUser(JSONUser user) {
        return given()
                .spec(getSpecs())
                .body(user)
                .post(POST_NEW_USER);
    }
    public Response logUser(JSONUser user) {
        return given()
                .spec(getSpecs())
                .body(user)
                .post(POST_LOG_USER);
    }
    public Response deleteUser(String token) {
        return given()
                .spec(getSpecs())
                .header("Authorization", token)
                .delete(DELETE_USER);
    }

    public String getToken(Response response) {
        return response.then().extract().path("accessToken");
    }
}