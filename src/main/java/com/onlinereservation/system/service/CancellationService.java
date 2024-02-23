package com.onlinereservation.system.service;

import com.onlinereservation.system.entity.Reservation;

public interface CancellationService {
    void cancelReservationByPnrNumber(long pnrNumber);
}
