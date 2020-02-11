package hu.fcs.dojo.tictactoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;
import static java.lang.String.format;


/**
 * +---+---+---+
 * | 0 | 1 | 2 |
 * +---+---+---+
 * | 3 | 4 | 5 |
 * +---+---+---+
 * | 6 | 7 | 8 |
 * +---+---+---+
 */

public class Position {
    public static final int NUMBER_OF_FIELDS = 9;
    public static final int[][] LINES = new int[][] {
        new int[] {0, 1, 2},
        new int[] {3, 4, 5},
        new int[] {6, 7, 8},
        new int[] {0, 3, 6},
        new int[] {1, 4, 7},
        new int[] {2, 5, 8},
        new int[] {0, 4, 8},
        new int[] {2, 4, 6},
    };
    private Player[] fields;

    public static Position p(Player... fields) {
        return new Position(fields);
    }

    public Position(Player... fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Null fields is not allowed!");
        }
        if (fields.length == 0) {
            this.fields = new Player[] {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
        } else {
            if (fields.length != NUMBER_OF_FIELDS) {
                throw new IllegalArgumentException(format("Needs %d fields", NUMBER_OF_FIELDS));
            }
            this.fields = fields;
        }
    }

    public Player getWinner() {
        for (int[] line : LINES) {
            Set<Player> players = new HashSet<>();
            for (int index : line) {
                players.add(fields[index]);
            }
            if (players.size() > 1) {
                continue;
            }
            Player candidate = players.iterator().next();
            if (candidate.equals(EMPTY)) {
                continue;
            }
            return candidate;
        }
        return EMPTY;
    }

    public Position move(Player player, int index) {
        if (fields[index] != EMPTY) {
            throw new IllegalArgumentException("Move over other piece is not allowed!");
        }
        Player[] result = fields.clone();
        result[index] = player;
        return new Position(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Arrays.equals(fields, position.fields);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fields);
    }

    public Collection<Integer> getEmptyFields() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == EMPTY) {
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return format("+---+---+---+\n"
                + "| %s | %s | %s |\n"
                + "+---+---+---+\n"
                + "| %s | %s | %s |\n"
                + "+---+---+---+\n"
                + "| %s | %s | %s |\n"
                + "+---+---+---+",
            fields[0], fields[1], fields[2],
            fields[3], fields[4], fields[5],
            fields[6], fields[7], fields[8]);
    }
}
