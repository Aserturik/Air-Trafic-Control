package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilImages {

    public static Image getImageBackground() {
         return new UtilImages().loadImage("assets/airport.png");
    }

    public BufferedImage loadImage(String imagenName) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(imagenName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return img;
    }


    public Image scaleImage(Image img, int width, int height) {
        ImageIcon imgIcon = new ImageIcon(img);
        Image scaledImage = imgIcon.getImage().getScaledInstance(width,
                height, Image.SCALE_SMOOTH);
        return scaledImage;
    }


    public Icon loadScaleImage(String imagenName, int width, int height) {
        Image img = loadImage(imagenName);
        Image img2 = scaleImage(img, width, height);
        Icon scaledIcon = new ImageIcon(img2);
        return scaledIcon;
    }


}
