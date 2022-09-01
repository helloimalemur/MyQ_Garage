import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class CheckHost {
    static boolean result;

    CheckHost() {
    }

    public static boolean check(String host) {
        try {
            InetAddress address = InetAddress.getByName(host);
            result = address.isReachable(5000);
        } catch(IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
    }
        return result;
    }
}
