package com.ibm.assessment.userregistration.dto;

import com.ibm.assessment.userregistration.validator.UserCountryValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequestPayload {
    private static final String NOT_NULL_MESSAGE = " must not be null or empty";
    private static final String PASSWORD_SIZE_MESSAGE = "Password must be greater than 8 characters";
    private static final String PASSWORD_CHAR_CONSTRAINTS_MESSAGE = "Password must contain at least 1 number,1 capitalized letter,1 special character in this set _#$%.";
    private static final String PASWD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[_#$%.])[A-Za-z\\d_#$%.]+$";
    @NotBlank(message = "UserName" + NOT_NULL_MESSAGE)
    private String userName;

    @NotBlank(message = "Password" + NOT_NULL_MESSAGE)
    @Size(min = 9, message = PASSWORD_SIZE_MESSAGE)
    @Pattern(regexp = PASWD_PATTERN, message = PASSWORD_CHAR_CONSTRAINTS_MESSAGE)
    private String password;

    @NotBlank(message = "IP Address" + NOT_NULL_MESSAGE)
    @UserCountryValidation
    private String ipAddress;

}
