package tictactoe;

public interface Player {
    Point makeMove();
    char getSymbol();
    String getName();
}
