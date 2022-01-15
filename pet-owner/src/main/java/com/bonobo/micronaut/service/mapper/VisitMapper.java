package com.bonobo.micronaut.service.mapper;

import com.bonobo.micronaut.domain.Visit;
import com.bonobo.micronaut.service.dto.VisitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330", uses = {})
public interface VisitMapper extends EntityMapper<VisitDto, Visit>{
    @Mapping(source = "pet.id", target = "petId")
    VisitDto toDto(Visit visit);

    @Mapping(source = "petId", target = "pet.id")
    Visit toEntity(VisitDto visitDTO);

    default Visit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Visit visit = new Visit();
        visit.setId(id);
        return visit;
    }
}
