import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Closer {
    public static void main (String[] args) throws Exception {
        Date date = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println("date: " + date);
        System.out.println("hour: " + hour);
        if (hour < 8){
            CloseControl.close();
        } else {System.out.println("too late in day");};



    }
}
