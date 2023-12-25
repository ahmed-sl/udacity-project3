package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Employee;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getAllByPetsContains(Pet pet);
    List<Schedule> getAllByEmployeesContains(Employee employee);
    List<Schedule> getAllByPetsIn(List<Pet> pets);
}