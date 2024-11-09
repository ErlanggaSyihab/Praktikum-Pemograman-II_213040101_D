package view.member;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JTable table;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(null);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 350, 30);
        
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 70, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 110, 106, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 160, 350, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        JenisMemberButtonSimpanActionListener actionListener = 
            new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);

        // Menambahkan komponen ke JFrame
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(button);
        this.add(scrollableTable);

        this.setVisible(true); // Menampilkan JFrame setelah semua komponen ditambahkan
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText(""); // Mengosongkan text field setelah menambah jenis member
    }
}
