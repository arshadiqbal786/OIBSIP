package com.onlinereservation.system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// ReservationDTO.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String trainNumber;
    private String trainName;
    private String classType;
    private LocalDate dateOfJourney;
    private String fromPlace;
    private String toDestination;
    private long pnrNumber;

}