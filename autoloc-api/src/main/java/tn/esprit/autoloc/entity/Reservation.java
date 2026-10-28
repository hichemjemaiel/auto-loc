package tn.esprit.autoloc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.enums.StatutReservation;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    // Cote proprietaire (cle etrangere client_id_client)
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    // Cote proprietaire (cle etrangere vehicule_id_vehicule) - aucune cascade
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicule vehicule;

    // Cote inverse du OneToOne - cascade ALL : le contrat n'existe que pour sa reservation.
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contrat contrat;

    // Garde les deux cotes synchronises.
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
        if (contrat != null) {
            contrat.setReservation(this);
        }
    }
}
