package it.epicode.gestioneViaggi.payload.dipendenteDTO;

public record DipendenteResponseDTO(
        Long id,
        String username,
        String nome,
        String cognome,
        String email,
        String avatarUrl
) {}
