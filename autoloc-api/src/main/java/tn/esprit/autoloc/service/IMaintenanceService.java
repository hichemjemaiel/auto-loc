package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    Maintenance retrieveMaintenance(Long idMaintenance);
    List<Maintenance> retrieveAllMaintenances();
    void deleteMaintenance(Long idMaintenance);
}

