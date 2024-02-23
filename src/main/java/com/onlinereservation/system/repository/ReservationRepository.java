package com.onlinereservation.system.repository;

import com.onlinereservation.system.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// ReservationRepository.java
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.pnrNumber = :pnrNumber")
    Reservation findByPnrNumber(@Param("pnrNumber") long pnrNumber);
}

