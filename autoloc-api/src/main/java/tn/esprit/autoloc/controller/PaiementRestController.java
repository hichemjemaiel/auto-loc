package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Paiement Management", description = "Endpoints pour la gestion des paiements")
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    @Operation(summary = "Ajouter un nouveau paiement")
    @PostMapping
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @Operation(summary = "Mettre à jour un paiement existant")
    @PutMapping
    public Paiement updatePaiement(@RequestBody Paiement paiement) {
        return paiementService.updatePaiement(paiement);
    }

    @Operation(summary = "Récupérer un paiement par son ID")
    @GetMapping("/{id}")
    public Paiement retrievePaiement(@PathVariable("id") Long idPaiement) {
        return paiementService.retrievePaiement(idPaiement);
    }

    @Operation(summary = "Récupérer la liste de tous les paiements")
    @GetMapping
    public List<Paiement> retrieveAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @Operation(summary = "Supprimer un paiement par son ID")
    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable("id") Long idPaiement) {
        paiementService.deletePaiement(idPaiement);
    }
}

