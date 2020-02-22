import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame {
    static JFrame frame = new JFrame("Tic Tac Toe");

    static JButton button1 = new JButton(" ");
    static JButton button2 = new JButton(" ");
    static JButton button3 = new JButton(" ");
    static JButton button4 = new JButton(" ");
    static JButton button5 = new JButton(" ");
    static JButton button6 = new JButton(" ");
    static JButton button7 = new JButton(" ");
    static JButton button8 = new JButton(" ");
    static JButton button9 = new JButton(" ");

    static JButton[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };

    public static void set_listeners() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(1);
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(2);
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(3);
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(4);
            }
        });
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(5);
            }
        });
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(6);
            }
        });
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(7);
            }
        });
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(8);
            }
        });
        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTac.user_input_demo(9);
            }
        });
    }

    public static void add_to_frame() {
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
    }

    public static void set_frame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));
        frame.setVisible(true);
    }

    public static void change_field(int num, char poteza) {
        buttons[num].setText(String.valueOf(poteza));
    }
}