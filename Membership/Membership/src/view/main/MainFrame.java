package view.main;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import model.*;
import view.jenismember.JenisMemberFrame;
import view.member.MemberFrame;
import dao.*;

public class MainFrame extends JFrame {
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame(JenisMemberDao jenisMemberDao, MemberDao memberDao) {
        // Set ukuran jendela dan pengaturan default
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);

        // Inisialisasi objek DAO
        this.jenisMemberDao = jenisMemberDao;  // Gunakan parameter, tidak perlu new
        this.memberDao = memberDao;            // Gunakan parameter, tidak perlu new

        // Inisialisasi komponen GUI
        this.jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        this.memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        
        this.setLayout(new FlowLayout());
        
        // Membuat dan menambahkan tombol
        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonMember = new JButton("Member");

        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);

        // Menambahkan tombol ke frame
        this.add(buttonJenisMember);
        this.add(buttonMember);
    }

    // Getter untuk tombol
    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    public JButton getButtonMember() {
        return buttonMember;
    }

    // Menampilkan frame JenisMember
    public void showJenisMemberFrame() {
        if (jenisMemberFrame == null) {
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        }
        jenisMemberFrame.setVisible(true);
    }

    // Menampilkan frame Member
    public void showMemberFrame() {
        if (memberFrame == null) {
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        }
        memberFrame.populateComboJenis();
        memberFrame.setVisible(true);
    }
}
