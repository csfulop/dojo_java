package hu.fcs.dojo.tictactoe;

import org.junit.jupiter.api.Test;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;
import static hu.fcs.dojo.tictactoe.Player.O;
import static hu.fcs.dojo.tictactoe.Player.X;
import static hu.fcs.dojo.tictactoe.Position.p;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestPosition {
    Position position;

    @Test
    void testNullFieldsAreNotAllowed() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Position(null)
        );
    }

    @Test
    void testNoWinnerYet() {
        position = new Position();
        assertThat(position.getWinner(), is(EMPTY));
    }

    @Test
    void testIncorectNumberOfFields() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Position(O, O, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, X)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Position(O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, X)
        );
    }

    @Test
    void testWinner() {
        assertWinner(O, p(O, O, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));
        assertWinner(O, p(EMPTY, EMPTY, EMPTY, O, O, O, EMPTY, EMPTY, EMPTY));
        assertWinner(O, p(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, O, O, O));
        assertWinner(O, p(O, EMPTY, EMPTY, O, EMPTY, EMPTY, O, EMPTY, EMPTY));
        assertWinner(O, p(EMPTY, O, EMPTY, EMPTY, O, EMPTY, EMPTY, O, EMPTY));
        assertWinner(O, p(EMPTY, EMPTY, O, EMPTY, EMPTY, O, EMPTY, EMPTY, O));
        assertWinner(O, p(O, EMPTY, EMPTY, EMPTY, O, EMPTY, EMPTY, EMPTY, O));
        assertWinner(O, p(EMPTY, EMPTY, O, EMPTY, O, EMPTY, O, EMPTY, EMPTY));
        assertWinner(X, p(X, X, X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));
        assertWinner(X, p(EMPTY, EMPTY, EMPTY, X, X, X, EMPTY, EMPTY, EMPTY));
        assertWinner(X, p(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, X, X, X));
        assertWinner(X, p(X, EMPTY, EMPTY, X, EMPTY, EMPTY, X, EMPTY, EMPTY));
        assertWinner(X, p(EMPTY, X, EMPTY, EMPTY, X, EMPTY, EMPTY, X, EMPTY));
        assertWinner(X, p(EMPTY, EMPTY, X, EMPTY, EMPTY, X, EMPTY, EMPTY, X));
        assertWinner(X, p(X, EMPTY, EMPTY, EMPTY, X, EMPTY, EMPTY, EMPTY, X));
        assertWinner(X, p(EMPTY, EMPTY, X, EMPTY, X, EMPTY, X, EMPTY, EMPTY));
    }

    private void assertWinner(Player expectedWinner, Position position) {
        assertThat(position.getWinner(), is(expectedWinner));
    }

    @Test
    void testMove() {
        assertThat(
            new Position().move(O, 0),
            is(p(O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY))
        );
    }

    @Test
    void testMoveOverOtherPieceIsNotAllowed() {
        assertThrows(
            IllegalArgumentException.class,
            () -> p(O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY).move(X, 0)
        );
    }

    @Test
    void testGetEmptyFields() {
        assertThat(
            p(EMPTY, EMPTY, X, EMPTY, X, EMPTY, X, EMPTY, EMPTY).getEmptyFields(),
            is(asList(0, 1, 3, 5, 7, 8))
        );
        assertThat(
            p(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY).getEmptyFields(),
            is(asList(0, 1, 2, 3, 4, 5, 6, 7, 8))
        );
        assertThat(
            p(X, X, X, X, X, X, X, X, X).getEmptyFields(),
            is(asList())
        );
    }
}
