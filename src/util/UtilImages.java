package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilImages {

    public BufferedImage loadImage(String imagenName){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(imagenName));
        } catch( IOException e)
        {
           throw new RuntimeException(e);
        }
        return img;
   }


   public Image scaleImage(Image img,int width,int height){
       ImageIcon imgIcon = new ImageIcon(img);
       Image scaledImage = imgIcon.getImage().getScaledInstance(width,
               height, Image.SCALE_SMOOTH);
       Icon iconoEscalado = new ImageIcon(scaledImage);
       return scaledImage;
   }


   public Icon loadScaleImage(String imagenName,int width,int height){
       Image img = loadImage(imagenName);
       Image img2 = scaleImage(img,width,height);
       Icon scaledIcon = new ImageIcon(img2);
       return scaledIcon;
   }



}
