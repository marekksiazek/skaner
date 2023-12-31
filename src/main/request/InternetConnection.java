package src.main.request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class InternetConnection {

    public InternetConnection() {
    }

    public boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.pl");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        }catch (MalformedURLException e){
            throw new RuntimeException(e);
        }catch (IOException e) {
            return false;
        }
    }
}
