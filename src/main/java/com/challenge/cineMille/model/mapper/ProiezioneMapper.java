package com.challenge.cineMille.model.mapper;

import com.challenge.cineMille.model.dto.ProiezioneDTO;
import com.challenge.cineMille.model.entity.ProiezioneEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProiezioneMapper {


    private final ModelMapper modelMapper;

    public ProiezioneDTO toDto(ProiezioneEntity proiezione) {
        return modelMapper.map(proiezione, ProiezioneDTO.class);
    }

    public ProiezioneEntity toEntity(ProiezioneDTO dto) {
        return modelMapper.map(dto, ProiezioneEntity.class);
    }
}
