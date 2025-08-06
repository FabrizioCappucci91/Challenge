package com.challenge.cineMille.repository;

import com.challenge.cineMille.model.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {
}
