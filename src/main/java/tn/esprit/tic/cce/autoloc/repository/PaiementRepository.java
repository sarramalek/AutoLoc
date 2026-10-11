package tn.esprit.tic.cce.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.cce.autoloc.domain.Paiement;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
