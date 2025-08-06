package com.challenge.cineMille.controller;

import com.challenge.cineMille.model.dto.ProiezioneDTO;
import com.challenge.cineMille.service.ProiezioneService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/proiezioni")
@RequiredArgsConstructor
public class ProiezioneController {

    private final ProiezioneService proiezioneService;

    @GetMapping("byTitoloAndIntervalloDate")
    public ResponseEntity<?> findByFilmTitoloAndDataBetween(@RequestParam @Nullable String titolo, @RequestParam LocalDate dataInizio, @RequestParam LocalDate dataFine) {
        if (dataFine.isBefore(dataInizio)) {
            return ResponseEntity.badRequest().body("Intervallo date non valido: dataFine < dataInizio");
        }
        if (dataInizio.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Intervallo date non valido: dataInizio < data corrente");
        }
        return ResponseEntity.ok(proiezioneService.findByFilmTitoloAndDataBetween(titolo, dataInizio, dataFine));
    }

    @GetMapping("/salaAndData")
    public ResponseEntity<?> findBySalaAndData(@RequestParam int numeroSala, @RequestParam LocalDate data) {
        return ResponseEntity.ok(proiezioneService.findBySalaAndData(numeroSala, data));
    }

    @PostMapping
    public ResponseEntity<?> addProiezione(@RequestBody ProiezioneDTO proiezioneDTO) {
        ProiezioneDTO savedProiezione = proiezioneService.save(proiezioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProiezione);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<ProiezioneDTO> proiezioni = proiezioneService.findAll();
        return ResponseEntity.ok(proiezioni);
    }
}
