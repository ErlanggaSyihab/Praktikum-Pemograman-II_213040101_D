package view;

import dao.BiodataDao;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Biodata;

public class BiodataView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private BiodataDao biodataDao;

    public BiodataView() {
        biodataDao = new BiodataDao();
        
        // Set JFrame properties
        setTitle("Biodata Management");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create DefaultTableModel for JTable
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Alamat", "Email", "Telepon"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add JTable to JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons for operations
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Tambah Biodata");
        JButton deleteButton = new JButton("Hapus Biodata");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add event listeners for buttons
        addButton.addActionListener(e -> addBiodata());
        deleteButton.addActionListener(e -> deleteBiodata());

        // Load data initially
        loadData();
    }

    private void loadData() {
        List<Biodata> biodataList = biodataDao.getAllBiodata();
        for (Biodata biodata : biodataList) {
            tableModel.addRow(new Object[]{biodata.getId(), biodata.getNama(), biodata.getAlamat(), biodata.getEmail(), biodata.getTelepon()});
        }
    }

    private void addBiodata() {
        String nama = JOptionPane.showInputDialog(this, "Nama:");
        String alamat = JOptionPane.showInputDialog(this, "Alamat:");
        String email = JOptionPane.showInputDialog(this, "Email:");
        String telepon = JOptionPane.showInputDialog(this, "Telepon:");

        Biodata biodata = new Biodata(0, nama, alamat, email, telepon);
        if (biodataDao.addBiodata(biodata)) {
            JOptionPane.showMessageDialog(this, "Biodata berhasil ditambahkan!");
            tableModel.addRow(new Object[]{biodata.getId(), biodata.getNama(), biodata.getAlamat(), biodata.getEmail(), biodata.getTelepon()});
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan biodata.");
        }
    }

    private void deleteBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) table.getValueAt(selectedRow, 0);
            if (biodataDao.deleteBiodata(id)) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Biodata berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus biodata.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih biodata yang ingin dihapus.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BiodataView().setVisible(true);
        });
    }
}
