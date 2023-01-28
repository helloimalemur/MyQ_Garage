public class Host {
    String name;
    String address;
    boolean newCheck;
    boolean oldCheck;
    IntervalTimer intervalTimer;
    TimeConstraint timeConstraint;

    Host(String s, int a, boolean b, int timeConstraintStart, int timeConstraintEnd) throws Exception {
        this.address = s;
        this.name = s;
        this.newCheck = false;
        this.oldCheck = false;
        this.intervalTimer = new IntervalTimer(a);
        intervalTimer.setDisConnTime();
        intervalTimer.resetValues();
        timeConstraint = new TimeConstraint(timeConstraintStart, timeConstraintEnd);
    }

    public void checkHostStatus() throws Exception {
        newCheck = CheckHost.check(address);
        //debug
        //System.out.println("Checking: " + address);


        if ((oldCheck != newCheck) && (newCheck)) { //if state change and is connected
            intervalTimer.setConnTime(); //set connection time

            if ((!oldCheck) && (newCheck)) { //verify change was from disconnected to connected
                System.out.println("----------------------");
                System.out.println(this.address + " connected");
                if (intervalTimer.calcTimePassed() && timeConstraint.check()) { //verify enough time has passed
                    String message = "attempting to open garage ..";
                    OpenControl.open();
                    intervalTimer.resetValues();
                    System.out.println(message);
                    DiscordNotif.sendNotif(message);
                }
            }
            oldCheck = newCheck;
        }


        if ((oldCheck != newCheck) && (!newCheck)) { //if state change and is NOT connected

            if ((oldCheck) && (!newCheck)) { //verify change was from connected to disconnected
                intervalTimer.setDisConnTime(); //set disconnection time
                System.out.println("----------------------");
                System.out.println(this.address + " disconnected");
                if (Main.close_on_disconnection) { //check 'close on disconnect' config setting
                    CloseControl.close(address);
                    intervalTimer.resetValues();

                }
            }
            oldCheck = newCheck;
        }
    }
}
