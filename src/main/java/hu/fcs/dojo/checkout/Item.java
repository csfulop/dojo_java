package hu.fcs.dojo.checkout;

import lombok.Data;

import java.math.BigDecimal;

@Data(staticConstructor = "item")
public class Item {
    private final String name;
    private final String description;
    private final BigDecimal price;
}
