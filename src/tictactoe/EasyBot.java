package tictactoe;

import java.util.Random;

public class EasyBot implements Player {

    protected Board board;
    protected char symbol;
    protected final TicTacToe game;
    private final Random generator;

    public EasyBot(char symbol, TicTacToe game) {
        this.symbol = symbol;
        this.board = game.board;
        this.game = game;
        generator = new Random();
    }

    @Override
    public Point makeMove() {
        int x;
        int y;
        do {
            x = generator.nextInt(board.getWidth()) + 1;
            y = generator.nextInt(board.getHeight()) + 1;
        } while (board.getField(new Point(x, y)) != Field.EMPTY);

        return new Point(x, y);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return "easy";
    }
}
