package com.challenge.cineMille.service;

import com.challenge.cineMille.configuration.AuthenticationContext;
import com.challenge.cineMille.exceptions.NotAuthenticatedException;
import com.challenge.cineMille.model.dto.ProiezioneDTO;
import com.challenge.cineMille.model.mapper.ProiezioneMapper;
import com.challenge.cineMille.repository.ProiezioneRepository;
import com.challenge.cineMille.validation.ProiezioneValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProiezioneServiceImpl implements ProiezioneService {

    private final ProiezioneRepository proiezioneRepository;
    private final ProiezioneMapper proiezioneMapper;
    private final AuthenticationContext authenticationContext;
    private final ProiezioneValidation proiezioneValidation;

    @Override
    public List<ProiezioneDTO> findByFilmTitoloAndDataBetween(String titolo, LocalDate dataInizio, LocalDate dataFine) {
        return titolo != null && !titolo.isEmpty()
                ? proiezioneRepository.findByFilmTitoloAndDataBetween(titolo, dataInizio, dataFine)
                .stream()
                .map(proiezioneMapper::toDto)
                .toList()
                : proiezioneRepository.findByDataBetween(dataInizio, dataFine)
                .stream()
                .map(proiezioneMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProiezioneDTO save(ProiezioneDTO proiezioneDTO) {
        if (!authenticationContext.isAdmin()) {
            throw new NotAuthenticatedException("Accesso non autorizzato: solo gli amministratori possono aggiungere proiezioni.");
        }
        proiezioneValidation.validateProiezione(proiezioneDTO);
        return proiezioneMapper.toDto(proiezioneRepository.save(proiezioneMapper.toEntity(proiezioneDTO)));

    }

    @Override
    public List<ProiezioneDTO> findBySalaAndData(int numeroSala, LocalDate data) {
        return proiezioneRepository.findBySalaAndData(numeroSala, data)
                .stream()
                .map(proiezioneMapper::toDto)
                .toList();
    }

    @Override
    public List<ProiezioneDTO> findAll() {
        if (!authenticationContext.isAdmin()) {
            throw new NotAuthenticatedException("Accesso non autorizzato: solo gli amministratori possono visualizzare tutte le proiezioni.");
        }
        return proiezioneRepository.findAll()
                .stream()
                .map(proiezioneMapper::toDto)
                .toList();
    }
}
