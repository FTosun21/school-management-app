package com.cankus.dto;

import com.cankus.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class AddressDto {

    @NotBlank(message = "Address is a required field")
    @Size(max = 50, min = 10, message = "Address must be between 10 and 50 characters long")
    private String addressInfo;

    @NotNull(message = "Please select a state")
    private State state;

    @NotNull(message = "Phone number is a required field")
    @Pattern(regexp = "^\\+1 \\(\\d{3}\\) \\d{3}-\\d{4}$", message = "USA phone numbers in the format +1 (XXX) XXX-XXXX")
    private String phoneNumber;

    public AddressDto(){}
    public AddressDto(String addressInfo, State state, String phoneNumber) {
        this.addressInfo = addressInfo;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}