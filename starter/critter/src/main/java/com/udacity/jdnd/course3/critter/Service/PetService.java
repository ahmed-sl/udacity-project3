package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.Mapper.PetMapper;
import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetDTO savePet(PetDTO petDTO){
        Pet pet = mapToPet(petDTO);
        Customer customer = customerRepository.getOne(petDTO.getOwnerId());
        pet.setCustomer(customer);
        petRepository.save(pet);
        customer.addPet(pet);
        customerRepository.save(customer);
        return petMapper.mapToDTO(pet);
    }

    public PetDTO getPetById(Long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);
        return petMapper.mapToDTO(pet);
    }

    public List<PetDTO> getAllPet() {
        return petMapper.mapToDTO(petRepository.findAll());
    }

    public List<PetDTO> getPetsByCustomer(long customerId) {
        List<Pet> pets = petRepository.getAllByCustomerId(customerId);
        return petMapper.mapToDTO(pets);
    }



    private Pet mapToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setCustomer(customerRepository.getOne(petDTO.getOwnerId()));
        return pet;
    }
}
