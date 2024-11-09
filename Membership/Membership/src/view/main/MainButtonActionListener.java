package view.main;

import java.awt.event.*;

public class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    // Konstruktor untuk menerima objek MainFrame
    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Memeriksa tombol mana yang diklik
        if (e.getSource() == mainFrame.getButtonJenisMember()) {
            // Menampilkan frame untuk Jenis Member
            mainFrame.showJenisMemberFrame();
        } else {
            // Menampilkan frame untuk Member
            mainFrame.showMemberFrame();
        }
    }
}
