package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Agence Management", description = "Endpoints pour la gestion des agences")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceRestController {

    private final IAgenceService agenceService;

    @Operation(summary = "Ajouter une nouvelle agence")
    @PostMapping
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    @Operation(summary = "Mettre à jour une agence existante")
    @PutMapping
    public Agence updateAgence(@RequestBody Agence agence) {
        return agenceService.updateAgence(agence);
    }

    @Operation(summary = "Récupérer une agence par son ID")
    @GetMapping("/{id}")
    public Agence retrieveAgence(@PathVariable("id") Long idAgence) {
        return agenceService.retrieveAgence(idAgence);
    }

    @Operation(summary = "Récupérer la liste de toutes les agences")
    @GetMapping
    public List<Agence> retrieveAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @Operation(summary = "Supprimer une agence par son ID")
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable("id") Long idAgence) {
        agenceService.deleteAgence(idAgence);
    }
}

