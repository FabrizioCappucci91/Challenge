package com.challenge.cineMille.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Cinema")
@Data
@NoArgsConstructor
public class CinemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;

    @OneToMany(mappedBy = "cinema")
    private List<SalaEntity> listaSale;

    @OneToMany(mappedBy = "cinema")
    private List<UserEntity> utenti;
}
