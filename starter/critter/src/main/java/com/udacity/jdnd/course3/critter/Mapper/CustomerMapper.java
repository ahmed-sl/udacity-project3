package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "pets",target = "petIds" )
    CustomerDTO mapToDTO(Customer Customer);

    List<CustomerDTO> mapToDTO(List<Customer> Customers);


    default List<Long> mapPets(List<Pet> pets) {
        return pets.stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
    }

}