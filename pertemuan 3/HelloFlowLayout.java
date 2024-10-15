import java.awt.*;
import javax.swing.*;


public class HelloFlowLayout extends JFrame {
    public HelloFlowLayout() {
        // Mengatur agar jendela ditutup saat tombol tutup diklik
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat 10 tombol dengan label angka 1 sampai 10
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        // ... (dan seterusnya sampai button10)

        // Mengatur layout jendela menjadi FlowLayout
        this.setLayout(new FlowLayout());

        // Menambahkan tombol-tombol ke dalam jendela
        this.add(button1);
        this.add(button2);
        // ... (dan seterusnya sampai button10)

        // Mengatur ukuran jendela
        this.setSize(400, 400);
    }

    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            HelloFlowLayout h = new HelloFlowLayout();
            h.setVisible(true);
        }
    });
}
}