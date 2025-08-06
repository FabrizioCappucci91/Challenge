package com.challenge.cineMille.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "Film")
@Data
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private int durata;
    private String genere;

    @OneToMany(mappedBy = "film")
    private List<ProiezioneEntity> proiezioni;
}
