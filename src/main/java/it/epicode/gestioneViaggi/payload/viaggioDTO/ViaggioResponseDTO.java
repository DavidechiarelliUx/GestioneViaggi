package it.epicode.gestioneViaggi.payload.viaggioDTO;

import it.epicode.gestioneViaggi.enums.Stato;

import java.time.LocalDate;
import java.util.List;

public record ViaggioResponseDTO(
        Long id,
        String destinazione,
        LocalDate data,
        Stato stato,
        List<Long> dipendentiId
) {
}
