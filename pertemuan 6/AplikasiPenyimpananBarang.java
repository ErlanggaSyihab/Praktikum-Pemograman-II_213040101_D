// Mengimpor pustaka-pustaka yang dibutuhkan untuk antarmuka GUI dan model tabel
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Kelas utama yang mewarisi JFrame, membuat aplikasi inventaris barang dengan GUI
public class AplikasiPenyimpananBarang extends JFrame {
    // Deklarasi model tabel dan tabel
    private final DefaultTableModel model;
    private final JTable table;

    // Konstruktor utama untuk inisialisasi frame utama aplikasi
    public AplikasiPenyimpananBarang() {
        // Mengatur judul, operasi penutupan, dan ukuran frame
        setTitle("Aplikasi Inventaris Penyimpanan Barang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // Membuat menu navigasi
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigasi");
        JMenuItem menuInput = new JMenuItem("Form Input Barang");
        JMenuItem menuView = new JMenuItem("Lihat Data Barang");

        // Menambahkan action listener untuk membuka form input barang saat diklik
        menuInput.addActionListener(e -> openFormInputBarang());
        // Menambahkan action listener untuk membuka data barang saat diklik
        menuView.addActionListener(e -> openDataBarang());

        // Menambahkan item ke menu dan menu ke menu bar
        menu.add(menuInput);
        menu.add(menuView);
        menuBar.add(menu);
        // Menetapkan menu bar pada frame utama
        setJMenuBar(menuBar);

        // Membuat model tabel dengan kolom data barang
        model = new DefaultTableModel(new String[]{
                "Nama Barang", "Deskripsi", "Kategori", "Kondisi", "Lokasi", "Penyimpanan", "Kuantitas", "Tahun Masuk"
        }, 0);
        
        // Membuat tabel berdasarkan model dan menambahkan tabel ke frame utama
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Menampilkan frame utama
        setVisible(true);
    }

    // Metode untuk membuka form input barang
    private void openFormInputBarang() {
        // Membuat frame baru untuk form input barang
        JFrame formFrame = new JFrame("Form Input Barang");
        formFrame.setSize(600, 400);
        formFrame.setLayout(new GridLayout(9, 2));

        // Komponen input untuk nama barang
        formFrame.add(new JLabel("Nama Barang:"));
        JTextField textFieldNama = new JTextField();
        formFrame.add(textFieldNama);

        // Komponen input untuk deskripsi barang
        formFrame.add(new JLabel("Deskripsi:"));
        JTextArea textAreaDeskripsi = new JTextArea();
        formFrame.add(new JScrollPane(textAreaDeskripsi));

        // Komponen input untuk kategori barang
        formFrame.add(new JLabel("Kategori:"));
        JRadioButton radioElektronik = new JRadioButton("Elektronik");
        JRadioButton radioFurniture = new JRadioButton("Furniture");
        
        // Mengelompokkan radio button untuk kategori barang
        ButtonGroup kategoriGroup = new ButtonGroup();
        kategoriGroup.add(radioElektronik);
        kategoriGroup.add(radioFurniture);
        
        // Panel untuk menampilkan pilihan kategori secara horizontal
        JPanel panelRadio = new JPanel();
        panelRadio.add(radioElektronik);
        panelRadio.add(radioFurniture);
        formFrame.add(panelRadio);

        // Komponen input untuk kondisi barang
        formFrame.add(new JLabel("Kondisi:"));
        JCheckBox checkBaru = new JCheckBox("Baru");
        JCheckBox checkBekas = new JCheckBox("Bekas");
        
        // Panel untuk menampilkan pilihan kondisi secara horizontal
        JPanel panelCheckbox = new JPanel();
        panelCheckbox.add(checkBaru);
        panelCheckbox.add(checkBekas);
        formFrame.add(panelCheckbox);

        // Komponen input untuk lokasi barang
        formFrame.add(new JLabel("Lokasi:"));
        JComboBox<String> comboLokasi = new JComboBox<>(new String[]{"Gudang 1", "Gudang 2", "Gudang 3"});
        formFrame.add(comboLokasi);

        // Komponen input untuk penyimpanan barang (Rak)
        formFrame.add(new JLabel("Penyimpanan:"));
        JList<String> listPenyimpanan = new JList<>(new String[]{"Rak 1", "Rak 2", "Rak 3"});
        formFrame.add(new JScrollPane(listPenyimpanan));

        // Komponen input untuk kuantitas barang dengan slider
        formFrame.add(new JLabel("Kuantitas:"));
        JSlider sliderKuantitas = new JSlider(0, 100);
        formFrame.add(sliderKuantitas);

        // Komponen input untuk tahun masuk barang
        formFrame.add(new JLabel("Tahun Masuk:"));
        JSpinner spinnerTahun = new JSpinner(new SpinnerNumberModel(2022, 2000, 2023, 1));
        formFrame.add(spinnerTahun);

        // Tombol submit untuk menambahkan data barang ke tabel
        JButton submitButton = new JButton("Tambah");
        
        formFrame.add(submitButton);

        // Action listener untuk tombol Tambah
        submitButton.addActionListener((ActionEvent e) -> {
            // Mengambil data dari komponen input
            String nama = textFieldNama.getText();
            String deskripsi = textAreaDeskripsi.getText();
            String kategori = radioElektronik.isSelected() ? "Elektronik" : radioFurniture.isSelected() ? "Furniture" : "";
            String kondisi = (checkBaru.isSelected() ? "Baru " : "") + (checkBekas.isSelected() ? "Bekas" : "");
            String lokasi = comboLokasi.getSelectedItem().toString();
            String penyimpanan = listPenyimpanan.getSelectedValue();
            int kuantitas = sliderKuantitas.getValue();
            int tahunMasuk = (int) spinnerTahun.getValue();
            
            // Menambahkan data barang ke model tabel
            model.addRow(new Object[]{nama, deskripsi, kategori, kondisi, lokasi, penyimpanan, kuantitas, tahunMasuk});
            
            // Menutup form input setelah data ditambahkan
            formFrame.dispose();
        });

        // Menampilkan form input
        formFrame.setVisible(true);
    }

    // Metode untuk membuka data barang dalam dialog
    private void openDataBarang() {
        // Menampilkan data barang dengan JOptionPane
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "Data Barang", JOptionPane.INFORMATION_MESSAGE);
    }

    // Metode main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Membuat instance dari kelas aplikasi untuk menampilkan GUI
        new AplikasiPenyimpananBarang();
    }
}
