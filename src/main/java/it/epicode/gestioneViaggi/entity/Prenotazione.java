package it.epicode.gestioneViaggi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Prenotazione")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id",  nullable = false)
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id",  nullable = false)
    private Dipendente dipendente;

    @Column(nullable = false, name = "data_richiesta")
    private LocalDate dataRichiesta;

    @Column(name = "note", length = 200)
    private String note;

}
