package it.epicode.gestioneViaggi.entity;

import it.epicode.gestioneViaggi.enums.Stato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaggi")

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column(nullable = false, length = 50)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Stato stato;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "viaggio_id")
    private List<Dipendente> dipendenti = new ArrayList<>();

}
