import java.util.Scanner;

class TicTac {
    
    public static void print_field(char[][] field) {

        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (field[i][j] == 'B') {
                    System.out.print(' ');
                } else {
                    System.out.print(field[i][j]);
                }
                
                if (j != 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();

            if (i != 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    public static char[][] user_turn(char[][] field, Scanner sc) {

        System.out.println("Your turn: [1-9]");

        String input = sc.nextLine();
        int field_num = Integer.parseInt(input);

        if (!valid_input(field, field_num)) {
            return user_turn(field, sc);
        }

        int row = (field_num-1) / 3;
        int collumn = (field_num-1) % 3;

        field[row][collumn] = 'P';
        print_field(field);

        return field;
    }

    public static boolean valid_input(char[][] field, int field_num) {
        int row = (field_num-1) / 3;
        int collumn = (field_num-1) % 3;

        if (field[row][collumn] == 'B') {
            return true;
        } 

        return false;
    }

    public static char[][] computer_turn(char[][] field) {

        // Demo 
        // TODO

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'B') {
                    field[i][j] = 'C';

                    print_field(field);
                    return field;
                }
            }
        }

        return null;
    }

    public static char[][] computer_turn_demo(char[][] field) {
        // TODO

        // naredimo novo tabelo ki bo vsebovala vrednosti zmag
        int[][] values = {{0,0,0},
                          {0,0,0},
                          {0,0,0}};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // za vsako polje v tabeli preverimo koliko potencialnih zmag drzi 
                if (field[i][j] == 'B') {
                    char[][] field_copy = copy_array(field);
                    field_copy[i][j] = 'C';
                    values[i][j] = computer_turn_demo(field_copy, 'P');
                }    
            }
        }

        int i_demo = 0;
        int j_demo = 0;
        int max_value = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (values[i][j] > max_value) {
                    i_demo = i;
                    j_demo = j;
                    max_value = values[i][j];
                }
            }
        }

        if (max_value == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == 'B') {
                        field[i][j] = 'C';
                        System.out.println("Racunalnik se je odlocil za "+ i + " "+ j);
                        print_field(field);
                        return field;
                    }
                }
            }
        }

        field[i_demo][j_demo] = 'C';
        System.out.println("Racunalnik se je odlocil za "+ i_demo + " "+ j_demo);
        print_field(field);
        return field;
    }

    private static int computer_turn_demo(char[][] field, char poteza) {

        // preverimo ce je igre ze konec
        // v tem primeru koncamo rekurzijo 
        if (win_chech(field) == 'C') {
            return 1;
        } else if (win_chech(field) == 'P') {
            return 0;
        } else if (win_chech(field) == 'N') {
            return 0;
        }

        int value = 0;
        char naslednja_poteza = poteza; 

        // nastavimo naslednjo potezo [P/C]
        if (poteza == 'C') {
            naslednja_poteza = 'P';
        } else {
            naslednja_poteza = 'C';
        }

        // za vsako prazno polje klicemo novo rekurzijo
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'B') {
                    char[][] field_copy = copy_array(field);
                    field_copy[i][j] = poteza;
                    value += computer_turn_demo(field_copy, naslednja_poteza);
                }
            }
        }

        return value;
    }
   
    public static char[][] copy_array(char[][] field) {
        char[][] field_copy = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field_copy[i][j] = field[i][j];
            }
        }

        return field_copy;
    }

    public static char win_chech(char[][] field) {

        /*
        *  Check if the game is over and return the winner
        *  if there is no winner, return B
        */ 

        // check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] &&  field[i][1] == field[i][2]) {
                return field[i][0];
            }
        }

        // check collumns
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                return field[0][i];
            }
        }

        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return field[0][0];
        }

        if (field[2][0] == field[1][1] && field[1][1] == field[0][2]) {
            return field[2][0];
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'B') {
                    return 'B';
                }
            }
        }
        return 'N';
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] field = {{'B', 'B', 'B'},
                          {'B', 'B', 'B'},
                          {'B', 'B', 'B'}};

        while (true) {
            field = user_turn(field, sc);

            if (!(win_chech(field) == 'B')) {
                break;
            }

            field = computer_turn_demo(field);

            if (!(win_chech(field) == 'B')) {
                break;
            }
        }

        if (win_chech(field) == 'P') {
            System.out.println("Zmagali ste!");
        } else if (win_chech(field) == 'C') {
            System.out.println("Zmagal je racunalnik!");
        } else if (win_chech(field) == 'N') {
            System.out.println("Neodloceno! Poskusi ponovno");
        } else {
            System.out.println("Neodloceno!");
        }
    }
}