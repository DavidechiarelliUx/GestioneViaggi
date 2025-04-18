package it.epicode.gestioneViaggi.repository;


import it.epicode.gestioneViaggi.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
