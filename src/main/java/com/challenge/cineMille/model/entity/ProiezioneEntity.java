package com.challenge.cineMille.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Proiezione")
@Data
@NoArgsConstructor
public class ProiezioneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime orario;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private SalaEntity sala;
}
