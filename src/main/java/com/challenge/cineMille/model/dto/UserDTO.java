package com.challenge.cineMille.model.dto;

import com.challenge.cineMille.model.enums.Ruolo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserDTO {
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String cognome;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    @NotEmpty
    private Set<Ruolo> ruoli;

}
