package tech.multiplex.booking.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.multiplex.booking.dto.ReservationRequestDTO;
import tech.multiplex.booking.dto.ReservationSummaryDTO;
import tech.multiplex.booking.exceptions.ReservationNotFoundException;
import tech.multiplex.booking.services.ReservationService;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @PostMapping
    public ResponseEntity<String> makeReservation(@Valid @RequestBody ReservationRequestDTO reservationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "))
            );
        }
        final Long bookingId = reservationService.makeReservation(reservationRequest);
        return ResponseEntity.ok().body(bookingId.toString());
    }

    @GetMapping("/{id}")
    public ReservationSummaryDTO getReservationSummary(@PathVariable Long id) {
        try {
            return reservationService.getReservationSummary(id);
        } catch (ReservationNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
