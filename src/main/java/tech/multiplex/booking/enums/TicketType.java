package tech.multiplex.booking.enums;

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

}
