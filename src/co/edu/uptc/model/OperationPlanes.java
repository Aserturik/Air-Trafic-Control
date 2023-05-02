package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.view.globals.ValuesGlobals;
import util.UtilImages;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OperationPlanes {
    private static List<Plane> planes;
    private static JLabel imageLabel;

    public List<Plane> getPlanes() {
        planes = new java.util.ArrayList<>();
        planes.add(new Plane(getPlaneImage(), 100, 600));
        planes.add(new Plane(getPlaneImage(), 350, 50));
        planes.add(new Plane(getPlaneImage(), 50, 300));
        planes.add(new Plane(getPlaneImage(), 850, 350));
        return planes;
    }

    public ImageIcon getPlaneImage() {
        UtilImages utilImages = new UtilImages();
        imageLabel = new JLabel();
        imageLabel.setBounds(10, 10, 40, 40);
        Icon img = utilImages.loadScaleImage(ValuesGlobals.PHAT_PLANE_IMAGE_ORIGINAL, imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon(img);
        return new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT));
    }
}
