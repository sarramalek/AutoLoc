package tn.esprit.tic.cce.autoloc.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.cce.autoloc.domain.Client;
import tn.esprit.tic.cce.autoloc.service.IClientService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Tag(name = "Clients", description = "CRUD des clients")
public class ClientController {

    IClientService clientService;

    @Operation(summary = "Lister tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    @GetMapping()
    public List<Client> getClients() {
        return clientService.retrieveAllClients();
    }

    @Operation(summary = "Récupérer un client par id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content)
    })
    @GetMapping("/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.retrieveClient(clientId);
    }

    @Operation(summary = "Ajouter un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Client addClient(@RequestBody Client c) {
        return clientService.addClient(c);
    }

    @Operation(summary = "Supprimer un client")
    @ApiResponse(responseCode = "204", description = "Client supprimé")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }

    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PutMapping()
    public Client modifyClient(@RequestBody Client c) {
        return clientService.updateClient(c);
    }
}