package PraktikumPemograman2;

import java.awt.event.*;
import javax.swing.*;

public class HelloRadioButton extends JFrame {
    private boolean CheckBoxSelected;

    public HelloRadioButton() {
        this.CheckBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 350, 10);

        JTextField textField = new JTextField();
        textField.setBounds(15, 40, 350, 30);

        JLabel labelNoHP = new JLabel("Nomor Hp:");
        labelNoHP.setBounds(15, 80, 350, 10);

        JTextField textNoHP = new JTextField();
        textNoHP.setBounds(15, 110, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 150, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        radioButton1.setBounds(15, 185, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 220, 350, 30);

        JCheckBox checkBox = new JCheckBox("Saya adalah Warga Negara Asing");
        checkBox.setBounds(15, 255, 350, 30);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	CheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 300, 100, 40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 350, 350, 100);
        txtOutput.setEditable(false); // Agar textarea tidak bisa diedit

        // Bagian ini menambahkan listener ke tombol (button)
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Inisialisasi variabel jenisKelamin untuk menyimpan jenis kelamin
                String jenisKelamin = "";

                // Cek radio button mana yang dipilih dan simpan teksnya ke jenisKelamin
                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                } else if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }

                String nama = textField.getText();
                String NoHP = textNoHP.getText();

                // Tampilkan pesan selamat datang dan informasi di txtOutput
                txtOutput.append("Hello " + nama + "\n");
                txtOutput.append("No Hp: " + NoHP + "\n");
                txtOutput.append("Jenis Kelamin Anda Adalah " + jenisKelamin + "\n");

                // Tampilkan informasi warga negara asing jika checkbox dicentang
                if (CheckBoxSelected) {
                    txtOutput.append("Warga Negara Asing\n");
                }

                // Kosongkan textField setelah data diambil
                textField.setText("");
                textNoHP.setText("");
            }
        });

        // Bagian ini menambahkan komponen-komponen ke dalam frame (jendela aplikasi)
        this.add(button);
        this.add(textField);
        this.add(textNoHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(checkBox);
        this.add(labelNama);
        this.add(labelNoHP);
        this.add(txtOutput);

        // Mengatur ukuran dan layout frame
        this.setSize(400, 500);
        this.setLayout(null);
        this.setVisible(true); // Tambahkan ini untuk menampilkan frame
    }

    // Bagian main untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HelloRadioButton(); // Memanggil konstruktor
            }
        });
    }
}
