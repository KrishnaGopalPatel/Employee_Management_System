package com.employeeManagementSystem.Employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employeeManagementSystem.Employee.entity.Employee;
import com.employeeManagementSystem.Employee.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	@GetMapping("/employees")
	public String listEmployee(Model model) {
		model.addAttribute("employees",employeeService.getAllEmployees());
		return "employees";
		
	}
	@GetMapping("/employees/new")
	public String creatEmployeeForm(Model model) {
		Employee employee= new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
	}
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee")Employee employee) {
		employeeService.savEmployee(employee);
		return "redirect:/employees";
	}
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id,Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	@PostMapping("/employees/{id}")
	public String updateemployee(@PathVariable Long id,@ModelAttribute("employee") Employee employee, Model model
) {
		
		Employee existingEmployee = employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setName(employee.getName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setCity(employee.getCity());
		
		employeeService.updateEmployee(existingEmployee);
		
		return "redirect:/employees";
		
	}
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
		
	}
	

}
