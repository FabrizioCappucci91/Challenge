package com.challenge.cineMille.service;

import com.challenge.cineMille.configuration.AuthenticationContext;
import com.challenge.cineMille.exceptions.NotAuthenticatedException;
import com.challenge.cineMille.model.dto.FilmDTO;
import com.challenge.cineMille.model.mapper.FilmMapper;
import com.challenge.cineMille.repository.FilmRepository;
import com.challenge.cineMille.validation.FilmValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;
    private final FilmValidation filmValidation;
    private final AuthenticationContext authenticationContext;

    @Override
    @Transactional
    public FilmDTO save(FilmDTO film) {
        if (!authenticationContext.isAdmin()) {
            throw new NotAuthenticatedException("Accesso negato: solo gli amministratori possono aggiungere film.");
        }
        filmValidation.validateFilm(film);
        return filmMapper.toDto(filmRepository.save(filmMapper.toEntity(film)));
    }

    @Override
    public List<FilmDTO> findAll() {
        if (!authenticationContext.isAdmin()) {
            throw new NotAuthenticatedException("Accesso negato: solo gli amministratori possono visualizzare lo storico di tutti i film.");
        }

        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toDto)
                .toList();
    }
}
