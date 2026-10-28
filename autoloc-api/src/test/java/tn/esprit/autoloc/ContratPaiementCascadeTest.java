package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.entity.Paiement;
import tn.esprit.autoloc.enums.ModePaiement;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ContratPaiementCascadeTest {

    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private TestEntityManager em;

    private Contrat contratAvecDeuxPaiements() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now());
        contrat.setMontantTotal(new BigDecimal("500.00"));
        contrat.setValide(true);
        contrat.addPaiement(paiement("200.00", ModePaiement.CARTE));
        contrat.addPaiement(paiement("300.00", ModePaiement.ESPECES));
        return contrat;
    }

    private Paiement paiement(String montant, ModePaiement mode) {
        Paiement p = new Paiement();
        p.setMontant(new BigDecimal(montant));
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(mode);
        return p;
    }

    @Test
    void persistContratPropageAuxPaiements() {
        Contrat saved = contratRepository.save(contratAvecDeuxPaiements());
        em.flush();
        em.clear();

        assertThat(paiementRepository.count()).isEqualTo(2);
        assertThat(contratRepository.findById(saved.getIdContrat()).orElseThrow()
                .getPaiements()).hasSize(2);
    }

    @Test
    void orphanRemovalSupprimeLePaiementRetireDeLaListe() {
        Contrat saved = contratRepository.save(contratAvecDeuxPaiements());
        em.flush();
        em.clear();

        Contrat contrat = contratRepository.findById(saved.getIdContrat()).orElseThrow();
        contrat.removePaiement(contrat.getPaiements().get(0));
        em.flush();

        assertThat(paiementRepository.count()).isEqualTo(1);
        assertThat(contratRepository.count()).isEqualTo(1);
    }

    @Test
    void removeContratSupprimeSesPaiements() {
        Contrat saved = contratRepository.save(contratAvecDeuxPaiements());
        em.flush();
        em.clear();

        contratRepository.deleteById(saved.getIdContrat());
        em.flush();

        assertThat(contratRepository.count()).isZero();
        assertThat(paiementRepository.count()).isZero();
    }
}
