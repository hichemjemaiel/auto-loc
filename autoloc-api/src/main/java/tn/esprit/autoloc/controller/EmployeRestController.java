package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Employe Management", description = "Endpoints pour la gestion des employés")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeRestController {

    private final IEmployeService employeService;

    @Operation(summary = "Ajouter un nouvel employé")
    @PostMapping
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    @Operation(summary = "Mettre à jour un employé existant")
    @PutMapping
    public Employe updateEmploye(@RequestBody Employe employe) {
        return employeService.updateEmploye(employe);
    }

    @Operation(summary = "Récupérer un employé par son ID")
    @GetMapping("/{id}")
    public Employe retrieveEmploye(@PathVariable("id") Long idEmploye) {
        return employeService.retrieveEmploye(idEmploye);
    }

    @Operation(summary = "Récupérer la liste de tous les employés")
    @GetMapping
    public List<Employe> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @Operation(summary = "Supprimer un employé par son ID")
    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable("id") Long idEmploye) {
        employeService.deleteEmploye(idEmploye);
    }
}

