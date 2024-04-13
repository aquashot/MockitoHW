package com.example.demoMockito.Сontrollers;

import com.example.demoMockito.Employee;
import com.example.demoMockito.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("department") Integer department,
                                @RequestParam("salary") Double salary) {

        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam("department") Integer department,
                                   @RequestParam("salary") Double salary) {


        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/search")
    public Employee searchEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("department") Integer department,
                                   @RequestParam("salary") Double salary) {

        return employeeService.searchEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/allEmployes")
    public List<Employee> allEmployes() {
        return employeeService.allEmployes();
    }
}



