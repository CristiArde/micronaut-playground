package com.bonobo.micronaut.service.mapper;

import com.bonobo.micronaut.domain.PetType;
import com.bonobo.micronaut.service.dto.PetTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {})
public interface PetTypeMapper extends EntityMapper<PetTypeDto, PetType> {

    default PetType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PetType petType = new PetType();
        petType.setId(id);
        return petType;
    }
}
