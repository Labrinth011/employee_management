package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repo.EmployeeRepo;
import com.example.employee_management.util.VarList;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    // EmployeeRepo does the data communication by extending the JPARepository
    // Hence injects the EmployeeRepo so that we can use the JPA methods
    @Autowired // Injects using this annotation
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper; // Note: Remember to add as a bean


    public String saveEmployee(EmployeeDTO employeeDTO){
        // Check if the data is already there in the DB using the EmployeeRepo
        // Can use the JPA methods as EmployeeRepo extends JPARepository
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            return VarList.RSP_DUPLICATED; // Returns the predefined code to the controller and is handled
        }else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO){
        // Checks if the user exists already to update
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        } else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }

    public EmployeeDTO searchEmployee(int employeeID){
        if(employeeRepo.existsById(employeeID)){
            Employee employee = employeeRepo.findById(employeeID).orElse(null);
            return modelMapper.map(employee, EmployeeDTO.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(int employeeID){
        if(employeeRepo.existsById(employeeID)){
            employeeRepo.deleteById(employeeID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
