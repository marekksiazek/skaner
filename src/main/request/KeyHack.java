package src.main.request;


import src.main.errors.AppKeyError;

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeyHack {

    private String firstLine;
    private String baseKey = "4609733220f8de810e1e8866d6d92ec9a073f3a569a517caee405e11e4f72949";
    private String username = System.getProperty("user.name");

    private String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\";
    private boolean setupFile = new File(pathToSetupFile).exists();

    public KeyHack() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = new DateRequest().getResponseMsg();
        RegisterRequest registerRequest = new RegisterRequest();

        File file = new File("C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\" + format + ".txt");
        boolean result;

        if(registerRequest.getRegisterMSG().equals(baseKey)){
            if (!file.exists()) {
                result = file.createNewFile();
                PrintWriter out = new PrintWriter("C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\" + format + ".txt");

                out.println(sha256(format)+baseKey);
                out.close();
            }
        }
    }

    public  String sha256(String format) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(format.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
