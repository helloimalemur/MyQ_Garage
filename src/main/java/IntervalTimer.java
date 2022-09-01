import java.time.LocalDate;
import java.time.LocalTime;

public class IntervalTimer {
    int thresholdinmins;
    int hourspassed;
    int minspassed;
    int dayspassed;
    int secondspassed;
    int[] conn = new int[5];//hour,min,day
    int[] diss = new int[5];
    boolean pass;

    IntervalTimer(int a){
        thresholdinmins = a;
    }


    public void setDisConnTime() {
        int dhour = LocalTime.now().getHour();
        int dmin = LocalTime.now().getMinute();
        int dday = LocalDate.now().getDayOfMonth();
        int dsec = LocalTime.now().getSecond();
        diss[0] = dhour;
        diss[1] = dmin;
        diss[3] = dday;
        diss[4] = dsec;
    }

    public void setConnTime() {
        int chour = LocalTime.now().getHour();
        int cmin = LocalTime.now().getMinute();
        int cday = LocalDate.now().getDayOfMonth();
        int csec = LocalTime.now().getSecond();
        conn[0] = chour;
        conn[1] = cmin;
        conn[3] = cday;
        conn[4] = csec;
    }


    public boolean calcTimePassed() {

        if (!(diss[0] == conn[0])) {
            //if (conn[0] == 1) { // if 12 hour format
            //    conn[0] = 13;
            //    hourspassed = conn[0] - diss[0];
            //} else {
            //    hourspassed = conn[0] - diss[0];
            //}
            if (conn[0] < diss[0]) {
                hourspassed = Math.abs(conn[0] - diss[0]);
            }
            hourspassed = conn[0] - diss[0];

        }


        if (conn[1] < diss[1]) {
            minspassed = 60-(Math.abs(conn[1] - diss[1]));
        } else {
            minspassed = conn[1] - diss[1];
        }

        dayspassed = Math.abs(conn[3] - diss[3]);

        if (conn[4] < diss[4]) {
            secondspassed = 60-(Math.abs(conn[4] - diss[4]));
        } else {
            secondspassed = conn[4] - diss[4];
        }


        pass = false;

        System.out.println("Days:" + dayspassed);
        if (dayspassed > 0) {pass = true;}

        System.out.println("Hours:" + hourspassed);
        if (hourspassed > 0 ) {pass = true;}

        System.out.println("Mins:" + minspassed);
        if (minspassed > thresholdinmins) {pass = true;}

        System.out.println("Secs:" + secondspassed);
        System.out.println("Theshold in mins: " + thresholdinmins);
        System.out.println(pass);
        return pass;
    }
}
