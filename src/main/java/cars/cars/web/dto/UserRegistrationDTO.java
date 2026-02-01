package cars.cars.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @Size(min = 2, max = 30)
    private String fullName;

    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 5, max = 12, message = "Username must be between 5 and 12 symbols")
    private String username;

    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 symbols")
    private String password;

    private String confirmPassword;
}
