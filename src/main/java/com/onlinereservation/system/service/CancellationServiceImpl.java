package com.onlinereservation.system.service;

// CancellationServiceImpl.java


import com.onlinereservation.system.entity.Reservation;
import com.onlinereservation.system.exception.ReservationNotFoundException;
import com.onlinereservation.system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancellationServiceImpl implements CancellationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void cancelReservationByPnrNumber(long pnrNumber) {
        Reservation reservation = reservationRepository.findByPnrNumber(pnrNumber);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found for PNR: " + pnrNumber);
        }
        reservationRepository.delete(reservation);
    }
}
