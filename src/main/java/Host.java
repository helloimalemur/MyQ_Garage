public class Host {
    String name;
    String address;
    boolean newcheck;
    boolean oldcheck;
    boolean cond;
    IntervalTimer timer;
    DiscordNotif discordNotif;


    Host(String s, int a, boolean b) throws Exception {
        this.address = s;
        this.name = s;
        this.newcheck = true;
        this.oldcheck = true;
        this.timer = new IntervalTimer(a);
        timer.setDisConnTime();
        this.cond = b;
        this.discordNotif = new DiscordNotif();
    }



    public void checkHostStatus() throws Exception {
        newcheck = CheckHost.check(address);
        //debug
        //System.out.println("Checking: " + address);


        if ((oldcheck != newcheck) && (newcheck)) {
            timer.setConnTime();
            //check if enough time has passed and opengarage
            if ((!oldcheck) && (newcheck)) {
                System.out.println("----------------------");
                System.out.println(this.address + " connected");
                if (timer.calcTimePassed() && CheckTime.check()) {
                    String message = "attempting to open garage ..";
                    OpenControl.open();
                    System.out.println(message);
                    discordNotif.sendNotif(message);
                }
            }
            oldcheck = newcheck;
        }


        if ((oldcheck != newcheck) && (!newcheck)) {

            if ((oldcheck) && (!newcheck)) {
                timer.setDisConnTime();
                System.out.println("----------------------");
                System.out.println(this.address + " disconnected");
                if (Main.closeondiscon) {
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
