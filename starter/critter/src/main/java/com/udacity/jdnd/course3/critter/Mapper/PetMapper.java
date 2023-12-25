package com.udacity.jdnd.course3.critter.Mapper;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.Model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper MAPPER = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "ownerId", source = "customer.id")
    PetDTO mapToDTO(Pet Pet);

    List<PetDTO> mapToDTO(List<Pet> Pets);
}