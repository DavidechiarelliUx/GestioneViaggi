package it.epicode.gestioneViaggi.payload.dipendenteDTO;

import jakarta.validation.constraints.*;

public record NewDipendenteDTO(
        @NotBlank(message = "Username obbligatorio")
        String username,

        @NotBlank(message = "Nome obbligatorio")
        String nome,

        @NotBlank(message = "Cognome obbligatorio")
        String cognome,

        @NotBlank(message = "Email obbligatoria")
        @Email(message = "Email non valida")
        String email
) {}

