package it.epicode.gestioneViaggi.payload.responseDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

    public record NewPrenotazioneDTO(
            @NotNull(message="Dipendente obbligatorio")
            Long dipendenteId,
            @NotNull(message="Viaggio obbligatorio")
            Long viaggioId,
            @NotNull(message="Data richiesta obbligatoria")
            LocalDate dataRichiesta,
            @Size(max=500, message="Note max 500 caratteri")
            String note
    ) {}

