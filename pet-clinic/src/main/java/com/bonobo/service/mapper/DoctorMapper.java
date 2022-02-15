package com.bonobo.service.mapper;

import com.bonobo.domain.Doctor;
import com.bonobo.service.dto.DoctorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {SpecialtyMapper.class})
public interface DoctorMapper extends EntityMapper<DoctorDto, Doctor> {

}
