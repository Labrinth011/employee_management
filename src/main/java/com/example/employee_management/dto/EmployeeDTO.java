package com.example.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private int empId;
    private String employeeName;
    private String employeeAddress;
    private String employeeMobileNumber;
}
