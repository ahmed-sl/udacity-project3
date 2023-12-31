package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDaysAvailableAndSkillsIn(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills);
}
