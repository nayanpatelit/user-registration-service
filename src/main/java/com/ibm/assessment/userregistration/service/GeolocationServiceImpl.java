package com.ibm.assessment.userregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationServiceImpl {
    private static final String BASE_URL_IP_API="https://ipapi.co";
    private static final String URL_SEPARATOR="/";

    @Autowired
    RestTemplate restTemplate;


    public String geolocationPerField(String ipAddress, String fieldName){
      return restTemplate.getForObject(BASE_URL_IP_API+URL_SEPARATOR+ipAddress+URL_SEPARATOR+fieldName,String.class);
    }
}
