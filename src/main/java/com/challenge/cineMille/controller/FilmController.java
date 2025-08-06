package com.challenge.cineMille.controller;

import com.challenge.cineMille.model.dto.FilmDTO;
import com.challenge.cineMille.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<?> addFilm(@Valid @RequestBody FilmDTO filmDTO) {
        FilmDTO savedFilm = filmService.save(filmDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<FilmDTO> film = filmService.findAll();
        return ResponseEntity.ok(film);
    }
}
