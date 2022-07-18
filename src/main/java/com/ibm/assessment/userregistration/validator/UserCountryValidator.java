package com.ibm.assessment.userregistration.validator;

import com.ibm.assessment.userregistration.service.GeolocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserCountryValidator implements ConstraintValidator<UserCountryValidation, String> {
    private static final String IP_API_FIELD_COUNTRY_NAME = "country_name";
    private final GeolocationServiceImpl geolocationService;

    @Value("${valid.user.location.countries}")
    String validCountries;

    @Override
    public void initialize(UserCountryValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String userLocation = geolocationService.geolocationPerField(value, IP_API_FIELD_COUNTRY_NAME);
        return isValidCountry(userLocation);
    }

    private boolean isValidCountry(String countryName) {
        //If no valid country list defined, means allowed for all countries
        if (StringUtils.isBlank(validCountries)) {
            return true;
        }
        return Arrays.stream(validCountries.split(",")).anyMatch(x -> x.equalsIgnoreCase(countryName));
    }
}
