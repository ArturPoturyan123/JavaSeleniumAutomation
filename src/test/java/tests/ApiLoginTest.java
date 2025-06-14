package tests;

import io.restassured.response.Response;
import models.LoginRequest;
import models.ApiResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.ApiService;

public class ApiLoginTest  {
    
    private ApiService apiService;
    
    @BeforeMethod
    public void setUp() {
        apiService = new ApiService();
    }
    
    @Test(description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Test data
        String email = "arthurp@doublecoconut.com";
        String password = "123456";
        
        // Perform login API call
        Response response = apiService.login(email, password);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(apiService.isResponseSuccessful(response), "Response should be successful");
        
        // Get response as ApiResponse object
        ApiResponse apiResponse = apiService.getApiResponse(response);
        Assert.assertTrue(apiResponse.isSuccess(), "Login should be successful");
        Assert.assertNotNull(apiResponse.getToken(), "Token should not be null");
        Assert.assertNotNull(apiResponse.getUserId(), "User ID should not be null");
        
        System.out.println("Login successful! Token: " + apiResponse.getToken());
        System.out.println("User ID: " + apiResponse.getUserId());
    }
    
    @Test(description = "Test login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        // Test data
        String email = "invalid@email.com";
        String password = "wrongpassword";
        
        // Perform login API call
        Response response = apiService.login(email, password);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 401, "Status code should be 401 for invalid credentials");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        // Get response as ApiResponse object
        ApiResponse apiResponse = apiService.getApiResponse(response);
        Assert.assertFalse(apiResponse.isSuccess(), "Login should not be successful");
        Assert.assertNotNull(apiResponse.getMessage(), "Error message should not be null");
        
        System.out.println("Login failed as expected: " + apiResponse.getMessage());
    }
    
    @Test(description = "Test login with empty email")
    public void testLoginWithEmptyEmail() {
        // Test data
        String email = "";
        String password = "123456";
        
        // Perform login API call
        Response response = apiService.login(email, password);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for empty email");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        System.out.println("Login failed as expected with empty email");
    }
    
    @Test(description = "Test login with empty password")
    public void testLoginWithEmptyPassword() {
        // Test data
        String email = "arthurp@doublecoconut.com";
        String password = "";
        
        // Perform login API call
        Response response = apiService.login(email, password);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for empty password");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        System.out.println("Login failed as expected with empty password");
    }
    
    @Test(description = "Test login using LoginRequest object")
    public void testLoginWithLoginRequestObject() {
        // Create LoginRequest object
        LoginRequest loginRequest = new LoginRequest("arthurp@doublecoconut.com", "123456");
        
        // Perform login API call
        Response response = apiService.login(loginRequest);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(apiService.isResponseSuccessful(response), "Response should be successful");
        
        // Get token from response
        String token = apiService.getTokenFromResponse(response);
        Assert.assertNotNull(token, "Token should not be null");
        
        System.out.println("Login successful using LoginRequest object! Token: " + token);
    }
} 