package com.challenge.cineMille.validation;

import com.challenge.cineMille.exceptions.FilmDataNotValidException;
import com.challenge.cineMille.model.dto.FilmDTO;
import com.challenge.cineMille.model.entity.FilmEntity;
import com.challenge.cineMille.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilmValidation {

    private final FilmRepository filmRepository;

    public boolean validateFilm(FilmDTO film) {

        Optional<FilmEntity> existingFilm = filmRepository.findByTitoloAndDurataAndGenere(film.getTitolo(),film.getDurata(), film.getGenere());
        if (existingFilm.isPresent()) {
            throw new FilmDataNotValidException("Esiste già un film con lo stesso titolo,durate e genere.");
        }
        return true;
    }
}
