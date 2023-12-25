package com.udacity.jdnd.course3.critter.Model;


import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Employee extends Users{

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

}
