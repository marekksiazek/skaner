package app;

import app.config.SetupFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Tray {

    public Tray(){



        SystemTray systemTray = SystemTray.getSystemTray();

        PopupMenu popMenu = new PopupMenu();
        MenuItem show = new MenuItem("Ustawienia");

        URL url = System.class.getResource("/images/barcode.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
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
