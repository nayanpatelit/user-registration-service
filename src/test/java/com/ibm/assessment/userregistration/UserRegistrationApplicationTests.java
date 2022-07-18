package com.ibm.assessment.userregistration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.assessment.userregistration.controller.UserRegistrationController;
import com.ibm.assessment.userregistration.dto.UserRequestPayload;
import com.ibm.assessment.userregistration.dto.UserResponse;
import com.ibm.assessment.userregistration.service.GeolocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRegistrationController.class)
@WebAppConfiguration
@TestPropertySource(properties = {"valid.user.location.countries=United States,"},
        locations = "classpath:application.properties"
)
class UserRegistrationApplicationTests {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GeolocationServiceImpl geolocationService;

    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void userRegistration_Return_Success() throws Exception {
        String url = "/user/registration";
        UserRequestPayload userRequestPayload = new UserRequestPayload();
        userRequestPayload.setIpAddress("8.8.8.8");
        userRequestPayload.setUserName("test");
        userRequestPayload.setPassword("Test123456$");

        Mockito.when(geolocationService.geolocationPerField("8.8.8.8", "country_name")).thenReturn("United States");
        Mockito.when(geolocationService.geolocationPerField("8.8.8.8", "city")).thenReturn("Boston");


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(userRequestPayload))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, Integer.valueOf(200));
        UserResponse userResponse = mapToPojo(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        assertEquals(userResponse.getMessage(), "Welcome test from Boston to this beautiful magic world!");
    }

    @Test
    public void userRegistration_Null_Filed_Failure() throws Exception {
        String url = "/user/registration";
        UserRequestPayload userRequestPayload = new UserRequestPayload();
        userRequestPayload.setIpAddress("");
        userRequestPayload.setUserName("test");
        userRequestPayload.setPassword("Test123456$");

        Mockito.when(geolocationService.geolocationPerField("8.8.8.8", "country_name")).thenReturn("United States");
        Mockito.when(geolocationService.geolocationPerField("8.8.8.8", "city")).thenReturn("Boston");


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(userRequestPayload))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(status, Integer.valueOf(400));
        //ErrorResponse errorResponse = mapToPojo(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        //assertTrue(errorResponse.getValidationList().get(0).equalsIgnoreCase("IP Address must not be null or empty"));
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    private <T> T mapToPojo(String jsonResponse, Class<T> tClass) throws JsonProcessingException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, tClass);
    }

}
