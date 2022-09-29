import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeConstraint {
    int startHour;
    int endHour;
    TimeConstraint(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean check () {
        Date date = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println("date: " + date);
        System.out.println("hour: " + hour);
//        if (hour < 8 || hour > 20){
        if (hour < endHour || hour > startHour){
            System.out.println("fail");
            return false;
        } else {
            System.out.println("pass");
            return true;
        }
    }
}
