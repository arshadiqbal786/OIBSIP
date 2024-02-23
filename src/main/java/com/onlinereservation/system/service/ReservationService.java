package com.onlinereservation.system.service;

import com.onlinereservation.system.entity.Reservation;
import com.onlinereservation.system.payload.ReservationDTO;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);
}