package it.epicode.gestioneViaggi.payload.viaggioDTO;

import it.epicode.gestioneViaggi.enums.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewViaggioDTO(

        @NotBlank(message = "Destinazione obbligatoria")
        String destinazione,
        @NotNull(message = "Data obbligatoria")
        LocalDate data,
        @NotNull(message = "Stato obbligatorio")
        Stato stato
) {
}
