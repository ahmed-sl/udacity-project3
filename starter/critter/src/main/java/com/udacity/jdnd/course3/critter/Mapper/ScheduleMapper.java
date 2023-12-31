package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Model.Employee;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleMapper MAPPER = Mappers.getMapper(ScheduleMapper.class);


    @Mapping(target = "employeeIds", source = "employees")
    @Mapping(target = "petIds", source = "pets")
    @Mapping(target = "activities")
    ScheduleDTO mapToDTO(Schedule Schedule);



    List<ScheduleDTO> mapToDTO(List<Schedule> Schedules);

    default List<Long> mapEmployees(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapPets(List<Pet> pets) {
        return pets.stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
    }

    default Set<EmployeeSkill> mapActivities(Set<EmployeeSkill> activities) {
        return activities;
    }
}