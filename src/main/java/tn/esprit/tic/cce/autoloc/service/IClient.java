package tn.esprit.tic.cce.autoloc.service;

import tn.esprit.tic.cce.autoloc.domain.Client;

import tn.esprit.tic.cce.autoloc.repository.ClientRepository;
import java.util.List;

public class IClient implements IClientService {

    ClientRepository clientRepository ;
    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client c) {
        //return null;
        return clientRepository.save(c);
    }

    @Override
    public Client updateClient(Client c) {
        //return null;
        return clientRepository.save(c);
    }

    @Override
    public Client retrieveClient(Long idClient) {
        //return null;
        return clientRepository.findById(idClient).orElse(null);
    }

    @Override
    public void removeClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }

    @Override
    public List<Client> addClients(List<Client> clients) {
        //return List.of();
        return(List<Client>) clientRepository.saveAll(clients);
    }
}
