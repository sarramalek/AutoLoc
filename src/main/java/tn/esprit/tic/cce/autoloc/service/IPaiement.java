package tn.esprit.tic.cce.autoloc.service;
import org.springframework.stereotype.Service;
import tn.esprit.tic.cce.autoloc.domain.Paiement;
import tn.esprit.tic.cce.autoloc.repository.PaiementRepository;
import tn.esprit.tic.cce.autoloc.service.IPaiementService;

import java.util.List;

public class IPaiement implements IPaiementService {
    private final PaiementRepository paiementRepository;

    public IPaiement(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement addPaiement(Paiement p) {
        return paiementRepository.save(p);
    }

    @Override
    public Paiement updatePaiement(Paiement p) {
        return paiementRepository.save(p);
    }

    @Override
    public Paiement retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement).orElse(null);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public List<Paiement> addPaiements(List<Paiement> paiements) {
        return paiementRepository.saveAll(paiements);
    }
}
