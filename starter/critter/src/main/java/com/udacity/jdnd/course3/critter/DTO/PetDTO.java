package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.PetType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

}
