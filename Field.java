package TicTacToe;

class Field {
    static char[][] field = { { 'B', 'B', 'B' }, { 'B', 'B', 'B' }, { 'B', 'B', 'B' } };

    public static char[][] get_field() {
        return field;
    }

    public static void update_field(char[][] new_field) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new_field[i][j];
            }
        }
    }
}