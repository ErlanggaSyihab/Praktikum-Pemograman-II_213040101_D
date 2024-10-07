import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class Tugas02 extends JFrame {
	private boolean CheckBoxSelected;

    public Tugas02() {
        this.CheckBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelNama = new JLabel("Masukan Username Anda!:");
        labelNama.setBounds(15, 20, 350, 10);

        JTextField textField = new JTextField();
        textField.setBounds(15, 40, 350, 30);

        JLabel labelPassword = new JLabel("Masukan Password Anda!:");
        labelPassword.setBounds(15, 80, 350, 15);

        JPasswordField textPassword = new JPasswordField();
        textPassword.setBounds(15, 100, 310, 30);
        
        
        JButton showPasswordButton = new JButton("👁");
        showPasswordButton.setBounds(325, 100, 40, 30);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textPassword.getEchoChar() != '\u0000') {
                    textPassword.setEchoChar('\u0000');
                } else {
                    textPassword.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        JLabel labelKonfirmasi = new JLabel("Masukan kembali Password Anda!:");
        labelKonfirmasi.setBounds(15, 140, 350, 15);

        JPasswordField textKonfirmasi = new JPasswordField();
        textKonfirmasi.setBounds(15, 160, 310, 30);

        
        JButton showConfirmPasswordButton = new JButton("👁");
        showConfirmPasswordButton.setBounds(325, 160, 40, 30);
        showConfirmPasswordButton.setFocusPainted(false);
        showConfirmPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textKonfirmasi.getEchoChar() != '\u0000') {
                    textKonfirmasi.setEchoChar('\u0000');
                } else {
                    textKonfirmasi.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        JLabel labelRadio = new JLabel("Pilih Jenis Tabungan:");
        labelRadio.setBounds(15, 200, 350, 15);

        JRadioButton radioButton1 = new JRadioButton("Tabungan Pendidikan", true);
        radioButton1.setBounds(15, 235, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Tabungan Haji");
        radioButton2.setBounds(15, 270, 350, 30);
        
        JRadioButton radioButton3 = new JRadioButton("Tabungan Masa Depan");
        radioButton3.setBounds(15, 305, 350, 30);
        
        JRadioButton radioButton4 = new JRadioButton("Tabungan Investasi");
        radioButton4.setBounds(15, 340, 350, 30);

        JCheckBox checkBox = new JCheckBox("Saya adalah Warga Negara Asing");
        checkBox.setBounds(15, 430, 350, 30);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                CheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);
        bg.add(radioButton4);

        
        JLabel labelSaldo = new JLabel("Masukkan Jumlah Saldo:");
        labelSaldo.setBounds(15, 375, 350, 15);

        SpinnerModel saldoModel = new SpinnerNumberModel(100000, 100000, 1000000, 50000); 
        JSpinner spinnerSaldo = new JSpinner(saldoModel);
        spinnerSaldo.setBounds(15, 395, 350, 30);

       
        JLabel labelTanggalLahir = new JLabel("Masukkan Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 475, 350, 15);

       
        SpinnerModel dateModel = new SpinnerDateModel();
        JSpinner spinnerTanggalLahir = new JSpinner(dateModel);
        spinnerTanggalLahir.setBounds(15, 495, 350, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd-MM-yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Keluar");
              
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(""); 
                textPassword.setText("");
                textKonfirmasi.setText("");
               
            }
        });
        
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        
        this.setJMenuBar(menuBar);
        
        this.setSize(400,500);
        this.setLayout(null);
        
        JButton button = new JButton("Simpan");
        button.setBounds(15, 535, 100, 40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 585, 350, 200);
        txtOutput.setEditable(false); 

        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisTabungan = "";

                
                if (radioButton1.isSelected()) {
                    jenisTabungan = radioButton1.getText();
                } else if (radioButton2.isSelected()) {
                    jenisTabungan = radioButton2.getText();
                } else if (radioButton3.isSelected()) {
                    jenisTabungan = radioButton3.getText();
                } else if (radioButton4.isSelected()) {
                    jenisTabungan = radioButton4.getText();
                }

                
                int saldo = (Integer) spinnerSaldo.getValue();

                
                Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();

                String nama = textField.getText();
                String Password = new String(textPassword.getPassword());
                String Konfirmasi = new String(textKonfirmasi.getPassword());

                if (!Password.equals(Konfirmasi)) {
                    JOptionPane.showMessageDialog(null, "Password dan Konfirmasi Password tidak sama!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                txtOutput.append("Hello " + nama + "\n");
                txtOutput.append("Password Anda Adalah: " + Password + "\n");
                txtOutput.append("Konfirmasi Password Anda Adalah: " + Konfirmasi + "\n");
                txtOutput.append("Jenis Tabungan yang Anda pilih Adalah: " + jenisTabungan + "\n");
                txtOutput.append("Jumlah Saldo Anda Adalah: " + saldo + "\n");
                txtOutput.append("Tanggal Lahir Anda Adalah: " + dateEditor.getFormat().format(tanggalLahir) + "\n");

               
                if (CheckBoxSelected) {
                    txtOutput.append("Warga Negara Asing\n");
                }

                
                textField.setText("");
                textPassword.setText("");
                textKonfirmasi.setText("");
            }
        });

        
        this.add(button);
        this.add(textField);
        this.add(labelPassword);
        this.add(textPassword);
        this.add(showPasswordButton);
        this.add(labelKonfirmasi);
        this.add(textKonfirmasi);
        this.add(showConfirmPasswordButton);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(radioButton4);
        this.add(checkBox);
        this.add(labelNama);
        this.add(labelSaldo);
        this.add(spinnerSaldo);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(txtOutput);

        
        this.setSize(400, 1000); 
        this.setLayout(null);
        this.setVisible(true); 
    }

   
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tugas02(); 
            }
        });
    }
}
