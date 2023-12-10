package com.app.clubmatrix.services;

import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.models.User;
import com.app.clubmatrix.repositories.EmployeeRepository;
import com.app.clubmatrix.services.dto.EmployeeRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    public Employee registerEmployee(EmployeeRegistrationDTO employeeDTO) {
        User newUser = userService.createUser(employeeDTO.getCredentials());

        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setAddress(employeeDTO.getAddress());
        newEmployee.setPhone(employeeDTO.getPhone());
        newEmployee.setEmail(employeeDTO.getEmail());
        newEmployee.setPosition(employeeDTO.getPosition());
        newEmployee.setSalary(employeeDTO.getSalary());
        newEmployee.setUser(newUser);

        try {
            return employeeRepository.save(newEmployee);
        } catch (Exception e) {
            userService.deleteUser(newUser);

            throw e;
        }
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}