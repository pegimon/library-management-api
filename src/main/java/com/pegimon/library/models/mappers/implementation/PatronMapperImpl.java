package com.pegimon.library.models.mappers.implementation;

import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.models.dto.PatronDto;
import com.pegimon.library.models.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PatronMapperImpl implements Mapper<PatronEntity, PatronDto> {
    @Override
    public PatronDto map(PatronEntity object) {
        return new PatronDto(object.getId(), object.getName(), object.getEmail(), object.getPhoneNumber());
    }

    @Override
    public PatronEntity reverse(PatronDto object) {
        return new PatronEntity(object.getId(), object.getName(), object.getEmail(), object.getPhoneNumber());
    }
}
