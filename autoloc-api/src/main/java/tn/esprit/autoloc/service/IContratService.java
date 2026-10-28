package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Contrat;

import java.util.List;

public interface IContratService {
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Contrat contrat);
    Contrat retrieveContrat(Long idContrat);
    List<Contrat> retrieveAllContrats();
    void deleteContrat(Long idContrat);
}

