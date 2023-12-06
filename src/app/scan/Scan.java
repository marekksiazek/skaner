package app.scan;

import app.dataHandler.DataReader;
import app.request.KeyHack;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Scan {


    String dataBuffer ="";
    public void Serial_EventBaseReading(SerialPort activePort) {
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                byte []newData = serialPortEvent.getReceivedData();
                for (int i=0; i <newData.length; i++) {
                    dataBuffer += (char)newData[i];

                }

                try {
                    KeyHack keyHack = new KeyHack();
                    getResponseMSGBarcode(dataBuffer);
                    getStatusCodeBarcode(dataBuffer);
                    dataBuffer = "";
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        });

    }

    public int getStatusCodeBarcode(String barcode) throws IOException {
        URL url = new URL("https://stand-by.pl/barcode/scan.php?barcode" + barcode + "&secretkey=" + new DataReader().getSecretKey());
        HttpURLConnection con;


        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stand-by.pl/barcode/scan.php?barcode" + barcode + "&secretkey=" + new DataReader().getSecretKey();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);


        int status = con.getResponseCode();
        return status;
    }

    public String getResponseMSGBarcode(String barcode) throws IOException {
        URL url = new URL("https://stand-by.pl/barcode/scan.php?barcode" + barcode + "&secretkey=" + new DataReader().getSecretKey());
        HttpURLConnection con;

        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stand-by.pl/barcode/scan.php?barcode" + barcode + "&secretkey=" + new DataReader().getSecretKey();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);


        String responseMSG = con.getResponseMessage();
        return responseMSG;
    }

}
