package tech.multiplex.booking.mappers;

import tech.multiplex.booking.domain.Screening;
import tech.multiplex.booking.dto.ScreeningDTO;

import java.util.List;

public class ScreeningMapper {
    public static ScreeningDTO map(Screening screening) {
        return new ScreeningDTO(screening.getId(), screening.getMovie().getTitle(), screening.getStartTime());
    }

    public static List<ScreeningDTO> map(List<Screening> screenings) {
        return screenings.stream().map(ScreeningMapper::map).toList();
    }
}
