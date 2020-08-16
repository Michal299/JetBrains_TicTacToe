package tictactoe;

public class MediumBot extends EasyBot {

    public MediumBot(char symbol, TicTacToe game) {
        super(symbol, game);
    }

    @Override
    public Point makeMove() {
        Point move;

        move = winPoint();
        if (move != null) {
            return move;
        }

        move = counterMove();
        if(move != null) {
            return move;
        }

        return super.makeMove();
    }

    private Point winPoint() {
        return findComplement(Field.getBySymbol(symbol));
    }

    private Point counterMove() {


        return Field.getBySymbol(symbol) == Field.CIRCLE ?
                findComplement(Field.CROSS) :
                findComplement(Field.CIRCLE);
    }

    private Point findComplement(Field type) {

        int numOfFreeFields = board.getHeight() * board.getWidth() - board.getOCounter() - board.getXCounter();

        if (numOfFreeFields <= 1) {
            return null;
        }

        Point[] freePoints = board.getFreePositions();

        int before;
        int after;
        Point point;
        for (int i = 0; i < numOfFreeFields; i++) {
            point = freePoints[i];

            before = game.findLines(type, 3);
            board.placeField(point, type);
            after = game.findLines(type, 3);

            board.eraseField(point);

            if (after != before) {
                return point;
            }
        }

        return null;
    }
    @Override
    public String getName() {
        return "medium";
    }
}
