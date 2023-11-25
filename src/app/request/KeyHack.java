package app.request;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeyHack {
    private String baseKey = "4609733220f8de810e1e8866d6d92ec9a073f3a569a517caee405e11e4f72949";
    private String username = System.getProperty("user.name");

    private String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\";
    private boolean setupFile = new File(pathToSetupFile).exists();

    public KeyHack() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = new DateRequest().getResponseMsg();

        File file = new File("C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\" + format + ".txt");
        boolean result;
        if (!file.exists()) {


            result = file.createNewFile();
            PrintWriter out = new PrintWriter("C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\" + format + ".txt");
            int hashedFormat = format.hashCode();
            out.println(hashedFormat+baseKey);
            out.close();
        }else {
            System.out.println("Plik istnieje");
        }


    }
}
