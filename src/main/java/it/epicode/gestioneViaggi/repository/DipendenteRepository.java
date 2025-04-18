package it.epicode.gestioneViaggi.repository;


import it.epicode.gestioneViaggi.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}
