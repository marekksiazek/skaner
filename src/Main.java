import app.config.Scan;
import app.dataHandler.CheckRegisterKey;
import app.dataHandler.DataReader;
import app.dataHandler.SetupCheck;
import app.errors.AppKeyError;
import app.errors.UrlError;
import app.errors.ErrorBarcodeConnect;
import app.errors.SecretKeyError;
import app.request.DateRequest;
import app.request.KeyHack;
import app.request.RequestURL;
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;


public class Main {



    public static void main(String[] args) throws IOException {

        SetupCheck setupCheck = new SetupCheck();

        CheckRegisterKey checkRegisterKey = new CheckRegisterKey();
        String serialDisk = checkRegisterKey.getTrimSS();

        DataReader dataReader = new DataReader();
        String appKey = dataReader.getAppKay();
        String secretKey = dataReader.getSecretKey();
        String URLBarcode = dataReader.getURLBarcode();

        if (appKey.isEmpty()){
            AppKeyError appKeyError = new AppKeyError();
        } else if (secretKey.isEmpty()) {
            SecretKeyError secretKeyError = new SecretKeyError();
        } else if (URLBarcode.isEmpty()) {
            UrlError urlError = new UrlError();
        }else {
            RequestURL request = new RequestURL();
        }
//
//        System.out.println(appKey);
//        System.out.println(secretKey);
//        System.out.println(URLBarcode);

        Scan scan = new Scan();
        DateRequest dateRequest = new DateRequest();


        boolean run = true;

        SerialPort []portLists = SerialPort.getCommPorts();

        if (portLists.length == 0) {
            ErrorBarcodeConnect frame = new ErrorBarcodeConnect();
        } else {
            SerialPort serialPort1 = portLists[0];
            serialPort1.setBaudRate(9600);
            serialPort1.setNumDataBits(8);
            serialPort1.setNumStopBits(1);
            serialPort1.setParity(SerialPort.NO_PARITY);
            serialPort1.openPort();
            while(run) {
                scan.Serial_EventBaseReading(serialPort1);
            }
        }

    }
}