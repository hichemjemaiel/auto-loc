package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Contrat Management", description = "Endpoints pour la gestion des contrats")
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratRestController {

    private final IContratService contratService;

    @Operation(summary = "Ajouter un nouveau contrat")
    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @Operation(summary = "Mettre à jour un contrat existant")
    @PutMapping
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        return contratService.updateContrat(contrat);
    }

    @Operation(summary = "Récupérer un contrat par son ID")
    @GetMapping("/{id}")
    public Contrat retrieveContrat(@PathVariable("id") Long idContrat) {
        return contratService.retrieveContrat(idContrat);
    }

    @Operation(summary = "Récupérer la liste de tous les contrats")
    @GetMapping
    public List<Contrat> retrieveAllContrats() {
        return contratService.retrieveAllContrats();
    }

    @Operation(summary = "Supprimer un contrat par son ID")
    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable("id") Long idContrat) {
        contratService.deleteContrat(idContrat);
    }
}

