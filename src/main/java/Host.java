public class Host {
    String name;
    String address;
    boolean newcheck;
    boolean oldcheck;
    boolean cond;
    IntervalTimer intervalTimer;
    DiscordNotif discordNotif;


    Host(String s, int a, boolean b) throws Exception {
        this.address = s;
        this.name = s;
        this.newcheck = true;
        this.oldcheck = true;
        this.intervalTimer = new IntervalTimer(a);
        intervalTimer.setDisConnTime();
        this.cond = b;
        this.discordNotif = new DiscordNotif();
    }



    public void checkHostStatus() throws Exception {
        newcheck = CheckHost.check(address);
        //debug
        //System.out.println("Checking: " + address);


        if ((oldcheck != newcheck) && (newcheck)) { //if state change and is connected
            intervalTimer.setConnTime(); //set connection time

            if ((!oldcheck) && (newcheck)) { //verify change was from disconnected to connected
                System.out.println("----------------------");
                System.out.println(this.address + " connected");
                if (intervalTimer.calcTimePassed() && CheckTime.check()) { //verify enough time has passed
                    String message = "attempting to open garage ..";
                    OpenControl.open();
                    System.out.println(message);
                    discordNotif.sendNotif(message);
                }
            }
            oldcheck = newcheck;
        }


        if ((oldcheck != newcheck) && (!newcheck)) { //if state change and is NOT connected

            if ((oldcheck) && (!newcheck)) { //verify change was from connected to disconnected
                intervalTimer.setDisConnTime(); //set disconnection time
                System.out.println("----------------------");
                System.out.println(this.address + " disconnected");
                if (Main.closeondiscon) { //check 'close on disconnect' config setting
                    String message = "Closing garage ..";
                    CloseControl.close();
                    System.out.println(message);
                    discordNotif.sendNotif(message);
                }
            }
            oldcheck = newcheck;
        } else {
            oldcheck = newcheck;
        }
    }
}
