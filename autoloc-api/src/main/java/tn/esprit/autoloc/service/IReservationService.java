package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    Reservation retrieveReservation(Long idReservation);
    List<Reservation> retrieveAllReservations();
    void deleteReservation(Long idReservation);
}

