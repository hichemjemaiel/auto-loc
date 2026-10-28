package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Client Management", description = "Endpoints pour la gestion des clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientService clientService;

    @Operation(summary = "Ajouter un nouveau client")
    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @Operation(summary = "Mettre à jour un client existant")
    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @Operation(summary = "Récupérer un client par son ID")
    @GetMapping("/{id}")
    public Client retrieveClient(@PathVariable("id") Long idClient) {
        return clientService.retrieveClient(idClient);
    }

    @Operation(summary = "Récupérer la liste de tous les clients")
    @GetMapping
    public List<Client> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @Operation(summary = "Supprimer un client par son ID")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") Long idClient) {
        clientService.deleteClient(idClient);
    }
}

