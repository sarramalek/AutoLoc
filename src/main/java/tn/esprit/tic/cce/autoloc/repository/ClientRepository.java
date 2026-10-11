package tn.esprit.tic.cce.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.cce.autoloc.domain.Client;
// jparepository , pagingandsortingRepository ,CrudRepository
public interface ClientRepository extends JpaRepository<Client, Long> { }
