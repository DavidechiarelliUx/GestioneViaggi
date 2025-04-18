package it.epicode.gestioneViaggi.repository;


import it.epicode.gestioneViaggi.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
