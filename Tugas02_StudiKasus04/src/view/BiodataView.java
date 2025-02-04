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
    private JProgressBar progressBar;
    private JLabel statusLabel;

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

        // Create progress bar and status label
        JPanel statusPanel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        statusLabel = new JLabel("Ready", JLabel.CENTER);
        statusPanel.add(progressBar, BorderLayout.NORTH);
        statusPanel.add(statusLabel, BorderLayout.SOUTH);
        add(statusPanel, BorderLayout.NORTH);

        // Add event listeners for buttons
        addButton.addActionListener(e -> addBiodata());
        deleteButton.addActionListener(e -> deleteBiodata());

        // Load data initially using SwingWorker
        loadData();
    }

    private void loadData() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                List<Biodata> biodataList = biodataDao.getAllBiodata();
                int progress = 0;
                int size = biodataList.size();
                for (int i = 0; i < size; i++) {
                    Biodata biodata = biodataList.get(i);
                    tableModel.addRow(new Object[]{biodata.getId(), biodata.getNama(), biodata.getAlamat(), biodata.getEmail(), biodata.getTelepon()});
                    progress = (int) ((i + 1) * 100.0 / size);
                    publish(progress); // Update progress
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latestProgress = chunks.get(chunks.size() - 1);
                progressBar.setValue(latestProgress);
                statusLabel.setText("Loading... " + latestProgress + "%");
            }

            @Override
            protected void done() {
                progressBar.setValue(100);
                statusLabel.setText("Load data selesai!");
            }
        };

        worker.execute();
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
