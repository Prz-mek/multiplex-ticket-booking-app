package tech.multiplex.booking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ReservationRequestDTO {

    private Long screeningId;

    @NotNull
    @Size(min = 3, max = 50, message = "Too short first name (at least 3 characters)")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}*$", message = "First name have to start with capital letter")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 150, message = "Too short last name (at least 3 characters)")
    @Pattern(regexp = "(^\\p{Lu}\\p{Ll}*$)|(^\\p{Lu}\\p{Ll}{2,}-\\p{Lu}\\p{Ll}*$)", message = "Last name have to start with capital letter")
    private String lastName;

    private List<TicketDTO> tickets;
}
