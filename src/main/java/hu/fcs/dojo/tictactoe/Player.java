package hu.fcs.dojo.tictactoe;

public enum Player {
    O("X"),
    X("O"),
    EMPTY("EMPTY") {
        @Override
        public String toString() {
            return " ";
        }

        @Override
        public Player getNext() {
            throw new UnsupportedOperationException();
        }
    };

    private final String next;

    Player(String next) {
        this.next = next;
    }

    public Player getNext() {
        return Player.valueOf(next);
    }
}
