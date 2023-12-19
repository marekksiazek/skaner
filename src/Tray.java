package src;

import src.main.config.SetupFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Tray {

    public Tray() throws IOException {



        SystemTray systemTray = SystemTray.getSystemTray();

        PopupMenu popMenu = new PopupMenu();
        MenuItem show = new MenuItem("Ustawienia");
        MenuItem exit = new MenuItem("Zamknij");

        Image img =  Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("src/barcode.png"));
        TrayIcon trayIcon = new TrayIcon(img, "Scanner", popMenu);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SetupFrame setupFrame = new SetupFrame();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popMenu.add(show);
        popMenu.add(exit);

        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popMenu);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


    }
}
