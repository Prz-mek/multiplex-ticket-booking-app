package tech.multiplex.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningDTO {
    private Long id;
    private String title;
    private LocalDateTime startTime;
}
