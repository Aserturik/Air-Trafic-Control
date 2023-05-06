package co.edu.uptc.view.globals;

import java.awt.*;

public class ValuesGlobals {
    public static final String PHAT_PLANE_IMAGE_ORIGINAL = "assets/image.png";
    public static final String PHAT_BACKGROUND_IMAGE = "assets/background.jpg";
    public static final int WIDTH_FRAME = 900;
    public static final int HEIGHT_FRAME = 600;
    public static final long TIME_SLEEP = 100;

    public static Point getCenterFrame() {
        return new Point(WIDTH_FRAME / 2, HEIGHT_FRAME / 2);
    }
}
