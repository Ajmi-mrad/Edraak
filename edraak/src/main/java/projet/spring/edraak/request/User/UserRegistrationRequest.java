package projet.spring.edraak.request.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
        @Email
        @NotBlank
        String email,

        @Size(min = 8, message = "Password must be at least 8 characters")
        @NotBlank
        String password
){
    public UserRegistrationRequest {
    }
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}