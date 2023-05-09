package co.edu.uptc.model;

import java.util.Calendar;
import java.util.Date;

public class Cronometer {
    private int initialTime;
    private static Cronometer instance;

    private Cronometer() {

    }

    public void start() {
        initialTime = Calendar.getInstance().get(Calendar.SECOND);
    }

    public static Cronometer getInstance() {
        return instance == null ? instance = new Cronometer() : instance;
    }

    public String getTime() {
        int time = Calendar.getInstance().get(Calendar.SECOND) - initialTime;
        return time + "";
    }

    public void stop() {
        initialTime = 0;
    }
}
