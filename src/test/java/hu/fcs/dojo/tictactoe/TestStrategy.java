package hu.fcs.dojo.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;
import static hu.fcs.dojo.tictactoe.Player.O;
import static hu.fcs.dojo.tictactoe.Player.X;
import static hu.fcs.dojo.tictactoe.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestStrategy {
    Strategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new Strategy();
    }

    @Test
    void testMoveForFinishedGameIsNotAllowed() {
        assertThrows(
            IllegalArgumentException.class,
            () -> strategy.getMoveFor(O, p(O, O, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY))
        );
    }

    @Test
    void testMoveForFullTableIsNotAllowed() {
        assertThrows(
            IllegalArgumentException.class,
            () -> strategy.getMoveFor(O, p(O, O, X, X, X, O, O, X, X))
        );
    }

    @Test
    void testWinnerMove() {
        assertThat(
            strategy.getMoveFor(O, p(O, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)),
            is(2)
        );
    }

    @Test
    void testSaverMove() {
        assertThat(
            strategy.getMoveFor(X, p(O, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)),
            is(2)
        );
    }
}
