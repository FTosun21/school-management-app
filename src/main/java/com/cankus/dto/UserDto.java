package com.cankus.dto;

import com.cankus.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


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

    public UserDto() {
    }


    public UserDto(Long id, String firstName, String lastName, String password, String userName, String confirmPassword, RoleDto role, Gender gender, AddressDto address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userName = userName;
        this.confirmPassword = confirmPassword;
        this.role = role;
        this.gender = gender;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "First Name is a required field") @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long") @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First Name is a required field") @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long") @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last Name is a required field") @Size(max = 15, min = 2, message = "Last Name must be between 2 and 15 characters long") @Pattern(regexp = "[A-Z]\\w*", message = "Last Name must starts with Uppercase character") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last Name is a required field") @Size(max = 15, min = 2, message = "Last Name must be between 2 and 15 characters long") @Pattern(regexp = "[A-Z]\\w*", message = "Last Name must starts with Uppercase character") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "UserName is a required field") @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Provide a suitable email address ex: michael23jordan23@test.com") String getUserName() {
        return userName;
    }

    public void setUserName(@Email(message = "UserName is a required field") @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Provide a suitable email address ex: michael23jordan23@test.com") String userName) {
        this.userName = userName;
    }

    public @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Contains at least one digit.\n" +
                    "    Contains at least one lowercase letter.\n" +
                    "    Contains at least one uppercase letter.\n" +
                    "    Contains at least one special character from the provided set.\n" +
                    "    Does not contain any whitespace.\n" +
                    "    Is at least 8 characters long.") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Contains at least one digit.\n" +
                    "    Contains at least one lowercase letter.\n" +
                    "    Contains at least one uppercase letter.\n" +
                    "    Contains at least one special character from the provided set.\n" +
                    "    Does not contain any whitespace.\n" +
                    "    Is at least 8 characters long.") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "ConfirmPassword is a required field") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotEmpty(message = "ConfirmPassword is a required field") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @NotNull(message = "Please select a role") RoleDto getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Please select a role") RoleDto role) {
        this.role = role;
    }

    public @NotNull(message = "Please select a Gender") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Please select a Gender") Gender gender) {
        this.gender = gender;
    }

    public @Valid @NotNull AddressDto getAddress() {
        return address;
    }

    public void setAddress(@Valid @NotNull AddressDto address) {
        this.address = address;
    }
}