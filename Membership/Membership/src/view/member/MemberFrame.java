package view.member;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.*;
import dao.MemberDao;
import dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    // Deklarasi variabel anggota
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    // Konstruktor
    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        // Inisialisasi komponen dan data
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        // Inisialisasi komponen GUI dan atur tata letak
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 30);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 70, 350, 30);
        
        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 110, 350, 30);
        
        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 140, 150, 30);
        
        JButton button = new JButton("Simpan");
        button.setBounds(15, 180, 100, 40);
        
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 230, 350, 200);
        
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);
        
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        // Tambahkan komponen ke form
        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        // Atur ukuran dan layout form
        this.setSize(400, 500);
        this.setLayout(null);

        // Isi combo box dengan jenis member
        populateComboJenis();
    }

    // Metode untuk mengisi combo box dengan jenis member
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    // Metode untuk mendapatkan nama dari text field
    public String getNama() {
        return textFieldNama.getText();
    }

    // Metode untuk mendapatkan jenis member yang dipilih
    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    // Metode untuk menambahkan member ke tabel
    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    // Metode untuk menampilkan pesan dialog
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}