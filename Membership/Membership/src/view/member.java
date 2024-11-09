package view.member;

import javax.swing.table.*;
import java.util.List;
import model.Member;

class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengambil nilai sel untuk setiap baris dan kolom
    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama(); // Mengambil nama member
            case 1:
                return rowItem.getJenisMember().getNama(); // Mengambil jenis member
            default:
                return null;
        }
    }

    // Menentukan apakah sel dapat diedit (diatur false agar tidak dapat diedit)
    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Tidak memungkinkan edit pada tabel
    }

    // Menambahkan data member ke dalam tabel
    public void add(Member value) {
        data.add(value); // Menambah data member baru
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Memperbarui tampilan tabel
    }

    // Menghapus data member dari tabel (opsional untuk masa depan)
    public void remove(int row) {
        data.remove(row); // Menghapus data member pada indeks yang diberikan
        fireTableRowsDeleted(row, row); // Memperbarui tampilan tabel setelah penghapusan
    }

    // Mengupdate seluruh data tabel
    public void setData(List<Member> newData) {
        this.data = newData;
        fireTableDataChanged(); // Memperbarui tampilan tabel dengan data baru
    }
}
