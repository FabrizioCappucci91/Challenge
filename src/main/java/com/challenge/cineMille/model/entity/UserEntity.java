package com.challenge.cineMille.model.entity;

import com.challenge.cineMille.model.enums.Ruolo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "Utenti")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    @Column(unique = true)
    private String username;

    private String password;

    @CollectionTable(
            name = "user_ruoli",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Ruolo> ruoli;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;
}