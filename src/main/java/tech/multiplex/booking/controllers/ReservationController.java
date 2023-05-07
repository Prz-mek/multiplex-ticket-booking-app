package tech.multiplex.booking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.multiplex.booking.dto.ReservationRequestDTO;
import tech.multiplex.booking.dto.ReservationSummaryDTO;
import tech.multiplex.booking.dto.ScreeningDetailsDTO;
import tech.multiplex.booking.exceptions.ApiExceptionResponse;
import tech.multiplex.booking.exceptions.ReservationNotFoundException;
import tech.multiplex.booking.exceptions.ValidationErrorResponse;
import tech.multiplex.booking.services.ReservationService;

@RestController
@RequestMapping("api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @Operation(summary = "Make reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation made",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict with other reservation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiExceptionResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Some data are incorrect",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}),
            @ApiResponse(responseCode = "410", description = "It is to late for reservation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiExceptionResponse.class))})
    })
    @PostMapping
    public ResponseEntity<Long> makeReservation(@Valid @RequestBody ReservationRequestDTO reservationRequest) {
        final Long bookingId = reservationService.makeReservation(reservationRequest);
        return ResponseEntity.ok().body(bookingId);
    }

    @Operation(summary = "Get reservation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservationSummaryDTO.class))}),
            @ApiResponse(responseCode = "204", description = "Reservation not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiExceptionResponse.class))})
    })
    @GetMapping("/{id}")
    public ReservationSummaryDTO getReservationSummary(@PathVariable Long id) {
        try {
            return reservationService.getReservationSummary(id);
        } catch (ReservationNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
