package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Maintenance Management", description = "Endpoints pour la gestion des opérations de maintenance")
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    @Operation(summary = "Ajouter un nouvel enregistrement de maintenance")
    @PostMapping
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    @Operation(summary = "Mettre à jour un enregistrement de maintenance existant")
    @PutMapping
    public Maintenance updateMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.updateMaintenance(maintenance);
    }

    @Operation(summary = "Récupérer un enregistrement de maintenance par son ID")
    @GetMapping("/{id}")
    public Maintenance retrieveMaintenance(@PathVariable("id") Long idMaintenance) {
        return maintenanceService.retrieveMaintenance(idMaintenance);
    }

    @Operation(summary = "Récupérer tous les enregistrements de maintenance")
    @GetMapping
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @Operation(summary = "Supprimer un enregistrement de maintenance par son ID")
    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable("id") Long idMaintenance) {
        maintenanceService.deleteMaintenance(idMaintenance);
    }
}

