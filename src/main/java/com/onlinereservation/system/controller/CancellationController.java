package com.onlinereservation.system.controller;

// CancellationController.java

import com.onlinereservation.system.service.CancellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cancellations")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @DeleteMapping("/{pnrNumber}")
    public ResponseEntity<String> cancelReservation(@PathVariable long pnrNumber) {
        cancellationService.cancelReservationByPnrNumber(pnrNumber);
        return ResponseEntity.ok("Reservation with PNR number " + pnrNumber + " has been cancelled.");
    }
}

