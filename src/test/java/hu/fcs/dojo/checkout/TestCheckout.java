package hu.fcs.dojo.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hu.fcs.dojo.checkout.Item.item;
import static java.math.BigDecimal.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCheckout {
    ShoppingCart cart;
    ItemAdapter itemAdapter;

    @BeforeEach
    void setUp() {
        itemAdapter = mock(ItemAdapter.class);
        cart = new ShoppingCart(itemAdapter);
    }

    @Test
    void testCheckoutEmptyCart() {
        assertThat(cart.checkout(), is(ZERO));
    }

    @Test
    void testCheckoutOneItem() {
        when(itemAdapter.getItems())
                .thenReturn(asList(item("pen", "blue", ONE)));
        assertThat(cart.checkout(), is(ONE));
    }

    @Test
    void testCheckoutTwoItems() {
        when(itemAdapter.getItems())
                .thenReturn(asList(
                        item("pen", "blue", ONE),
                        item("paper", "white", ONE)
                ));
        assertThat(cart.checkout(), is(valueOf(2)));
    }
}
