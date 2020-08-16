package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Input command: ");
            command = scanner.nextLine();
            String[] arguments = command.split(" ");
            if (isValid(arguments)) {

                if (arguments[0].equals("exit")) {
                    break;
                } else {

                    TicTacToe game = new TicTacToe();
                    game.addFirstPlayer(arguments[1], 'X');
                    game.addSecondPlayer(arguments[2], 'O');
                    game.startGame();

                    while (game.getStatus() == Status.NOT_END) {
                        game.drawBoard();
                        game.makeRound();
                    }

                    game.drawBoard();
                    System.out.println(game.getResult());
                }

            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static boolean isValid(String[] arguments) {
        if (arguments.length == 3) {
            if (!arguments[0].equals("start")) {
                return false;
            }

            return (arguments[1].equals("easy") || arguments[1].equals("user") || arguments[1].equals("medium") || arguments[1].equals("hard")) &&
                    (arguments[2].equals("easy") || arguments[2].equals("user") || arguments[2].equals("medium") || arguments[2].equals("hard"));

        } else if (arguments.length == 1) {
            return arguments[0].equals("exit");
        } else {
            return false;
        }
    }
}
