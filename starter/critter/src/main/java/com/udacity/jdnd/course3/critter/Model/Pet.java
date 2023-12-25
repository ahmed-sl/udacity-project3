package com.udacity.jdnd.course3.critter.Model;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Enum.PetType;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Nationalized
    private String name;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private LocalDate birthDate;

    @Nationalized
    private String notes;


}
