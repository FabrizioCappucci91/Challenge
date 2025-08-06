package com.challenge.cineMille.model.mapper;

import com.challenge.cineMille.model.dto.FilmDTO;
import com.challenge.cineMille.model.entity.FilmEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilmMapper {

    private final ModelMapper modelMapper;

    public FilmDTO toDto(FilmEntity film) {
        return modelMapper.map(film, FilmDTO.class);
    }

    public FilmEntity toEntity(FilmDTO dto) {
        return modelMapper.map(dto, FilmEntity.class);
    }
}
