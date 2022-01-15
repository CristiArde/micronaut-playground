package com.bonobo.micronaut.service.mapper;

import com.bonobo.micronaut.domain.Pet;
import com.bonobo.micronaut.service.dto.PetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {PetTypeMapper.class, VisitMapper.class})
public interface PetMapper extends EntityMapper<PetDto, Pet> {
    default Pet fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pet pet = new Pet();
        pet.setId(id);
        return pet;
    }
}
