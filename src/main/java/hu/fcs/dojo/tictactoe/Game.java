package hu.fcs.dojo.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static hu.fcs.dojo.tictactoe.Player.EMPTY;
import static hu.fcs.dojo.tictactoe.Player.X;
import static java.lang.String.format;


public class Game {
    private Strategy strategy = new Strategy();
    private Position position = new Position();
    private Player player = X;

    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (position.getEmptyFields().size() > 0 && position.getWinner().equals(EMPTY)) {
            System.out.println(position);
            System.out.println(format("Next player is: %s", player));
            System.out.println(format("I suggest move: %d", strategy.getMoveFor(player, position)));
            System.out.println("Your move? ");
            String in = reader.readLine();
            Integer index = Integer.valueOf(in);
            position = position.move(player, index);
            player = player.getNext();
        }
        if (!position.getWinner().equals(EMPTY)) {
            System.out.println(position);
            System.out.println(format("The winner is: %s", player.getNext()));
        } else {
            System.out.println("The game is draw!");
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}
