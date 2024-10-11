import javax.swing.*;

public class HelloWordSwing {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("HelloWordSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello Adan Ahmad Erlangga Selamat Datang dan Selamat Beraktifitas");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
