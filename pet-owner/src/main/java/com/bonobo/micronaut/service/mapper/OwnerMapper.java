package com.bonobo.micronaut.service.mapper;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.service.dto.OwnerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {PetMapper.class})
public interface OwnerMapper extends EntityMapper<OwnerDto, Owner> {
    default Owner fromId(Long id) {
        if (id == null) return null;
        Owner owner = new Owner();
        owner.setId(id);
        return owner;
    }
}
