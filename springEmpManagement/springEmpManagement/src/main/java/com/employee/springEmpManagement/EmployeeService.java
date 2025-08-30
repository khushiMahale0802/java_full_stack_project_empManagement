package com.employee.springEmpManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, entity);
        EmployeeEntity saved = employeeRepository.save(entity);

        Employee savedDto = new Employee();
        BeanUtils.copyProperties(saved, savedDto);
        return savedDto;
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).orElse(null);
        if (entity == null) return null;
        Employee dto = new Employee();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> entities = employeeRepository.findAll();
        List<Employee> list = new ArrayList<>();
        for (EmployeeEntity e : entities) {
            Employee dto = new Employee();
            BeanUtils.copyProperties(e, dto);
            list.add(dto);
        }
        return list;
    }

    @Override
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted from DB";
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existing = employeeRepository.findById(id).orElse(null);
        if (existing == null) return "Employee with ID " + id + " not found!";
        existing.setName(employee.getName());
        existing.setPhone(employee.getPhone());
        existing.setEmail(employee.getEmail());
        employeeRepository.save(existing);
        return "Updated Successfully!";
    }
}
