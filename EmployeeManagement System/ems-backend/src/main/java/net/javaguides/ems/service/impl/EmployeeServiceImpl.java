package net.javaguides.ems.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepositroy;
import net.javaguides.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	private EmployeeRepositroy employeeRepositroy;
	
	
	public EmployeeServiceImpl(EmployeeRepositroy employeeRepositroy) {
		super();
		this.employeeRepositroy = employeeRepositroy;
	}

    
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		// convert EmployeeDto into employee jpa entity
		Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee =employeeRepositroy.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}


	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepositroy.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee is not exists :"+employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}


	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees=employeeRepositroy.findAll();
		return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepositroy.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
				);
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		
		Employee updateEmployeeObj=employeeRepositroy.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
	}


	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepositroy.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
				);
		employeeRepositroy.deleteById(employeeId);
		
	}

}
