package app.dataHandler;

import app.errors.ErrorSetupEnter;

import java.io.File;
import java.io.IOException;

public class SetupCheck {

    private String username = System.getProperty("user.name");
    private String pathToDirectory = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode";
    private String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\setup.ini";
    private boolean directoryBarcode = new File(pathToDirectory).exists();
    private boolean setupFile = new File(pathToSetupFile).exists();

    public SetupCheck() {

    if (!directoryBarcode) {
        new File(pathToDirectory).mkdir();
    }

    if (!setupFile) {
        File file = new File(pathToSetupFile);
        boolean result;


        try {
            result = file.createNewFile();

            if (result) {
//                System.out.println("file created" + file.getCanonicalPath());
                ErrorSetupEnter errorFrame = new ErrorSetupEnter();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    }


}
