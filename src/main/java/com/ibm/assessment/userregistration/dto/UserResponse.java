package com.ibm.assessment.userregistration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID uuid = UUID.randomUUID();
    private String message;
}
