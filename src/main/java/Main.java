import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Main {
    public static boolean closeondiscon;
    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        String fileName = "/usr/share/myq/myq.config";
        try (FileInputStream fin = new FileInputStream(fileName)) {
        props.load(fin);

        } catch (FileNotFoundException fnf) {
            System.out.println("config not found" + fnf);
        } catch (IOException ioe) {
            System.out.println("check config" + ioe);
        }
        int threshold = Integer.parseInt(props.getProperty("app.threshold")); //in minutes
        System.out.println("Threshold: " + threshold);
        closeondiscon = Boolean.parseBoolean(props.getProperty("app.closeondiscon"));
        System.out.println("Close on Disconnect: " + closeondiscon);
        int participants = Integer.parseInt(props.getProperty("app.totalparticipants")); //in minutes
        System.out.println("participants: " + participants);

        //create list of Host objects
        List<Host> hosts = new ArrayList<>() {};
        for (int i=0; i<participants; i++) {
            String ht = props.getProperty("app.participant"+i);
            boolean cc = Boolean.parseBoolean(props.getProperty("app.participant"+i+"closeondiscon"));
            hosts.add(i, new Host(ht, threshold, cc));
        }


        //for loop to check list of Host objects
        while (true) {
            for (int i=0; i<participants; i++) {
                hosts.get(i).checkHostStatus();
                Thread.sleep(1500);
            }
        }
    }
}