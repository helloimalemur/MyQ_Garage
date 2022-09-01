import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OpenControl extends Thread implements Runnable {

    OpenControl() {
    }

    public void pythonClose() throws Exception {
        System.out.println("building process..");
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "/usr/share/myq/openGarage.py");
        processBuilder.redirectErrorStream(true);
        System.out.println("starting..");
        Process process = processBuilder.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        System.out.println(in.readLine());
    }

    @Override
    public void run() {

        try {
            pythonClose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void open() {
        Thread opener = new Thread(new OpenControl());
        opener.start();
    }

}
