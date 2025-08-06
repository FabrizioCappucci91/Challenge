package com.challenge.cineMille.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Sala")
@Data
@NoArgsConstructor
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroSala;
    private int capienza;
    private String tipoTecnologia;

    @OneToMany(mappedBy = "sala")
    private List<ProiezioneEntity> proiezioni;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;
}