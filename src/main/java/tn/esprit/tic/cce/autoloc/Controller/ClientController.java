package tn.esprit.tic.cce.autoloc.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.tic.cce.autoloc.domain.Client;
import tn.esprit.tic.cce.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    public record ClientDTO(Long idClient, String nom, String prenom, String email) {}

    // ---- mapping DTO <-> entité ----
    private Client toEntity(ClientDTO dto) {
        Client c = new Client();
        c.setNom(dto.nom());
        c.setPrenom(dto.prenom());
        c.setEmail(dto.email());
        return c;
    }

    private ClientDTO toDto(Client c) {
        return new ClientDTO(c.getIdClient(), c.getNom(), c.getPrenom(), c.getEmail());
    }

    // CREATE : Ajouter un client
    @PostMapping("/add")
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO dto) {
        Client saved = clientService.addClient(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // READ : Afficher tous les clients
    @GetMapping("/all")
    public List<ClientDTO> getAllClients() {
        return clientService.retrieveAllClients().stream().map(this::toDto).toList();
    }

    // READ : Rechercher un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.retrieveClient(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(client));
    }

    // UPDATE : Modifier un client
    @PutMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO dto) {
        if (dto.idClient() == null) {
            return ResponseEntity.badRequest().build();
        }
        Client existing = clientService.retrieveClient(dto.idClient());
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setNom(dto.nom());
        existing.setPrenom(dto.prenom());
        existing.setEmail(dto.email());

        return ResponseEntity.ok(toDto(clientService.updateClient(existing)));
    }

    // DELETE : Supprimer un client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.retrieveClient(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.removeClient(id);
        return ResponseEntity.noContent().build();
    }

    // CREATE : Ajouter plusieurs clients
    @PostMapping("/addAll")
    public ResponseEntity<List<ClientDTO>> addClients(@RequestBody List<ClientDTO> dtos) {
        List<Client> saved = clientService.addClients(dtos.stream().map(this::toEntity).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.stream().map(this::toDto).toList());
    }
}