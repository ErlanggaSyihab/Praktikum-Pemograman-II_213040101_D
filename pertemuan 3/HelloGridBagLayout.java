import java.awt.*;
import javax.swing.*;

public class HelloGridBagLayout extends JFrame {
    // Konstruktor kelas
    public HelloGridBagLayout() {
        // Mengatur agar jendela tertutup saat tombol tutup diklik
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat label judul
        JLabel headerLabel = new JLabel("Layout in action: GridBagLayout", JLabel.CENTER);

        // Membuat panel kontrol dengan layout FlowLayout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Membuat panel utama dengan layout GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300, 300);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Mengatur beberapa properti GridBagConstraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Menambahkan tombol pertama ke panel dengan posisi tertentu
        panel.add(new JButton("Button 1"), gbc);

        // Mengubah posisi untuk tombol kedua
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Button 2"), gbc);

        // Mengatur properti tambahan untuk tombol ketiga dan keempat
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JButton("Button 3"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JButton("Button 4"), gbc);

        // Menambahkan tombol kelima dengan lebar 2 kolom
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(new JButton("Button 5"), gbc);

        // Menambahkan panel utama ke panel kontrol
        controlPanel.add(panel);

        // Mengatur layout frame
        this.setLayout(new GridLayout(2, 1));
        this.add(headerLabel);
        this.add(controlPanel);

        // Mengatur ukuran frame
        this.setSize(400, 400);
    }

    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloGridBagLayout h = new HelloGridBagLayout();
                h.setVisible(true);
            }
        });
    }
}
