package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Agence;

import java.util.List;

public interface IAgenceService {
    Agence addAgence(Agence agence);
    Agence updateAgence(Agence agence);
    Agence retrieveAgence(Long idAgence);
    List<Agence> retrieveAllAgences();
    void deleteAgence(Long idAgence);
}

