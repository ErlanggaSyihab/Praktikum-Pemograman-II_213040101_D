package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserView extends JFrame {
    // Deklarasi komponen GUI
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnAdd = new JButton("Add User");
    private JButton btnRefresh = new JButton("Refresh");
    private JList<String> userList = new JList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    // Konstruktor untuk inisialisasi tampilan
    public UserView() {
        setTitle("User Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel untuk nama dan email
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        // Membuat panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);

        // Menambahkan panel ke frame utama
        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Inisialisasi JList untuk menampilkan daftar pengguna
        userList.setModel(listModel);
        add(new JScrollPane(userList), BorderLayout.CENTER);
    }

    // Metode untuk mendapatkan nilai dari text field nama
    public String getNameInput() {
        return txtName.getText();
    }

    // Metode untuk mendapatkan nilai dari text field email
    public String getEmailInput() {
        return txtEmail.getText();
    }

    public void setUserList(String[] users) {
        listModel.clear();
        for (String user : users) {

        }
    }

    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

}