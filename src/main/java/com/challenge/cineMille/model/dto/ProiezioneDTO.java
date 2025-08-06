package com.challenge.cineMille.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProiezioneDTO {

    private Long id;

    @NotNull
    @NotBlank
    private LocalTime orario;
    @NotNull
    @NotBlank
    @FutureOrPresent
    private LocalDate data;
    @NotNull
    @NotBlank
    @JsonBackReference
    private FilmDTO film;
    @NotNull
    @NotBlank
    private SalaDTO sala;
}
