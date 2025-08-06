package com.challenge.cineMille.service;

import com.challenge.cineMille.model.dto.ProiezioneDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProiezioneService {

    List<ProiezioneDTO> findByFilmTitoloAndDataBetween(String titolo, LocalDate dataInizio, LocalDate dataFine);
    ProiezioneDTO save(ProiezioneDTO dto);
    List<ProiezioneDTO> findBySalaAndData(int numeroSala, LocalDate data);
    List<ProiezioneDTO> findAll();

}
