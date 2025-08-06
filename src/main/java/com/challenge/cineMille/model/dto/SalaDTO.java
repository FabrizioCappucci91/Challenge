package com.challenge.cineMille.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class SalaDTO {

    @NotNull
    @Positive
    private int numeroSala;

    @Positive
    @NotNull
    private int capienza;

    @NotNull
    @NotBlank
    private String tipoTecnologia;

    private List<ProiezioneDTO> proiezioni;
}