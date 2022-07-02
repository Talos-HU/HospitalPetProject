package com.talos.hospital.Controller;

import com.talos.hospital.Model.Entity.Employee;
import com.talos.hospital.Service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class EmployeeControllerTML {

    private final EmployeeService employeeService;

    public EmployeeControllerTML(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {"/", "/listAllEmployees"})
    public ModelAndView listAllEmployees() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Employee> employeeList = employeeService.findAllEmployeesTML();
        modelAndView.addObject("employees", employeeList);
        return modelAndView;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee employee = new Employee();
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(Employee employee, Model model) {
        employeeService.saveEmployeeTML(employee);
        model.addAttribute(employee);
        return "redirect:listAllEmployees";
    }


    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam UUID employeeId) {
        ModelAndView modelAndView = new ModelAndView("update-employee-form");
        Employee employee = employeeService.findEmployeeByIdTML(employeeId);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam UUID employeeId) {
        employeeService.deleteEmployeeByIdTML(employeeId);
        return "redirect:/listAllEmployees";
    }
}
