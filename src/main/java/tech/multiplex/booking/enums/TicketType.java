package tech.multiplex.booking.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public enum TicketType {
    ADULT("Adult", new BigDecimal(25.0)),
    STUDENT("Student", new BigDecimal(18.0)),
    CHILD("Child", new BigDecimal(12.5));

    private final String name;
    private final BigDecimal price;

    TicketType(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @JsonCreator
    public static TicketType fromText(String text) {
        if (text.equals(ADULT.getName())) {
            return ADULT;
        }

        if (text.equals(STUDENT.getName())) {
            return STUDENT;
        }

        return CHILD;
    }
}
