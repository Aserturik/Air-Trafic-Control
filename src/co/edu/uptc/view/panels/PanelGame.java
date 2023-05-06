package co.edu.uptc.view.panels;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.view.MyFrame;
import co.edu.uptc.view.globals.ValuesGlobals;
import util.UtilImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener {
    private MyFrame frame;
    private Graphics2D g2d;
    private List<Plane> planes;
    private ImageIcon imagePlane;
    private JLabel imageLabel;

    public PanelGame(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        planes = new ArrayList<Plane>();
        imagePlane = getImagePlane();
        this.setBackground(Color.BLACK);
        initComponents();
        setSizes();
    }

    private void initComponents() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        drawAllPlanes(g2d);
    }

    public void paintRecorrides() {
        //planes = getFrame().getModelPhoto();
        //drawAllPlanes();
    }

    public void drawAllPlanes(Graphics2D g2d) {
        for (Plane plane : planes) {
            drawImage(plane, g2d);
            printInfoPlane(plane);
        }
    }

    private void printInfoPlane(Plane plane) {
        System.out.println("El avión está en: " + plane.getPosition().x + " " + plane.getPosition().y);
        System.out.println("El avión tiene un ángulo de: " + plane.getAngle());
    }

    private MyFrame getFrame() {
        return this.frame;
    }

    private void drawImage(Plane plane, Graphics2D g2d) {
        double rotationRequired = Math.toRadians(plane.getAngle());
        AffineTransform tx = g2d.getTransform();

        int imageWidth = imagePlane.getIconWidth();
        int imageHeight = imagePlane.getIconHeight();
        int drawX = plane.getPosition().x - imageWidth / 2;
        int drawY = plane.getPosition().y - imageHeight / 2;

        g2d.rotate(rotationRequired, plane.getPosition().x, plane.getPosition().y);
        g2d.drawImage(imagePlane.getImage(), drawX, drawY, null);
        g2d.setTransform(tx);
        System.out.println("Dibujando en: " + drawX + " " + drawY);
    }

    private ImageIcon getImagePlane() {
        UtilImages utilImages = new UtilImages();
        imageLabel = new JLabel();
        imageLabel.setBounds(10, 10, 40, 40);
        Icon img = utilImages.loadScaleImage(ValuesGlobals.PHAT_PLANE_IMAGE_ORIGINAL, imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon(img);
        return new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT));
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        this.setPreferredSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setLayout(null);
        System.out.println("El tamaño del PanelGame es: " + this.getWidth() + " " + this.getHeight());
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        frame.getPresenter().isSelectedPlane(e.getPoint());
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Plane planeSelected = frame.getPresenter().getModel().getPlaneSelected(e.getPoint());
        System.out.println("El avion seleccionado es: " + planeSelected);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
