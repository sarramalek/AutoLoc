package tn.esprit.tic.cce.autoloc.service;
import org.springframework.stereotype.Service;
import tn.esprit.tic.cce.autoloc.domain.Reservation;
import tn.esprit.tic.cce.autoloc.repository.ReservationRepository;
import tn.esprit.tic.cce.autoloc.service.IReservationService;

import java.util.List;

public class IReservation implements IReservationService {
    private final ReservationRepository reservationRepository;

    public IReservation(
            ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public void removeReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public List<Reservation> addReservations(
            List<Reservation> reservations) {
        return reservationRepository.saveAll(reservations);
    }
}
