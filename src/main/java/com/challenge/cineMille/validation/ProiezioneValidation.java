package com.challenge.cineMille.validation;

import com.challenge.cineMille.exceptions.ProiezioneDataNotValidException;
import com.challenge.cineMille.model.dto.ProiezioneDTO;
import com.challenge.cineMille.model.entity.ProiezioneEntity;
import com.challenge.cineMille.model.mapper.ProiezioneMapper;
import com.challenge.cineMille.model.mapper.SalaMapper;
import com.challenge.cineMille.repository.ProiezioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProiezioneValidation {

    private final ProiezioneRepository proiezioneRepository;
    private final ProiezioneMapper proiezioneMapper;
    private final SalaMapper salaMapper;

    public boolean validateProiezione(ProiezioneDTO proiezioneDTO) {

        if (proiezioneDTO.getData().isBefore(LocalDate.now())) {
            throw new ProiezioneDataNotValidException("La data della proiezione non può essere nel passato.");
        }
        if (proiezioneDTO.getOrario().isBefore(LocalTime.now())) {
            throw new ProiezioneDataNotValidException("L'orario della proiezione non può essere nel passato.");
        }

        List<ProiezioneEntity> proiezioniFilm =
                proiezioneRepository.findByFilmTitolo(proiezioneDTO.getFilm().getTitolo());

        LocalDate dataInizio = proiezioniFilm.stream()
                .map(ProiezioneEntity::getData)
                .min(LocalDate::compareTo)
                .orElse(proiezioneDTO.getData());

        LocalDate dataFine = proiezioniFilm.stream()
                .map(ProiezioneEntity::getData)
                .max(LocalDate::compareTo)
                .orElse(proiezioneDTO.getData());

        LocalTime inizioNuova = proiezioneDTO.getOrario();
        LocalTime fineNuova = inizioNuova.plusMinutes(proiezioneDTO.getFilm().getDurata());

        List<ProiezioneEntity> proiezioniEsistenti = proiezioneRepository.findBySalaAndData(
                proiezioneDTO.getSala().getNumeroSala(),
                proiezioneDTO.getData()
        );

        for (ProiezioneEntity p : proiezioniEsistenti) {
            LocalTime inizioEsistente = p.getOrario();
            LocalTime fineEsistente = inizioEsistente.plusMinutes(p.getFilm().getDurata());

            if (!(fineNuova.isBefore(inizioEsistente) || inizioNuova.isAfter(fineEsistente))) {
                throw new ProiezioneDataNotValidException("Inserimento non disponibile: la sala ha già una proiezione in quell'orario.");
            }
        }

        long settimane = ChronoUnit.WEEKS.between(dataInizio, dataFine);
        if (settimane < 1 || settimane > 3) {
            throw new ProiezioneDataNotValidException("Le proiezioni del film devono coprire almeno una settimana e massimo tre.");
        }

        Optional<ProiezioneDTO> proiezioneProgrammata =
                                proiezioneRepository.findByDataAndSalaAndOrario(
                                                proiezioneDTO.getData(),
                                                salaMapper.toEntity(
                                                        proiezioneDTO.getSala()),
                                                proiezioneDTO.getOrario())
                                        .map(proiezioneMapper::toDto);
        if (proiezioneProgrammata.isPresent()) {
            throw new ProiezioneDataNotValidException("Esiste già una proiezione in questa sala nell'orario indicato.");
        }
        return true;
    }
}
