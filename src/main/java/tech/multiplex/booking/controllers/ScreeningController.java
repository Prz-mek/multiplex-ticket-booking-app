package tech.multiplex.booking.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.multiplex.booking.dto.ScreeningDTO;
import tech.multiplex.booking.dto.ScreeningDetailsDTO;
import tech.multiplex.booking.exceptions.ApiExceptionResponse;
import tech.multiplex.booking.services.ScreeningService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @Operation(summary = "Get screenings starting between specified time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Screening found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScreeningDTO.class))})
    })
    @GetMapping
    public List<ScreeningDTO> getScreenings(@Valid @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                            @Valid @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return screeningService.getScreeningsByDate(from, to);
    }

    @Operation(summary = "Get details of screening by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Screening found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScreeningDetailsDTO.class))}),
            @ApiResponse(responseCode = "204", description = "Screening not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiExceptionResponse.class))})
    })
    @GetMapping("/{id}")
    public ScreeningDetailsDTO getScreeningDetails(@PathVariable("id") Long id) {
        return screeningService.getScreeningDetails(id);
    }
}
