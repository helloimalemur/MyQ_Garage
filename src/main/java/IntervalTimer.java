import java.time.LocalDate;
import java.time.LocalTime;

public class IntervalTimer {
    int thresholdinmins;

    int secondspassed;
    int minspassed;
    int hourspassed;
    int dayspassed;

    int[] conn = new int[4];//sec,min,hour,day
    int[] diss = new int[4];
    boolean pass;
    DiscordNotif discordNotif;

    IntervalTimer(int a) throws Exception {

        thresholdinmins = a;
        discordNotif = new DiscordNotif();
    }

    public void resetValues() {
        secondspassed = 0;
        minspassed = 0;
        hourspassed = 0;
        dayspassed = 0;
    }

    public void setDisConnTime() {
        int dhour = LocalTime.now().getHour();
        int dmin = LocalTime.now().getMinute();
        int dday = LocalDate.now().getDayOfMonth();
        int dsec = LocalTime.now().getSecond();
        diss[0] = dsec;
        diss[1] = dmin;
        diss[2] = dhour;
        diss[3] = dday;

    }

    public void setConnTime() {

        int csec = LocalTime.now().getSecond();
        int cmin = LocalTime.now().getMinute();
        int chour = LocalTime.now().getHour();
        int cday = LocalDate.now().getDayOfMonth();
        conn[0] = csec;
        conn[1] = cmin;
        conn[2] = chour;
        conn[3] = cday;
    }


    public boolean calcTimePassed() throws Exception {

        if (conn[0] < diss[0]) { // check seconds passed
            secondspassed = 60-(Math.abs(conn[0] - diss[0]));
        } else {
            secondspassed = conn[0] - diss[0];
        }

        if (conn[1] < diss[1]) { // check mins passed
            minspassed = 60-(Math.abs(conn[1] - diss[1]));
        } else {
            minspassed = conn[1] - diss[1];
        }


        if (!(diss[2] == conn[2])) { // check the hours passed
            if (conn[2] < diss[2]) {
                //hourspassed = 24 - (diss[2] - conn[2]);
                hourspassed = 0;
                dayspassed += 1;
            }
            hourspassed = conn[2] - diss[2];
        }

        dayspassed += Math.abs(conn[3] - diss[3]); // days passed

        pass = false;
        if (Math.abs(dayspassed) > 0) {pass = false;} // we're only going to fire on same-day leave/return (debug)
        if (Math.abs(hourspassed) > 0 ) {pass = true;}
        if (Math.abs(minspassed) > thresholdinmins) {pass = true;}

        discordNotif.sendNotif("TimePassed: " + "Secs: " + secondspassed + "Mins: " + minspassed + "Hours: " + hourspassed + "Days: " + dayspassed + " Of theshold: " + thresholdinmins);
        System.out.println("TimePassed: " + "Secs: " + secondspassed + "Mins: " + minspassed + "Hours: " + hourspassed + "Days: " + dayspassed);
        System.out.println("Theshold in mins: " + thresholdinmins);
        System.out.println(pass);
        return pass;
    }
}
