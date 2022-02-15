package com.bonobo.service.mapper;

import com.bonobo.domain.Specialty;
import com.bonobo.service.dto.SpecialtyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {})
public interface SpecialtyMapper extends EntityMapper<SpecialtyDto, Specialty> {

}
