package src.main;

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

//        URL url = System.class.getResource("src/barcode.png");
        Image img =  Toolkit.getDefaultToolkit().getImage("src/barcode.png");
        TrayIcon trayIcon = new TrayIcon(img, "Scanner", popMenu);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SetupFrame setupFrame = new SetupFrame();
            }
        });

        MenuItem exit = new MenuItem("Zamknij");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popMenu.add(show);
        popMenu.add(exit);

        trayIcon.setPopupMenu(popMenu);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


    }
}
