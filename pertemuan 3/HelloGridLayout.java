import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridLayout extends JFrame implements ActionListener {
    private JButton[] buttons;
    private boolean gameOver;

    public HelloGridLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 3));

        gameOver = false;
        buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        this.setSize(300, 300);
    }

    public void actionPerformed(ActionEvent e) {
        // Jika permainan sudah berakhir, maka tidak ada tindakan yang dilakukan
        if (gameOver) {
            return;
        }

        // Mendapatkan tombol yang ditekan
        JButton button = (JButton) e.getSource();

        // Jika teks pada tombol kosong (belum diisi "X" atau "O")
        if (button.getText().isEmpty()) {
            // Mengisi tombol dengan "O"
            button.setText("O");

            // Memeriksa apakah ada pemenang setelah giliran pemain "O"
            checkWinner();

            // Jika belum ada pemenang, maka giliran pemain "X"
            if (!gameOver) {
                // Melakukan perulangan untuk mencari tombol kosong
                for (int i = 0; i < buttons.length; i++) {
                    if (buttons[i].getText().isEmpty()) {
                        // Mengisi tombol kosong pertama yang ditemukan dengan "X"
                        buttons[i].setText("X");
                        break; // Keluar dari perulangan setelah menemukan tombol kosong
                    }
                }
                // Memeriksa apakah ada pemenang setelah giliran pemain "X"
                checkWinner();
            }
        }
    }

    private void checkWinner() {
        String winner = "";

        // Cek baris
        for (int i = 0; i < 3; i++) {
            if (!buttons[i * 3].getText().isEmpty() &&
                buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText()) &&
                buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText())) {
                winner = buttons[i * 3].getText();
                buttons[i * 3].setForeground(Color.RED);
                buttons[i * 3 + 1].setForeground(Color.RED);
                buttons[i * 3 + 2].setForeground(Color.RED);
            }
        }

        // Cek kolom
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() &&
                buttons[i].getText().equals(buttons[i + 3].getText()) &&
                buttons[i].getText().equals(buttons[i + 6].getText())) {
                winner = buttons[i].getText();
                buttons[i].setForeground(Color.RED);
                buttons[i + 3].setForeground(Color.RED);
                buttons[i + 6].setForeground(Color.RED);
            }
        }

        // Cek diagonal
        if (!buttons[0].getText().isEmpty() &&
            buttons[0].getText().equals(buttons[4].getText()) &&
            buttons[0].getText().equals(buttons[8].getText())) {
            winner = buttons[0].getText();
            buttons[0].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[8].setForeground(Color.RED);
        } else if (!buttons[2].getText().isEmpty() &&
                   buttons[2].getText().equals(buttons[4].getText()) &&
                   buttons[2].getText().equals(buttons[6].getText())) {
            winner = buttons[2].getText();
            buttons[2].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[6].setForeground(Color.RED);
        }

        if (!winner.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pemenang: " + winner);
            gameOver = true;
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloGridLayout h = new HelloGridLayout();
                h.setVisible(true);
            }
        });
    }
}
