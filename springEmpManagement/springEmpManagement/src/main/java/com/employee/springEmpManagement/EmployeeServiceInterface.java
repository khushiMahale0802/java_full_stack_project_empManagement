package com.employee.springEmpManagement;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee createEmployee(Employee employee);
    Employee readEmployee(Long id);
    List<Employee> readEmployees();
    String deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
}
