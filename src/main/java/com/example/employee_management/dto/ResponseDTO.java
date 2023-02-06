package com.example.employee_management.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// To handle responses
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    // Sends a response code to the frontend
    private String code;
    private String message;
    private Object content;
}
