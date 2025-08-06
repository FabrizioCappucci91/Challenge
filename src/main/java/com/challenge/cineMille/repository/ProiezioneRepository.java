package com.challenge.cineMille.repository;

import com.challenge.cineMille.model.entity.ProiezioneEntity;
import com.challenge.cineMille.model.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProiezioneRepository extends JpaRepository<ProiezioneEntity, Long> {

    @Query("SELECT p FROM Proiezione p WHERE p.film.titolo = :titolo AND p.data BETWEEN :startDate AND :endDate")
    List<ProiezioneEntity> findByFilmTitoloAndDataBetween(@Param("titolo") String titolo,
                                                          @Param("startDate") LocalDate startDate,
                                                          @Param("endDate") LocalDate endDate);
    List<ProiezioneEntity> findByDataBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Proiezione p WHERE p.film.titolo = :titolo")
    List<ProiezioneEntity> findByFilmTitolo(@Param("titolo") String titolo);

    Optional<ProiezioneEntity> findByDataAndSalaAndOrario(LocalDate date,
                                                         SalaEntity sala,
                                                         LocalTime orario);

    @Query("SELECT p FROM Proiezione p WHERE p.sala.numeroSala = :numeroSala AND p.data = :data")
    List<ProiezioneEntity> findBySalaAndData(@Param("numeroSala") int numeroSala, @Param("data") LocalDate data);


}
