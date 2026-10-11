package tn.esprit.tic.cce.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.cce.autoloc.domain.Client;
import tn.esprit.tic.cce.autoloc.domain.Equipement;

public interface EquipementRepository  extends JpaRepository<Equipement, Long> {
}
