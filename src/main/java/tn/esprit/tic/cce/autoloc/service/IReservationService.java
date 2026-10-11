package tn.esprit.tic.cce.autoloc.service;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.tic.cce.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation addReservation(Reservation r);
    Reservation updateReservation(Reservation r);
    Reservation retrieveReservation(Long idReservation);
    void removeReservation(Long idReservation);
    List<Reservation> addReservations(List<Reservation> reservations);
}
