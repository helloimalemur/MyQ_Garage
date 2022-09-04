import java.time.LocalDate;
import java.time.LocalTime;

public class TimerTest {

    int thresholdinmins;
    int hourspassed;
    int minspassed;
    int dayspassed;
    int secondspassed;
    int[] conn = new int[5];//hour,min,day
    int[] diss = new int[5];
    boolean pass;

    public void setDissconValues(int a, int b, int c, int d) {
        diss[0] = a;
        diss[1] = b;
        diss[2] = c;
        diss[3] = d;
        System.out.println("disconnected: ");
        System.out.println("seconds; " + diss[0]);
        System.out.println("mins; " + diss[1]);
        System.out.println("hours; " + diss[2]);
        System.out.println("days; " + diss[3]);
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


    public void calc() {

        if (conn[3] < diss[3]) { //check days passed
            dayspassed = 30 - (Math.abs(conn[3] - diss[3]));
        } else {
            dayspassed = Math.abs(conn[3] - diss[3]);
        }


        if (conn[2] < diss[2]) { // check the hours passed
            System.out.println("if triggered");
            hourspassed = Math.abs(conn[2] - diss[2]);
        } else {
            hourspassed = Math.abs(conn[2] - diss[2]);
        }


        if (conn[1] < diss[1]) { // check mins passed
            minspassed = 60-(Math.abs(conn[1] - diss[1]));
        } else {
            minspassed = conn[1] - diss[1];
        }


        if (conn[0] < diss[0]) { // check seconds passed
            secondspassed = 60-(Math.abs(conn[0] - diss[0]));
        } else {
            secondspassed = conn[0] - diss[0];
        }



        pass = false;
        if (Math.abs(dayspassed) > 0) {pass = false;} // we're only going to fire on same-day leave/return (debug)
        if (Math.abs(hourspassed) > 0 ) {pass = true;}
        if (Math.abs(minspassed) > thresholdinmins) {pass = true;}

        System.out.println("TimePassed: " + "Secs: " + secondspassed + " Mins: " + minspassed + " Hours: " + hourspassed + " Days: " + dayspassed);
        System.out.println("Theshold in mins: " + thresholdinmins);
        System.out.println(pass);
    }
    public static void main(String[] arg) {
        TimerTest timerTest = new TimerTest();
        System.out.println(LocalTime.now().getSecond());
        System.out.println(LocalTime.now().getMinute());
        System.out.println(LocalTime.now().getHour());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println("----------------------");
        timerTest.resetValues();

        timerTest.setDisConnTime();
        timerTest.setDissconValues(59, 59,2,3);
        timerTest.setConnTime();
        timerTest.calc();
    }
}

