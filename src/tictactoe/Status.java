package tictactoe;

public enum Status {
    X_WIN("X wins"),
    O_WIN("O wins"),
    DRAW("Draw"),
    NOT_END("Game isn't over"),
    NOT_STARTED("Game doesn't start yet");

    String message;
    Status(String message) {
        this.message = message;
    }
}
