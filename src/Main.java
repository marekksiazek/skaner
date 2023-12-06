import app.Tray;
import app.config.SetupFrame;
import app.errors.*;
import app.scan.Scan;
import app.dataHandler.CheckRegisterKey;
import app.dataHandler.DataReader;
import app.dataHandler.SetupCheck;
import app.request.DateRequest;
import app.request.RequestURL;
import com.fazecast.jSerialComm.*;

import java.io.File;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {

        boolean run = false;
        boolean startScan = false;
        SerialPort serialPort1 = null;
        String username = System.getProperty("user.name");
        String pathToSetupFile = "C:\\Users\\" + username + "\\AppData\\Local\\Barcode\\setup.ini";
        boolean fileExist = new File(pathToSetupFile).exists();


        SetupCheck setupCheck = new SetupCheck();
        Tray tray = new Tray();

        SerialPort[] serialPort = SerialPort.getCommPorts();

        if (!fileExist){
            CheckRegisterKey checkRegisterKey = new CheckRegisterKey();
            String serialDisk = checkRegisterKey.getTrimSS();
        }



        DataReader dataReader = new DataReader();
        String appKey = dataReader.getAppKay();
        String secretKey = dataReader.getSecretKey();
        String URLBarcode = dataReader.getURLBarcode();
        String comName = dataReader.getCom();

        if (fileExist) {
            System.out.println("File Exist!");
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
                System.out.println("File Exist else");
                RequestURL request = new RequestURL();
                run = true;
                startScan = true;

                Scan scan = new Scan();
                DateRequest dateRequest = new DateRequest();



                while (run){
                    System.out.println("Inside while");
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
                    System.out.println("Inside while with scan");
                    scan.Serial_EventBaseReading(serialPort1);
                }
        }


    }

//        Scan scan = new Scan();
//        DateRequest dateRequest = new DateRequest();
//
//
//
//        while (run){
//            if (serialPort.length == 0) {
//                ErrorBarcodeConnect frame = new ErrorBarcodeConnect();
//            } else {
//                serialPort1 = SerialPort.getCommPort(DataReader.getComPort());
//                serialPort1.setBaudRate(9600);
//                serialPort1.setNumDataBits(8);
//                serialPort1.setNumStopBits(1);
//                serialPort1.setParity(SerialPort.NO_PARITY);
//                serialPort1.openPort();
//                startScan = true;
//             }
//        }
//
//        while (startScan){
//            scan.Serial_EventBaseReading(serialPort1);
//        }


    }
}