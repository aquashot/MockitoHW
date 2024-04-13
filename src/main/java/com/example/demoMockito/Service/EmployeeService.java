package com.example.demoMockito.Service;

import com.example.demoMockito.Employee;
import com.example.demoMockito.Exception.EmployeeAlreadyAddedException;
import com.example.demoMockito.Exception.EmployeeNotFoundException;
import com.example.demoMockito.Exception.EmployeeStorageIsFullException;
import com.example.demoMockito.Exception.MyInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.tomcat.util.http.parser.HttpParser.isAlpha;
@Service
public class EmployeeService {
    private Map<String, Employee> employes;

    public EmployeeService(Map<String, Employee> employes) {
        this.employes = new HashMap<>();
    }


    final static int MAX_EMPLOYES = 10;

    public Employee addEmployee(String firstName, String lastName, int department, double salary) {

        isAlpha(firstName, lastName);
        Employee employee = new Employee(capitalizeFirstName(firstName), capitalizeLastName(lastName), department, salary);
        if (employes.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть");
        }
        if (employes.size() > MAX_EMPLOYES) {
            throw new EmployeeStorageIsFullException("список переполнен");
        }
        employes.put(employee.getFullName(), employee);

        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName, int department, double salary) {
        isAlpha(firstName, lastName);
        Employee employee = new Employee(capitalizeFirstName(firstName), capitalizeLastName(lastName), department, salary);
        if (employes.containsKey(employee.getFullName())) {
            employes.remove(employee.getFullName());

            return employee;
        }
        throw new EmployeeNotFoundException("человек не найден");
    }

    public Employee searchEmployee(String firstName, String lastName, int department, double salary) {
        isAlpha(firstName, lastName);
        Employee employee = new Employee(capitalizeFirstName(firstName), capitalizeLastName(lastName), department, salary);
        if (employes.containsKey(employee.getFullName())) {
            return employes.get(employee.getFullName());

        }

        throw new EmployeeNotFoundException("человек не найден");
    }

    public List<Employee> allEmployes() {
        List<Employee> list = new ArrayList<>();
        list.addAll(employes.values());
        return list;
    }

    public void isAlpha(String firstName, String lastName) {
        if (!org.apache.commons.lang3.StringUtils.isAlpha(firstName) || !org.apache.commons.lang3.StringUtils.isAlpha(lastName)) {
            throw new MyInvalidException();
        }
    }

    public String capitalizeFirstName(String firstName) {
        String firstNameCopy = org.apache.commons.lang3.StringUtils.capitalize(firstName);
        return firstNameCopy;
    }

    public String capitalizeLastName(String lastName) {
        lastName = StringUtils.capitalize(lastName);
        return lastName;
    }
}
