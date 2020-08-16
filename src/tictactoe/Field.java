package tictactoe;

public enum Field {
    CROSS('X'),
    CIRCLE('O'),
    EMPTY('_');

    char symbol;

    Field(char symbol) {
        this.symbol = symbol;
    }

    public static Field getBySymbol(char symbol) {
        switch (symbol) {
            case 'X':
                return Field.CROSS;
            case 'O':
                return Field.CIRCLE;
            default:
                return Field.EMPTY;
        }
    }

}
