package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("statusCode")
    private int statusCode;

    // Default constructor
    public ApiResponse() {
    }

    // Constructor with parameters
    public ApiResponse(boolean success, String message, String token, String userId, int statusCode) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.statusCode = statusCode;
    }

    // Getters and Setters
    public boolean isSuccess() {

        return success;

    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
} 