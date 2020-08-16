package tictactoe;

public class HardBot extends EasyBot implements Player  {

    public HardBot(char symbol, TicTacToe game) {
        super(symbol, game);
    }

    @Override
    public Point makeMove() {
        return findMove();
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return "hard";
    }

    private Point findMove() {
        int bestScore = -Integer.MAX_VALUE;
        Point bestMove = null;
        Point move;
        int score;

        for (int x = 1; x <= board.getWidth(); x++) {
            for (int y = 1; y <= board.getHeight(); y++) {
                move = new Point(x, y);
                if (board.getField(move) == Field.EMPTY) {

                    board.placeField(move, Field.getBySymbol(symbol));
                    game.updateStatus();

                    score = minimax(false);

                    board.eraseField(move);
                    game.updateStatus();

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        Field myField = Field.getBySymbol(symbol);
        Field opponentField = myField == Field.CROSS ? Field.CIRCLE : Field.CROSS;
        if (game.getStatus() == Status.DRAW) {
            return 0;
        } else {

            if (myField == Field.CIRCLE && game.getStatus() == Status.O_WIN) {
                return 1;
            } else if (myField == Field.CROSS && game.getStatus() == Status.X_WIN) {
                return 1;
            } else if (game.getStatus() != Status.NOT_END) {
                return -1;
            }

        }

        if (isMaximizing) {
            int bestScore = -Integer.MAX_VALUE;
            Point point;

            for (int x = 1; x <= board.getWidth(); x++) {
                for (int y = 1; y <= board.getHeight(); y++) {
                    point = new Point(x, y);
                    if (board.getField(point) == Field.EMPTY) {

                        board.placeField(point, myField);
                        game.updateStatus();

                        bestScore = Math.max(minimax(false), bestScore);

                        board.eraseField(point);
                        game.updateStatus();
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            Point point;

            for (int x = 1; x <= board.getWidth(); x++) {
                for (int y = 1; y <= board.getHeight(); y++) {
                    point = new Point(x, y);
                    if (board.getField(point) == Field.EMPTY) {

                        board.placeField(point, opponentField);
                        game.updateStatus();

                        bestScore = Math.min(minimax(true), bestScore);

                        board.eraseField(point);
                        game.updateStatus();
                    }
                }
            }
            return bestScore;
        }

    }

}

