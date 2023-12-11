package src.main.dataHandler;


import src.main.errors.ErrorSetupEnter;
import src.main.request.RegisterRequest;

import java.io.File;
import java.io.IOException;

public class SetupCheck {

    private String baseKey = "4609733220f8de810e1e8866d6d92ec9a073f3a569a517caee405e11e4f72949";
    private String username = System.getProperty("user.name");
    private String pathToDirectory = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode";
    private String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\setup.ini";
    private boolean directoryBarcode = new File(pathToDirectory).exists();
    private boolean setupFile = new File(pathToSetupFile).exists();

    public SetupCheck() throws IOException {

            if (!directoryBarcode) {
                new File(pathToDirectory).mkdir();
            }

            if (!setupFile) {
                File file = new File(pathToSetupFile);
                boolean result;

                try {
                    result = file.createNewFile();

                    if (result) {
                        ErrorSetupEnter errorFrame = new ErrorSetupEnter();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    }
}
