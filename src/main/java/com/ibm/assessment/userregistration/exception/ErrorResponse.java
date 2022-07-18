package com.ibm.assessment.userregistration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String exMessage;
    private List<String> validationList;
}
