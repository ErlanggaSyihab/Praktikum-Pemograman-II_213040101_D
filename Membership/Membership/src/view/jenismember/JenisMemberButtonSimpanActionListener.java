package view.jenismember;

import java.awt.event.*;
import java.util.UUID;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();

        // Validasi nama untuk memastikan tidak kosong
        if (nama == null || nama.trim().isEmpty()) {
            this.jenisMemberFrame.showError("Nama tidak boleh kosong.");
            return;
        }

        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        // Menyimpan jenisMember ke dalam frame dan database
        this.jenisMemberFrame.addJenisMember(jenisMember);
        if (this.jenisMemberDao.insert(jenisMember) > 0) {
            this.jenisMemberFrame.showSuccess("Jenis member berhasil disimpan.");
        } else {
            this.jenisMemberFrame.showError("Gagal menyimpan jenis member.");
        }
    }
}
