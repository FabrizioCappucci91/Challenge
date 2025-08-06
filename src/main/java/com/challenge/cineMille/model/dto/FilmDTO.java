package com.challenge.cineMille.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FilmDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String titolo;
    @NotNull
    @NotBlank
    private String descrizione;

    @NotNull
    @Positive
    private int durata;

    @NotBlank
    @NotNull
    private String genere;
    @JsonManagedReference
    private List<ProiezioneDTO> proiezioni;
}
