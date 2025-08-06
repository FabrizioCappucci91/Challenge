package com.challenge.cineMille.service;

import com.challenge.cineMille.model.dto.FilmDTO;

import java.util.List;

public interface FilmService {

    FilmDTO save(FilmDTO film);

    List<FilmDTO> findAll();
}
