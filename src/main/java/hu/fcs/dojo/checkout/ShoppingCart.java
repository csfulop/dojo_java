package hu.fcs.dojo.checkout;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class ShoppingCart {
    private ItemAdapter itemAdapter;

    public ShoppingCart(ItemAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
    }

    public BigDecimal checkout() {
        List<Item> items = itemAdapter.getItems();
        BigDecimal result = ZERO;
        for (Item item : items) {
            result = result.add(item.getPrice());
        }
        return result;
    }
}
