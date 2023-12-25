package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.Model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO mapToDTO(Employee Employee);
    List<EmployeeDTO> mapToDTO(List<Employee> Employees);

    Employee mapToEmployee(EmployeeDTO employeeDTO);
    List<Employee> mapToEmployee(List<EmployeeDTO> Employees);
}