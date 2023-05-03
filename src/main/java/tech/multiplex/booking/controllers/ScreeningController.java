package tech.multiplex.booking.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tech.multiplex.booking.dto.ScreeningDTO;
import tech.multiplex.booking.dto.ScreeningDetailsDTO;
import tech.multiplex.booking.services.ScreeningService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @GetMapping
    public List<ScreeningDTO> getScreenings(@RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        try {
            return screeningService.getScreeningsByDate(from, to);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public ScreeningDetailsDTO getScreeningDetails(@PathVariable("id") Long id) {
        try {
            return screeningService.getScreeningDetails(id);
        } catch (Exception e) {
            return null;
        }
    }
}
