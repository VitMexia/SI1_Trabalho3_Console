package isel.si1.console;

import isel.si1.console.presentationlayer.Console;



public class Main {

    public static void main(String[] args) {
        if (!Configuration.getInstance().ConfigurationLoadSucess) {
            System.out.println("Unable to load configuration.");
            System.out.println("Unable to proceed!. Please check the following error:");
            System.out.println(Configuration.getInstance().ConfigurationLoadDescription);
            return;
        }


        try {
            (new Console()).run();
        }
        catch (Exception e) {
            System.out.println("Sorry something silly happend!!!");
            System.out.println("Cause: " + e.getMessage());
            System.out.println("Program aborted.");
            e.printStackTrace();
        }
    }
}
