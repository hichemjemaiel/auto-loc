package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Employe;

import java.util.List;

public interface IEmployeService {
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    Employe retrieveEmploye(Long idEmploye);
    List<Employe> retrieveAllEmployes();
    void deleteEmploye(Long idEmploye);
}

