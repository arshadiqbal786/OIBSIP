package com.onlinereservation.system.entity;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;


@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String trainName;
    private String classType;
    private LocalDate dateOfJourney;
    private String fromPlace;
    private String toDestination;


    @Column(unique = true)
    private Long pnrNumber;



}
