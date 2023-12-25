package com.udacity.jdnd.course3.critter.Model;


import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Customer extends Users{
    @Nationalized
    private String phoneNumber;

    @Nationalized
    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    public void addPet(Pet pet){
        pets.add(pet);
    }

}
