package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.RegisterRequest;
import models.ApiResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.ApiService;

public class ApiRegisterTest extends BaseTest {
    
    private ApiService apiService;
    
    @BeforeMethod
    public void setUp() {
        apiService = new ApiService();
    }
    
    @Test(description = "Test successful registration with valid data")
    public void testSuccessfulRegistration() {
        // Test data - using unique email to avoid conflicts
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "TestPassword123!";
        String firstName = "Test";
        String lastName = "User";
        String phone = "+37412345678";
        
        // Perform registration API call
        Response response = apiService.register(email, password, firstName, lastName, phone);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201 for successful registration");
        Assert.assertTrue(apiService.isResponseSuccessful(response), "Response should be successful");
        
        // Get response as ApiResponse object
        ApiResponse apiResponse = apiService.getApiResponse(response);
        Assert.assertTrue(apiResponse.isSuccess(), "Registration should be successful");
        Assert.assertNotNull(apiResponse.getUserId(), "User ID should not be null");
        
        System.out.println("Registration successful! User ID: " + apiResponse.getUserId());
        System.out.println("Email: " + email);
    }
    
    @Test(description = "Test registration with existing email")
    public void testRegistrationWithExistingEmail() {
        // Test data - using existing email
        String email = "arthurp@doublecoconut.com";
        String password = "TestPassword123!";
        String firstName = "Test";
        String lastName = "User";
        String phone = "+37412345678";
        
        // Perform registration API call
        Response response = apiService.register(email, password, firstName, lastName, phone);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 409, "Status code should be 409 for existing email");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        // Get response as ApiResponse object
        ApiResponse apiResponse = apiService.getApiResponse(response);
        Assert.assertFalse(apiResponse.isSuccess(), "Registration should not be successful");
        Assert.assertNotNull(apiResponse.getMessage(), "Error message should not be null");
        
        System.out.println("Registration failed as expected: " + apiResponse.getMessage());
    }
    
    @Test(description = "Test registration with invalid email format")
    public void testRegistrationWithInvalidEmail() {
        // Test data - invalid email format
        String email = "invalid-email";
        String password = "TestPassword123!";
        String firstName = "Test";
        String lastName = "User";
        String phone = "+37412345678";
        
        // Perform registration API call
        Response response = apiService.register(email, password, firstName, lastName, phone);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for invalid email");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        System.out.println("Registration failed as expected with invalid email");
    }
    
    @Test(description = "Test registration with weak password")
    public void testRegistrationWithWeakPassword() {
        // Test data - weak password
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "123"; // Weak password
        String firstName = "Test";
        String lastName = "User";
        String phone = "+37412345678";
        
        // Perform registration API call
        Response response = apiService.register(email, password, firstName, lastName, phone);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for weak password");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        System.out.println("Registration failed as expected with weak password");
    }
    
    @Test(description = "Test registration with empty required fields")
    public void testRegistrationWithEmptyFields() {
        // Test data - empty required fields
        String email = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String phone = "";
        
        // Perform registration API call
        Response response = apiService.register(email, password, firstName, lastName, phone);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for empty fields");
        Assert.assertFalse(apiService.isResponseSuccessful(response), "Response should not be successful");
        
        System.out.println("Registration failed as expected with empty fields");
    }
    
    @Test(description = "Test registration using RegisterRequest object")
    public void testRegistrationWithRegisterRequestObject() {
        // Create RegisterRequest object
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        RegisterRequest registerRequest = new RegisterRequest(
            email, 
            "TestPassword123!", 
            "Test", 
            "User", 
            "+37412345678"
        );
        
        // Perform registration API call
        Response response = apiService.register(registerRequest);
        
        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Assert.assertTrue(apiService.isResponseSuccessful(response), "Response should be successful");
        
        // Get user ID from response
        String userId = apiService.getUserIdFromResponse(response);
        Assert.assertNotNull(userId, "User ID should not be null");
        
        System.out.println("Registration successful using RegisterRequest object! User ID: " + userId);
        System.out.println("Email: " + email);
    }
} 