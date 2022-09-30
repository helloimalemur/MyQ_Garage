import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CloseControl extends Thread implements Runnable {
    String address;
    DiscordNotif discordNotif;
    CloseControl(String address) {
        this.address = address;
    }

    public void pythonClose() throws Exception {
        Thread.sleep(15000);
        if (CheckHost.check(address)) {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "/usr/share/myq/closeGarage.py");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println(in.readLine());
            String message = "Closing garage ..";
            System.out.println(message);
            discordNotif.sendNotif(message);
            System.out.println("Closing...");
        } else {System.out.println("..not closing..");}
    }

    @Override
    public void run() {

        try {
            pythonClose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(String address) {
        CloseControl closer = new CloseControl(address);
        closer.start();
    }

}
