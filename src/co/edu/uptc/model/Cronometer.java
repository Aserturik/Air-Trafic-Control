package co.edu.uptc.model;

import java.util.Calendar;

public class Cronometer {
    private int initialTime;
    private int actualTime;
    private int timePause;
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

    public void continueTime() {
        actualTime = Calendar.getInstance().get(Calendar.SECOND);
        initialTime = initialTime + (actualTime - timePause);
    }

    public void pauseTime() {
        timePause = Calendar.getInstance().get(Calendar.SECOND);
    }
}
