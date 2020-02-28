package TicTacToe;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public static boolean valid_input(char[][] field, int field_num) {
        int row = (field_num-1) / 3;
        int collumn = (field_num-1) % 3;

        if (field[row][collumn] == 'B') {
            return true;
        } 

        return false;
    }

    public static void print_array(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void print_array_char(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    public static int first_move(char[][] field) {
       /*
        *   Preverimo nevarnost neposredne naslednje uporabnikove poteze    
        */

        // preverimo ce lahko v naslednji potezi zmagamo 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'B') {
                    char[][] field_copy = copy_array(field);
                    field_copy[i][j] = 'P';

                    if (win_chech(field_copy) == 'C') {
                        return (i*3 + j);
                    }
                }    
            }
        }

        // pregledamo ce lahko uporabnik v naslednji potezi zmaga 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'B') {
                    char[][] field_copy = copy_array(field);
                    field_copy[i][j] = 'P';

                    if (win_chech(field_copy) == 'P') {
                        return (i*3 + j);
                    }
                }    
            }
        }
        return -1;
    }
    
    public static char[][] computer_turn_demo(char[][] field) {

        // naredimo novo tabelo ki bo vsebovala vrednosti zmag
        int[][] values = {{0,0,0},
                          {0,0,0},
                          {0,0,0}};

        // preverimo neposredno nevarnost naslednje poteze 
        // ustrezno ukrepamo 
        
        int first_move = first_move(field);
        System.out.println("First move " + first_move);
        if (first_move != -1) {
            System.out.println("Nevarnost");
            int i = first_move / 3;
            int j = first_move % 3;

            field[i][j] = 'C';
            return field;
        }
        

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // za vsako polje v tabeli preverimo koliko potencialnih zmag drzi 
                if (field[i][j] == 'B') {
                    char[][] field_copy = copy_array(field);
                    field_copy[i][j] = 'C';
                    values[i][j] = computer_turn_demo(field_copy, 'P', 1);
                }    
            }
        }

        print_array(values);

        int i_demo = 0;
        int j_demo = 0;
        int max_value = Integer.MIN_VALUE;

        // dolocimo katero polje je najbolj donosno
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (values[i][j] > max_value) {
                    if (field[i][j] == 'B') {
                        i_demo = i;
                        j_demo = j;
                        max_value = values[i][j];
                    }    
                }
            }
        }

        // ce ni nobeno polje dominantno uporabimo prvo blank polje 
        if (max_value == Integer.MIN_VALUE) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == 'B') {
                        field[i][j] = 'C';
                        System.out.println("Racunalnik se je odlocil za "+ i + " "+ j);
                        System.out.println("Ni druge izbire");
                        //MyFrame.change_field(i*3 + j, 'C');
                        print_field(field);
                        return field;
                    }
                }
            }
        }

        // drugace uporabimo polje ki je dominantno 
        field[i_demo][j_demo] = 'C';
        System.out.println("Racunalnik se je odlocil za "+ i_demo + " "+ j_demo);
        print_field(field);
        return field;
    }

    private static int computer_turn_demo(char[][] field, char poteza, int niz) {

        // preverimo ce je igre ze konec
        // v tem primeru koncamo rekurzijo 
        if (win_chech(field) == 'C') {
            return (10 - niz);
        } else if (win_chech(field) == 'P') {
            return (10 - niz);
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
                    value += computer_turn_demo(field_copy, naslednja_poteza, niz + 1);
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
            if (field[i][0] == field[i][1] &&  field[i][1] == field[i][2] && field[i][0] != 'B') {
                return field[i][0];
            }
        }

        // check collumns
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != 'B') {
                return field[0][i];
            }
        }

        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != 'B') {
            return field[0][0];
        }

        if (field[2][0] == field[1][1] && field[1][1] == field[0][2] && field[2][0] != 'B') {
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

        new MyFrame();
    }
}