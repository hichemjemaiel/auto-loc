package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Client;

import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    Client updateClient(Client client);
    Client retrieveClient(Long idClient);
    List<Client> retrieveAllClients();
    void deleteClient(Long idClient);
}

