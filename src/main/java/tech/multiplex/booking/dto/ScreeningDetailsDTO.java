package tech.multiplex.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningDetailsDTO {
    private Long id;
    private String movieTitle;
    private LocalDateTime startTime;
    private String screeningRoom;
    private List<SeatDTO> availableSeats;
}
