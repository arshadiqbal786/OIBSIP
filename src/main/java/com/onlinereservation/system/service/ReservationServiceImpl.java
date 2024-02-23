package com.onlinereservation.system.service;

import com.onlinereservation.system.entity.Reservation;
import com.onlinereservation.system.payload.ReservationDTO;
import com.onlinereservation.system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        // Generate PNR number
        long pnrNumber = generateNumericPnrNumber();

        // Convert DTO to entity
        Reservation reservation = convertDtoToEntity(reservationDTO);

        // Set the generated PNR number
        reservation.setPnrNumber(pnrNumber);

        // Save reservation entity to database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Convert saved entity back to DTO
        return convertEntityToDto(savedReservation);
    }

    // Method to convert ReservationDTO to Reservation entity
    private Reservation convertDtoToEntity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setTrainNumber(dto.getTrainNumber());
        reservation.setTrainName(dto.getTrainName());
        reservation.setClassType(dto.getClassType());
        reservation.setDateOfJourney(dto.getDateOfJourney());
        reservation.setFromPlace(dto.getFromPlace());
        reservation.setToDestination(dto.getToDestination());
        return reservation;
    }

    // Method to convert Reservation entity to ReservationDTO
    private ReservationDTO convertEntityToDto(Reservation reservation) {
        if (reservation == null) {
            return null; // Or throw an exception, depending on your requirements
        }
        ReservationDTO dto = new ReservationDTO();
        dto.setTrainNumber(reservation.getTrainNumber());
        dto.setTrainName(reservation.getTrainName());
        dto.setClassType(reservation.getClassType());
        dto.setDateOfJourney(reservation.getDateOfJourney());
        dto.setFromPlace(reservation.getFromPlace());
        dto.setToDestination(reservation.getToDestination());
        dto.setPnrNumber(reservation.getPnrNumber());
        return dto;
    }

    // Method to generate random long PNR number
    private long generateNumericPnrNumber() {
        return Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }
}
