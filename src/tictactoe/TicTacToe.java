package tictactoe;

public class TicTacToe {
    Board board;
    Status status;
    Player first;
    Player second;

    int roundNumber;

    TicTacToe() {
        status = Status.NOT_STARTED;
        board = new Board(3, 3);
        roundNumber = 0;
    }

    public void startGame() {
        if (status == Status.NOT_STARTED) {
            status = Status.NOT_END;
        }
    }

    public void drawBoard() {
        for (int i = 0; i < board.getWidth() * board.getHeight(); i++) {
            System.out.print('-');
        }
        System.out.println();

        for (int y = board.getHeight(); y > 0 ; y--) {
            System.out.print("| ");
            for (int x = 1; x <= board.getWidth(); x++) {
                System.out.print(board.getField(new Point(x, y)).symbol + " ");
            }
            System.out.println("|");
        }

        for (int i = 0; i < board.getWidth() * board.getHeight(); i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public void makeRound() {

        roundNumber++;

        Point point;
        char toPut;
        Player makingMove;

        if (roundNumber % 2 == 1) {
            makingMove = first;
        } else {
            makingMove = second;
        }
        toPut = makingMove.getSymbol();


        do {

            if (makingMove instanceof User) {
                System.out.print("Enter the coordinates: ");
            } else {
                System.out.println("Making move level \"" + makingMove.getName() + "\"");
            }

            point = makingMove.makeMove();

        } while (!board.placeField(point, Field.getBySymbol(toPut)));

        updateStatus();

    }

    public Status getStatus() {
        return status;
    }

    public String getResult() {
        return status.message;
    }

    public void updateStatus() {
        int xRows = findLines(Field.CROSS, 3);
        int yRows = findLines(Field.CIRCLE, 3);
        int freeFields = (board.getHeight() * board.getWidth()) - (board.getXCounter() + board.getOCounter());
        if (xRows == 0 && yRows == 0) {
            if (freeFields > 0) {
                status = Status.NOT_END;
            } else {
                status = Status.DRAW;
            }
        } else {
            status = xRows > 0 ? Status.X_WIN : Status.O_WIN;
        }
    }

    public int findLines(Field field, int lineLength) {
        int result = 0;

        int inVerticalRow;
        int inHorizontalRow;

        for (int k = 1; k <= board.getWidth(); k++) {
            inVerticalRow = 0;
            inHorizontalRow = 0;
            for (int l = 1; l <= board.getHeight(); l++) {
                if (board.getField(new Point(k, l)) == field) {
                    inVerticalRow++;
                } else {
                    inVerticalRow = 0;
                }

                if (board.getField(new Point(l, k)) == field) {
                    inHorizontalRow++;
                } else {
                    inHorizontalRow = 0;
                }

                if (inVerticalRow == lineLength) {
                    inVerticalRow = 0;
                    result++;
                }

                if (inHorizontalRow == lineLength) {
                    inHorizontalRow = 0;
                    result++;
                }
            }
        }

        int inFirstDiagonal = 0;
        int inSecondDiagonal = 0;

        for (int x = 1; x <= board.getWidth(); x++) {
            for (int y = 1; y <= board.getHeight(); y++) {

                if (x == y) {
                    if (board.getField(new Point(x, y)) == field) {
                        inFirstDiagonal++;
                    } else {
                        inFirstDiagonal = 0;
                    }
                }

                if (x == board.getHeight() - y + 1) {
                    if (board.getField(new Point(x, y)) == field) {
                        inSecondDiagonal++;
                    } else {
                        inSecondDiagonal = 0;
                    }
                }

                if (inFirstDiagonal == lineLength) {
                    result++;
                    inFirstDiagonal = 0;
                }

                if (inSecondDiagonal == lineLength) {
                    result++;
                    inSecondDiagonal = 0;
                }
            }
        }

        return result;
    }

    public void addFirstPlayer(String playerCode, char playerSymbol) {
        Player player = getPlayer(playerCode, playerSymbol);
        if (player != null) {
            first = player;
        } else {
            System.out.println("There is no such player type.");
        }
    }

    public void addSecondPlayer(String playerCode, char playerSymbol) {
        Player player = getPlayer(playerCode, playerSymbol);
        if (player != null) {
            second = player;
        } else {
            System.out.println("There is no such player type.");
        }
    }

    private Player getPlayer(String code, char symbol) {
        switch (code) {
            case "user":
                return new User("user", symbol);
            case "easy":
                return new EasyBot(symbol, this);
            case "medium":
                return new MediumBot(symbol, this);
            case "hard":
                return new HardBot(symbol, this);
            default:
                return null;
        }
    }
}
