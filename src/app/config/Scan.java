package app.config;

import app.request.KeyHack;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;

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
                System.out.println(dataBuffer);
                dataBuffer = "";
                try {
                    KeyHack keyHack = new KeyHack();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
