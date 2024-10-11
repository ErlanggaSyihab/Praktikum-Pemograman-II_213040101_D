import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Latihan3 extends JFrame {
    private boolean checkBoxSelected;

    public Latihan3() {
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menggunakan GridBagLayout untuk tata letak yang fleksibel
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(layout);

        // Membuat label dan textfield untuk input nama
        JLabel labelNama = new JLabel("Nama: ");
        JTextField textFieldNama = new JTextField(25); // Untuk Mengatur Ukuran 

        // Membuat label dan textfield untuk input telepon
        JLabel labelTelepon = new JLabel("Nomor HP: ");
        JTextField textFieldTelepon = new JTextField(25); // Untuk Mengatur Ukuran 

        // Membuat label dan radio button untuk jenis kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Membuat checkbox untuk verifikasi WNA
        JCheckBox checkBox = new JCheckBox("Warga Asing");

        // Membuat button untuk simpan data
        JButton button = new JButton("Simpan");

        // Membuat textarea untuk output data
        JTextArea textArea = new JTextArea(10, 30); // Untuk Mengatur Ukuran 
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Mengatur penempatan komponen
        gbc.insets = new Insets(10, 10, 10, 10);

        // Komponen bagian kiri
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(labelNama, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(textFieldNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelTelepon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(textFieldTelepon, gbc);

        // Komponen bagian kanan
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(labelRadio, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(radioButton1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(radioButton2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(checkBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        this.add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3; // Membuat textarea memakan 3 kolom
        gbc.fill = GridBagConstraints.BOTH; // Membuat textarea mengisi ruang
        this.add(scrollPane, gbc);

        // Action listeners untuk checkbox dan button
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == 1;
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = radioButton1.isSelected() ? "Laki-Laki" : "Perempuan";

                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();

                textArea.append("Nama\t: " + nama + "\n");
                textArea.append("Nomor HP\t: " + telepon + "\n");
                textArea.append("Jenis Kelamin\t: " + jenisKelamin + "\n");
                textArea.append("WNA\t: " + (checkBoxSelected ? "Ya" : "Bukan") + "\n");
                textArea.append("==================================\n");

                textFieldNama.setText("");
                textFieldTelepon.setText("");
                checkBox.setSelected(false);
            }
        });

        // Membuat frame dengan ukuran yang sesuai
        this.setSize(600, 600);
        this.setLocationRelativeTo(null); // Posisikan di tengah layar
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Latihan3 latihan = new Latihan3();
                latihan.setVisible(true);
            }
        });
    }
}


//Create By : Adan Ahmad Erlangga