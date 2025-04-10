package com.cankus.dto;

import com.cankus.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String firstName;

    @NotBlank(message = "Last Name is a required field")
    @Size(max = 15, min = 2, message = "Last Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "Last Name must starts with Uppercase character")
    private String lastName;

    @Email(message = "UserName is a required field")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Provide a suitable email address ex: michael23jordan23@test.com")
    private String userName;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Contains at least one digit.\n" +
                    "    Contains at least one lowercase letter.\n" +
                    "    Contains at least one uppercase letter.\n" +
                    "    Contains at least one special character from the provided set.\n" +
                    "    Does not contain any whitespace.\n" +
                    "    Is at least 8 characters long.")
    private String password;

    @NotEmpty(message = "ConfirmPassword is a required field")
    private String confirmPassword;

    @NotNull(message = "Please select a role")
    private RoleDto role;

    @NotNull(message = "Please select a Gender")
    private Gender gender;

    @Valid
    @NotNull
    private AddressDto address;
}