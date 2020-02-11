package hu.fcs.dojo.tictactoe;

import org.junit.jupiter.api.Test;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;
import static hu.fcs.dojo.tictactoe.Player.O;
import static hu.fcs.dojo.tictactoe.Player.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestPlayer {
    @Test
    void testNextPlayer() {
        assertThat(X.getNext(), is(O));
        assertThat(O.getNext(), is(X));
        assertThrows(UnsupportedOperationException.class, () -> EMPTY.getNext());
    }
}
