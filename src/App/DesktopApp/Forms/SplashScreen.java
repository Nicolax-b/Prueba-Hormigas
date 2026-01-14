package App.DesktopApp.Forms;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Infrastructure.AppConfig;

public class SplashScreen {
    private static JFrame       frmSplash   = new JFrame();
    public SplashScreen() {
        initComponents();
    }

    private void initComponents() {
        JProgressBar prbLoaging= new JProgressBar(0, 100);
        ImageIcon    icoImagen = new ImageIcon(AppConfig.URL_SPLASH);
        JLabel       lblSplash = new JLabel(icoImagen);

        prbLoaging.setStringPainted(true);
        frmSplash.setUndecorated(true);
        frmSplash.getContentPane().add(lblSplash, BorderLayout.CENTER);
        frmSplash.add(prbLoaging, BorderLayout.SOUTH);
        frmSplash.setSize(icoImagen.getIconWidth(), icoImagen.getIconHeight());
        frmSplash.setLocationRelativeTo(null); // Centrar en la pantalla
        frmSplash.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt(); 
                break;
            }
            prbLoaging.setValue(i);
        }
        frmSplash.setVisible(false);
        frmSplash.dispose();
    }

}
