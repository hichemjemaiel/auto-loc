package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Equipement Management", description = "Endpoints pour la gestion des équipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementRestController {

    private final IEquipementService equipementService;

    @Operation(summary = "Ajouter un nouvel équipement")
    @PostMapping
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @Operation(summary = "Mettre à jour un équipement existant")
    @PutMapping
    public Equipement updateEquipement(@RequestBody Equipement equipement) {
        return equipementService.updateEquipement(equipement);
    }

    @Operation(summary = "Récupérer un équipement par son ID")
    @GetMapping("/{id}")
    public Equipement retrieveEquipement(@PathVariable("id") Long idEquipement) {
        return equipementService.retrieveEquipement(idEquipement);
    }

    @Operation(summary = "Récupérer la liste de tous les équipements")
    @GetMapping
    public List<Equipement> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @Operation(summary = "Supprimer un équipement par son ID")
    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable("id") Long idEquipement) {
        equipementService.deleteEquipement(idEquipement);
    }
}

