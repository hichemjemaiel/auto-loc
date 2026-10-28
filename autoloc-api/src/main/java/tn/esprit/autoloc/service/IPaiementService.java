package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement addPaiement(Paiement paiement);
    Paiement updatePaiement(Paiement paiement);
    Paiement retrievePaiement(Long idPaiement);
    List<Paiement> retrieveAllPaiements();
    void deletePaiement(Long idPaiement);
}

