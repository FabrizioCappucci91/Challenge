package com.challenge.cineMille.repository;

import com.challenge.cineMille.model.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    Optional<FilmEntity> findByTitoloAndDurataAndGenere(String titolo, int durata, String genere);
}
