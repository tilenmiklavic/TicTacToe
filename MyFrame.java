package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    static JButton b1;
    static JButton b2;
    static JButton b3;
    static JButton b4;
    static JButton b5;
    static JButton b6;
    static JButton b7;
    static JButton b8;
    static JButton b9;
    static JButton[] buttons = new JButton[9];

    static Label text;
    static JButton reset;

    MyFrame() {
        b1 = new JButton(" ");
        b2 = new JButton(" ");
        b3 = new JButton(" ");
        b4 = new JButton(" ");
        b5 = new JButton(" ");
        b6 = new JButton(" ");
        b7 = new JButton(" ");
        b8 = new JButton(" ");
        b9 = new JButton(" ");
        text = new Label("");
        reset = new JButton("Resetiraj");

        buttons[0] = b1; buttons[1] = b2; buttons[2] = b3; buttons[3] = b4; buttons[4] = b5; buttons[5] = b6; buttons[6] = b7; buttons[7] = b8; buttons[8] = b9;

        // (oddaljenost od L roba, oddaljenost od Z roba, velikost x, velikost y)
        b1.setBounds(0, 0, 100, 100);
        b2.setBounds(100, 0, 100, 100);
        b3.setBounds(200, 0, 100, 100);
        b4.setBounds(0, 100, 100, 100);
        b5.setBounds(100, 100, 100, 100);
        b6.setBounds(200, 100, 100, 100);
        b7.setBounds(0, 200, 100, 100);
        b8.setBounds(100, 200, 100, 100);
        b9.setBounds(200, 200, 100, 100);
        text.setBounds(15,275,140,100);
        reset.setBounds(160,300,140,50);

        add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);
        add(text);add(reset);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(1);

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(2);
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(3);

            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(4);

            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(5);

            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(6);

            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(7);

            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(8);

            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_turn(9);

            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset_game();
            }
        });

        setSize(300, 370);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static char[][] update_field(char[][] field, int field_num, char poteza) {
        int i = (field_num) / 3;
        int j = field_num % 3;

        field[i][j] = poteza;

        return field;
    }

    public static void update_buttons(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (field[i][j] != 'B') {
                    int num = i * 3 + j;
                    buttons[num].setText(String.valueOf(field[i][j]));
                }
            }
        }
    }

    public static void win(char winner) {
        if (winner == 'P') {
            text.setText("Zmagali ste!");
        } else if (winner == 'C') {
            text.setText("Zmagal je racunalnik!");
        } else {
            text.setText("Neodloceno!");
        }

        reset.setText("Poskusi ponovno");
    }

    public static void reset_game() {
        Field.reset_field();
        reset_buttons();
        text.setText("");
        reset.setText("Resetiraj");

    }

    public static void reset_buttons() {
        for (int i = 0 ; i < 9; i++) {
            buttons[i].setText("");
        }
    }

    public static void user_turn(int num) {
        System.out.println(num);
        char[][] field = Field.get_field();

        // check if the input is valid and add the input to the board 
        if (TicTac.valid_input(field, num)) {
            field = update_field(field, num-1, 'P');
            update_buttons(field);
        } else {
            return;
        }
        
        char c = TicTac.win_chech(field);
        if (c == 'P') {
            System.out.println("Zmagali ste!");
            win('P');
            return;
        } else if (c == 'N') {
            System.out.println("Neodloceno!");
            win('N');
            return;
        }
        // get computer input
        field = TicTac.computer_turn_demo(field);
        update_buttons(field);
        c = TicTac.win_chech(field);

        if (c == 'C') {
            System.out.println("Zmagal je racunalnik!");
            win('C');
            return;
        } else if (c == 'N') {
            System.out.println("Neodloceno!");
            win('N');
            return;
        }

        // update the currect working field
        Field.update_field(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}