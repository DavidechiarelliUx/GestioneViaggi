package it.epicode.gestioneViaggi.payload.responseDTO;

import java.time.LocalDate;

public record PrenotazioneResponseDTO(
        Long id,
        Long dipendenteId,
        Long viaggioId,
        LocalDate dataRichiesta,
        String note
) {}
