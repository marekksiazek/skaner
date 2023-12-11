package src;


import com.fazecast.jSerialComm.*;
import src.main.Tray;
import src.main.dataHandler.CheckRegisterKey;
import src.main.dataHandler.DataReader;
import src.main.dataHandler.SetupCheck;
import src.main.errors.*;
import src.main.request.DateRequest;
import src.main.request.KeyHack;
import src.main.request.RegisterRequest;
import src.main.request.RequestURL;
import src.main.scan.Scan;

import java.io.File;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {

        boolean run = false;
        boolean startScan = false;
        SerialPort serialPort1 = null;
        String baseKey = "4609733220f8de810e1e8866d6d92ec9a073f3a569a517caee405e11e4f72949";

        SetupCheck setupCheck = new SetupCheck();
        Tray tray = new Tray();
        DataReader dataReader = new DataReader();
        KeyHack keyHack = new KeyHack();

        String registerRequest = new RegisterRequest().getRegisterMSG();
        if (registerRequest.equals("err")){
            RegisterError registerFrame = new RegisterError();
        }
        SerialPort[] serialPort = SerialPort.getCommPorts();

        if (registerRequest.equals(baseKey)){

            String username = System.getProperty("user.name");
            String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\setup.ini";
            boolean fileExist = new File(pathToSetupFile).exists();

            if (!fileExist){
                CheckRegisterKey checkRegisterKey = new CheckRegisterKey();
                String serialDisk = checkRegisterKey.getTrimSS();
            }


            String appKey = dataReader.getAppKay();
            String secretKey = dataReader.getSecretKey();
            String URLBarcode = dataReader.getURLBarcode();
            String comName = dataReader.getCom();

            if (fileExist) {
                if (appKey == null) {
                    AppKeyError error = new AppKeyError();
                } else if (secretKey == null) {
                    SecretKeyError error = new SecretKeyError();
                } else if (URLBarcode == null) {
                    UrlError error = new UrlError();
                } else if (comName == null){
                    ErrorBarcodeConnect error = new ErrorBarcodeConnect();
                }
                else {
                    RequestURL request = new RequestURL();
                    run = true;
                    startScan = true;

                    Scan scan = new Scan();
                    DateRequest dateRequest = new DateRequest();



                    while (run){
                        if (serialPort.length == 0) {
                            ErrorBarcodeConnect frame = new ErrorBarcodeConnect();
                        } else {
                            serialPort1 = SerialPort.getCommPort(DataReader.getComPort());
                            serialPort1.setBaudRate(9600);
                            serialPort1.setNumDataBits(8);
                            serialPort1.setNumStopBits(1);
                            serialPort1.setParity(SerialPort.NO_PARITY);
                            serialPort1.openPort();
                            startScan = true;
                            run = false;
                        }
                    }

                    while (startScan){
                        scan.Serial_EventBaseReading(serialPort1);
                    }
                }
            }







        }
    }
}