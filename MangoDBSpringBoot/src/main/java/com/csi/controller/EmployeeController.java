package com.csi.controller;

import com.csi.exception.RecordNotFoundExeption;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PostMapping("/saveall")
    public List<Employee> saveAll(@RequestBody List<Employee> employeeList){
        return employeeService.saveall(employeeList);
    }

    @GetMapping("/findbyid/{empId}")
    public Optional<Employee> findById(@PathVariable int empId){
        return Optional.ofNullable(employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundExeption("Record Is Not Available Into the DB")));
    }

    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PutMapping("/update/{empId}")
    public Employee update(@PathVariable int empId,@RequestBody Employee employee){
        Employee employee1 = findById(empId).orElseThrow(()->new RecordNotFoundExeption("ID not exist to update"));
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee1.getEmpAddress());
        return employeeService.update(employee1);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Employee Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteAll();
        return ResponseEntity.ok("All Data deleted Successfully");
    }
}
