package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.LoginRequest;
import models.RegisterRequest;
import models.ApiResponse;
import utils.ConfigReader;

public class ApiService {

    private static final String BASE_URL = ConfigReader.get("apiBaseUrl");
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";

    private RequestSpecification requestSpec;

    public ApiService() {
        RestAssured.baseURI = BASE_URL;
        requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    /**
     * Login API call
     *
     * @param email    user email
     * @param password user password
     * @return Response object
     */
    public Response login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        return requestSpec
                .body(loginRequest)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Login API call with LoginRequest object
     *
     * @param loginRequest LoginRequest object
     * @return Response object
     */
    public Response login(LoginRequest loginRequest) {
        return requestSpec
                .body(loginRequest)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Register API call
     *
     * @param email     user email
     * @param password  user password
     * @param firstName user first name
     * @param lastName  user last name
     * @param phone     user phone number
     * @return Response object
     */
    public Response register(String email, String password, String firstName, String lastName, String phone) {
        RegisterRequest registerRequest = new RegisterRequest(email, password, firstName, lastName, phone);

        return requestSpec
                .body(registerRequest)
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Register API call with RegisterRequest object
     *
     * @param registerRequest RegisterRequest object
     * @return Response object
     */
    public Response register(RegisterRequest registerRequest) {
        return requestSpec
                .body(registerRequest)
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get token from login response
     *
     * @param response Response object
     * @return token string
     */
    public String getTokenFromResponse(Response response) {
        return response.jsonPath().getString("token");
    }

    /**
     * Get user ID from response
     *
     * @param response Response object
     * @return user ID string
     */
    public String getUserIdFromResponse(Response response) {
        return response.jsonPath().getString("userId");
    }

    /**
     * Check if response is successful
     *
     * @param response Response object
     * @return boolean
     */
    public boolean isResponseSuccessful(Response response) {
        return response.getStatusCode() >= 200 && response.getStatusCode() < 300;
    }

    /**
     * Get API response as ApiResponse object
     *
     * @param response Response object
     * @return ApiResponse object
     */
    public ApiResponse getApiResponse(Response response) {
        return response.as(ApiResponse.class);
    }
} 