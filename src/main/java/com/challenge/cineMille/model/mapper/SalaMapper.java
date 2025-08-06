package com.challenge.cineMille.model.mapper;

import com.challenge.cineMille.model.dto.SalaDTO;
import com.challenge.cineMille.model.entity.SalaEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalaMapper {

    private final ModelMapper modelMapper;

    public SalaDTO toDto(SalaEntity sala) {
        return modelMapper.map(sala, SalaDTO.class);
    }

    public SalaEntity toEntity(SalaDTO dto) {
        return modelMapper.map(dto, SalaEntity.class);
    }
}
