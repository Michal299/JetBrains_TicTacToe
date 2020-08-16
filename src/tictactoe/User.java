package tictactoe;

import java.util.Scanner;

public class User implements Player {

    private final char symbol;
    private final String nickname;
    private final Scanner input;

    public User(String nickname, char symbol) {
        this.nickname = nickname;
        this.symbol = symbol;
        this.input = new Scanner(System.in);
    }

    @Override
    public Point makeMove() {
        int x = input.nextInt();
        int y = input.nextInt();
        return new Point(x, y);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return nickname;
    }
}
