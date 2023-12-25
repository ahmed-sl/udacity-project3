package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return petService.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.getPetById(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPet();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByCustomer(ownerId);
    }
}
