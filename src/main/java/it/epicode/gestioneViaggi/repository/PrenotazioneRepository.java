package it.epicode.gestioneViaggi.repository;


import it.epicode.gestioneViaggi.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByDipendenteIdAndDataRichiesta(
            Long dipendenteId,
            LocalDate dataRichiesta
    );
}
