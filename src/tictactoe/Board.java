package tictactoe;

public class Board {
    private final Field[][] fields;
    private final int width;
    private final int height;
    private int xCounter;
    private int oCounter;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new Field[width][height];
        clearBoard();
    }

    public Field getField(Point position) {
        return fields[position.getX() - 1][height - position.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXCounter() {
        return xCounter;
    }

    public int getOCounter() {
        return oCounter;
    }

    public boolean placeField(Point position, Field field) {

        if (    position.getX() > 0
                && position.getX() <= width
                && position.getY() > 0
                && position.getY() <= height
                && fields[position.getX() - 1][height - position.getY()] == Field.EMPTY) {

            fields[position.getX() - 1][height - position.getY()] = field;
            if (field == Field.CIRCLE) {
                oCounter++;
            } else if (field == Field.CROSS) {
                xCounter++;
            }

            return true;
        } else {
            int x = position.getX() - 1;
            int y = height - position.getY();
            if (x < 0 || x >= width || y < 0 || y >= height) {
                System.out.println("Coordinates should be from 1 to " + width + "!");
            } else if (fields[x][y] != Field.EMPTY) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            return false;
        }
    }

    public void eraseField(Point position) {
        int x = position.getX() - 1;
        int y = height - position.getY();

        if (fields[x][y] == Field.CIRCLE) {
            oCounter--;
        } else if (fields[x][y] == Field.CROSS) {
            xCounter--;
        }

        fields[x][y] = Field.EMPTY;
    }

    public void clearBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                fields[x][y] = Field.EMPTY;
            }
        }
        xCounter = 0;
        oCounter = 0;
    }

    public Point[] getFreePositions() {
        int numOfFreeFields = this.getHeight() * this.getWidth() - this.getOCounter() - this.getXCounter();

        if (numOfFreeFields < 1) {
            return null;
        }

        Point[] freePoints = new Point[numOfFreeFields];
        int freeIndex = 0;
        for (int x = 1; x <= this.getWidth(); x++) {
            for (int y = 1; y <= this.getHeight(); y++) {

                Point point = new Point(x, y);
                if (this.getField(point) == Field.EMPTY) {
                    freePoints[freeIndex] = point;
                    freeIndex++;
                }
            }
        }

        return freePoints;
    }
}
