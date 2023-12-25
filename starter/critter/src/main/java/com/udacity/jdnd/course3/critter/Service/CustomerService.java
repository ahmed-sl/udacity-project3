package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.Mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PetRepository petRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepository.save(convertToCustomer(customerDTO));
        return customerMapper.mapToDTO(customer);
    }

    public CustomerDTO getCustomerByPetId(Long petId){
        return customerMapper.mapToDTO(petRepository.findById(petId).get().getCustomer());
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.mapToDTO(customers);
    }

    private Customer convertToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());

        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();


        if (petIds != null) {
            for (Long petId : petIds) {
                petRepository.findById(petId).ifPresent(pets::add);
            }
        }
        customer.setPets(pets);
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        return customer;
    }
}
