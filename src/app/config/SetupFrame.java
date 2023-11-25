package app.config;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SetupFrame extends JFrame {

    public String username = System.getProperty("user.name");
    public SetupFrame() {
        Border borderInput = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        JLabel appKey = new JLabel("Klucz aplikacji: ");
        JLabel secretKey = new JLabel("Secretkey:  ");
        JLabel urlBarcode = new JLabel("URL Barcode:  ");
        JLabel autostart = new JLabel("Autostart:  ");

        JTextField appKeyInput = new JTextField(150);
        JTextField secretKeyInput = new JTextField(150);
        JTextField urlBarcodeInput = new JTextField(150);

        JCheckBox autostartCheck = new JCheckBox();

        JButton buttonSave = new JButton("Zapisz");

        // LABELS
        appKey.setFont(new Font("Arial", Font.PLAIN, 14));
        appKey.setForeground(Color.WHITE);
        appKey.setBounds(50, 50, 110, 26);

        secretKey.setFont(new Font("Arial", Font.PLAIN, 14));
        secretKey.setForeground(Color.WHITE);
        secretKey.setBounds(50, 100, 110, 26);

        urlBarcode.setFont(new Font("Arial", Font.PLAIN, 14));
        urlBarcode.setForeground(Color.WHITE);
        urlBarcode.setBounds(50, 150, 110, 26);

        autostart.setFont(new Font("Arial", Font.PLAIN, 14));
        autostart.setForeground(Color.WHITE);
        autostart.setBounds(50, 200, 110, 26);

        // INPUTS
        appKeyInput.setBounds(200, 50, 300, 26);
        appKeyInput.setBorder(borderInput);
        appKeyInput.setFont(new Font("Arial", Font.PLAIN, 14));




        secretKeyInput.setBounds(200, 100, 300, 26);
        secretKeyInput.setBorder(borderInput);
        secretKeyInput.setFont(new Font("Arial", Font.PLAIN, 14));



        urlBarcodeInput.setBounds(200, 150, 300, 26);
        urlBarcodeInput.setBorder(borderInput);
        urlBarcodeInput.setFont(new Font("Arial", Font.PLAIN, 14));


        // CHECKBOX

        autostartCheck.setLocation(200, 200);
        autostartCheck.setSize(36, 36);
        autostartCheck.setBackground(Color.DARK_GRAY);
        autostartCheck.setBorder(borderInput);

        // BUTTON
        buttonSave.setBounds(450, 300, 70, 26);
        buttonSave.setBorder(borderInput);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appKeyInputValue = appKeyInput.getText();
                String secretKeyInputValue = secretKeyInput.getText();
                String urlBarcodeInputValue = urlBarcodeInput.getText();

                try {
                    PrintWriter out = new PrintWriter("C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\setup.ini");
                    out.println(appKeyInputValue);
                    out.println(secretKeyInputValue);
                    out.println(urlBarcodeInputValue);

                    out.close();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0);
            }
        });



        this.setTitle("Ustawienia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 400);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        // LABELS
        this.add(appKey);
        this.add(secretKey);
        this.add(urlBarcode);
        this.add(autostart);

        // INPUTS FIELDS
        this.add(appKeyInput);
        this.add(secretKeyInput);
        this.add(urlBarcodeInput);
        this.add(autostartCheck);

        //BUTTON
        this.add(buttonSave);

        }

}


