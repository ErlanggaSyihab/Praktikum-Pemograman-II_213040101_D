package view.member;

import java.awt.event.*;
import java.util.UUID;
import model.Member;
import model.JenisMember;
import dao.MemberDao;

public class MemberButtonSimpanActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.memberFrame.getNama();
        
        // Validasi nama tidak boleh kosong
        if (nama.isEmpty()) {
            this.memberFrame.showAlert("Nama tidak boleh kosong");
            return; // Kembali jika nama kosong
        }

        JenisMember jenisMember = this.memberFrame.getJenisMember();
        
        // Validasi jenis member tidak boleh null
        if (jenisMember == null) {
            this.memberFrame.showAlert("Jenis member tidak boleh kosong");
            return; // Kembali jika jenis member tidak ada
        }

        Member member = new Member();
        member.setId(UUID.randomUUID().toString());
        member.setNama(nama);
        member.setJenisMember(jenisMember);

        // Menyimpan member dan memberikan umpan balik
        this.memberFrame.addMember(member);
        if (this.memberDao.insert(member) > 0) {
            this.memberFrame.showSuccess("Member berhasil disimpan.");
        } else {
            this.memberFrame.showAlert("Gagal menyimpan member.");
        }
    }
}