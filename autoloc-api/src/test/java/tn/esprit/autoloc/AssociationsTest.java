package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tn.esprit.autoloc.entity.*;
import tn.esprit.autoloc.repository.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AssociationsTest {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private EquipementRepository equipementRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Test
    void manyToManyVehiculeEquipementUtiliseUneTableDeJointure() {
        Equipement gps = equipementRepository.save(new Equipement());
        Vehicule v1 = new Vehicule();
        Vehicule v2 = new Vehicule();
        v1.addEquipement(gps);
        v2.addEquipement(gps);
        vehiculeRepository.save(v1);
        vehiculeRepository.save(v2);
        em.flush();
        em.clear();

        Number lignes = (Number) em.getEntityManager()
                .createNativeQuery("select count(*) from vehicule_equipement")
                .getSingleResult();
        assertThat(lignes.longValue()).isEqualTo(2);
        assertThat(equipementRepository.count()).isEqualTo(1);
    }

    @Test
    void persistClientPropageAuxReservations() {
        Client client = new Client();
        client.addReservation(new Reservation());
        client.addReservation(new Reservation());
        clientRepository.save(client);
        em.flush();
        em.clear();

        assertThat(reservationRepository.count()).isEqualTo(2);
    }

    @Test
    void persistVehiculePropageAuxMaintenances() {
        Vehicule vehicule = new Vehicule();
        vehicule.addMaintenance(new Maintenance());
        vehiculeRepository.save(vehicule);
        em.flush();
        em.clear();

        assertThat(maintenanceRepository.count()).isEqualTo(1);
    }

    @Test
    void cascadeAllDeReservationVersContrat() {
        Reservation reservation = new Reservation();
        reservation.setContrat(new Contrat());
        reservationRepository.save(reservation);
        em.flush();
        em.clear();

        assertThat(contratRepository.count()).isEqualTo(1);

        reservationRepository.deleteAll();
        em.flush();

        assertThat(contratRepository.count()).isZero();
    }
}
