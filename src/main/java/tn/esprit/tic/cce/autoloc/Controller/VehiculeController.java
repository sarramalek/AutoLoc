package tn.esprit.tic.cce.autoloc.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.tic.cce.autoloc.domain.Vehicule;
import tn.esprit.tic.cce.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // ⚠️ mets ici les vrais champs de ta classe Vehicule
    public record VehiculeDTO(Long idVehicule, String immatriculation, String marque, String modele) {}

    // ---- mapping DTO <-> entité ----
    private Vehicule toEntity(VehiculeDTO dto) {
        Vehicule v = new Vehicule();
        v.setImmatriculation(dto.immatriculation());
        v.setMarque(dto.marque());
        v.setModele(dto.modele());
        return v;
    }

    private VehiculeDTO toDto(Vehicule v) {
        return new VehiculeDTO(v.getIdVehicule(), v.getImmatriculation(), v.getMarque(), v.getModele());
    }

    // CREATE : Ajouter un véhicule
    @PostMapping("/add")
    public ResponseEntity<VehiculeDTO> addVehicule(@RequestBody VehiculeDTO dto) {
        Vehicule saved = vehiculeService.addVehicule(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // READ : Afficher tous les véhicules
    @GetMapping("/all")
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.retrieveAllVehicules().stream().map(this::toDto).toList();
    }

    // READ : Rechercher un véhicule par ID
    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.retrieveVehicule(id);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(vehicule));
    }

    // UPDATE : Modifier un véhicule
    @PutMapping("/update")
    public ResponseEntity<VehiculeDTO> updateVehicule(@RequestBody VehiculeDTO dto) {
        if (dto.idVehicule() == null) {
            return ResponseEntity.badRequest().build();
        }
        Vehicule existing = vehiculeService.retrieveVehicule(dto.idVehicule());
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setImmatriculation(dto.immatriculation());
        existing.setMarque(dto.marque());
        existing.setModele(dto.modele());

        return ResponseEntity.ok(toDto(vehiculeService.updateVehicule(existing)));
    }

    // DELETE : Supprimer un véhicule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        if (vehiculeService.retrieveVehicule(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.removeVehicule(id);
        return ResponseEntity.noContent().build();
    }

    // CREATE : Ajouter plusieurs véhicules
    @PostMapping("/addAll")
    public ResponseEntity<List<VehiculeDTO>> addVehicules(@RequestBody List<VehiculeDTO> dtos) {
        List<Vehicule> saved = vehiculeService.addVehicules(dtos.stream().map(this::toEntity).toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.stream().map(this::toDto).toList());
    }
}