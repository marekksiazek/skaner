package src.main.scan;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import src.main.dataHandler.DataReader;
import src.main.errors.BarcodeShows;
import src.main.errors.RegisterError;
import src.main.request.KeyHack;
import src.main.request.RegisterRequest;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Scan {


    String dataBuffer ="";

    private String messages = "";
    RegisterRequest registerRequest = new RegisterRequest();

    public Scan() throws IOException {
    }

    public void Serial_EventBaseReading(SerialPort activePort) {
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {

                messages += new String(serialPortEvent.getReceivedData());
                while (messages.contains("\n")){
                    String[] message = messages.split("\\n", 2);
                    messages = (message.length > 1) ? message[1] : "";
                    System.out.println("Message: " + message[0]);
                    BarcodeShows barcodeShows = new BarcodeShows(message[0]);
                    message[0] = "";

                }
//                BarcodeShows barcodeShows = new BarcodeShows(message[0]);



//                try {
//                    byte []newData = new byte[0];
//                    for (int i = 0; i < 10; i++){
//                        newData = serialPortEvent.getReceivedData();
//                    }
////                    byte []newData = serialPortEvent.getReceivedData();
//                    dataBuffer = "";
//
//                    for (int i=0; i < newData.length; i++) {
//                        dataBuffer += (char)newData[i];
//                    }
//
//                    System.out.println(dataBuffer);
//                    KeyHack keyHack = new KeyHack();
//                    BarcodeShows barcodeShows = new BarcodeShows(dataBuffer);
//
//                    String code = getBarcode(dataBuffer);
//                    System.out.println(code);
//                    if (registerRequest.getRegisterMSG().equals("err")){
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e.getMessage());
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
            }
        });

    }

    public String getBarcode(String barcode) throws IOException {
        URL url = new URL("https://stand-by.pl/barcode/scan.php?barcode=" + barcode + "&secretkey=" + new DataReader().getSecretKey());
        HttpURLConnection con;


        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = "https://stand-by.pl/barcode/scan.php?barcode=" + barcode + "&secretkey=" + new DataReader().getSecretKey();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);

        InputStream content = con.getInputStream();
        String result = new BufferedReader(new InputStreamReader(content)).lines().parallel().collect(Collectors.joining("\n"));
        String trimResult = result.substring(3);
        System.out.println(result);


        return trimResult;
    }


}
