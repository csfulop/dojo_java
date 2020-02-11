package hu.fcs.dojo.tictactoe;

import java.util.Optional;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;


public class Strategy {

    public int getMoveFor(Player player, Position position) {
        validatePosition(position);
        Optional<Integer> move;
        move = searchWinnerMoves(player, position);
        if (move.isPresent()) {
            return move.get();
        }
        move = searchSaverMoves(player, position);
        if (move.isPresent()) {
            return move.get();
        }

        return position.getEmptyFields().iterator().next();
    }

    private void validatePosition(Position position) {
        if (!position.getWinner().equals(EMPTY)) {
            throw new IllegalArgumentException("No more moves for a finished position");
        }
        if (position.getEmptyFields().size() == 0) {
            throw new IllegalArgumentException("No more moves for a full table");
        }
    }

    private Optional<Integer> searchWinnerMoves(Player player, Position position) {
        for (int index : position.getEmptyFields()) {
            if (position.move(player, index).getWinner().equals(player)) {
                return Optional.of(index);
            }
        }
        return Optional.empty();
    }

    private Optional<Integer> searchSaverMoves(Player player, Position position) {
        for (int index : position.getEmptyFields()) {
            Player nextPlayer = player.getNext();
            if (position.move(nextPlayer, index).getWinner().equals(nextPlayer)) {
                return Optional.of(index);
            }
        }
        return Optional.empty();
    }
}
