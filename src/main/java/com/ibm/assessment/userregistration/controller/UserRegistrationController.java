package com.ibm.assessment.userregistration.controller;

import com.ibm.assessment.userregistration.dto.UserRequestPayload;
import com.ibm.assessment.userregistration.dto.UserResponse;
import com.ibm.assessment.userregistration.service.GeolocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class UserRegistrationController {
    private static final String IP_API_FIELD_CITY = "city";
    private final GeolocationServiceImpl geolocationService;

    @PostMapping(value = "/user/registration")
    public UserResponse userRegistration(@Valid @RequestBody UserRequestPayload userRequestPayload) {
        String userCity = geolocationService.geolocationPerField(userRequestPayload.getIpAddress(), IP_API_FIELD_CITY);
        String message = String.format("Welcome %s from %s to this beautiful magic world!", userRequestPayload.getUserName(), userCity);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage(message);
        return userResponse;
    }


}
