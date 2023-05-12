package util;

import java.awt.*;

public class ValuesGlobals {
    public static final String PHAT_AIRPORT_IMAGE_ORIGINAL = "assets/planeStreet.png";
    public static final int WIDTH_FRAME = 900;
    public static final int WIDTH_MENU = 700;
    public static final int HEIGHT_FRAME = 600;
    public static final int HEIGHT_MENU = 400;
    public static final long TIME_SLEEP = 50;
    public static final int TIME_GENERATE_PLANE = 6000;
    public static final long TIME_ELIMINATE_PLANE = 80;
    public static final Rectangle LANDED_RECTANGLE = new Rectangle(300, 250, 46, 46);

    public static Point getCenterFrame() {
        return new Point(WIDTH_FRAME / 2, HEIGHT_FRAME / 2);
    }
}
