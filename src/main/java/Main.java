import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static boolean close_on_disconnection;
    public static void main(String[] args) {
        //load properties file
        Properties props = new Properties();
        String fileName = "/usr/share/myq/myq.config";

        try (FileInputStream fin = new FileInputStream(fileName)) {
            props.load(fin);
        } catch (FileNotFoundException fnf) {
            System.out.println("config not found");
            System.out.println(fnf);
        } catch (IOException ioe) {
            System.out.println("check config");
            System.out.println(ioe);
        }

        //time away before garage will open threshold setting
        int threshold = Integer.parseInt(props.getProperty("app.threshold")); //in minutes
        System.out.println("Threshold: " + threshold);

        //close on disconnect setting
        close_on_disconnection = Boolean.parseBoolean(props.getProperty("app.closeondiscon"));
        System.out.println("Close on Disconnect: " + close_on_disconnection);

        //total Hosts/participants setting
        int participants = Integer.parseInt(props.getProperty("app.totalparticipants")); //in minutes
        System.out.println("participants: " + participants);

        //time constraint settings
        int timeConstraintStart = Integer.parseInt(props.getProperty("app.timeconstraintstart")); //in hour of day
        System.out.println("time Constraint Start: " + timeConstraintStart);
        int timeConstraintEnd = Integer.parseInt(props.getProperty("app.timeconstraintend")); //in hour of day
        System.out.println("time Constraint End: " + timeConstraintEnd);


        //create list of Host objects
        List<Host> hosts = new ArrayList<>() {};

        try {
            for (int i=0; i<participants; i++) {
                String hostAddress = props.getProperty("app.participant"+i);
                hosts.add(i, new Host(hostAddress, threshold, close_on_disconnection, timeConstraintStart, timeConstraintEnd));
            }
        } catch (Exception e) {System.out.println(e.getMessage());}

        try {
            while (true) {
                for (Host host : hosts) {
                    host.checkHostStatus();
                    Thread.sleep(1500);
                }
            }
        } catch (Exception e) {System.out.println(e.getMessage());}
        //loop list of Host objects
    }
}