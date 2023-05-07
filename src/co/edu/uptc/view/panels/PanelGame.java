package co.edu.uptc.view.panels;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.view.MyFrame;
import co.edu.uptc.view.globals.ValuesGlobals;
import util.UtilImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private MyFrame frame;
    private Graphics2D g2d;
    private List<Plane> planes;
    private ImageIcon imagePlane;
    private JLabel imageLabel;
    private Font font;

    public PanelGame(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        planes = new ArrayList<Plane>();
        imagePlane = getImagePlane();
        font = new Font("Arial", Font.BOLD, 12);
        this.setBackground(Color.BLACK);
        initComponents();
        setSizes();
    }

    private void initComponents() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        drawAllPlanes(g2d);
        drawAllPaths(g2d);
        g2d.drawRect(0, 0, ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        printNumberPlanes();
        frame.getPresenter().notifyModel();
    }

    private void printNumberPlanes() {
        g2d.setColor(Color.WHITE);
        FontRenderContext fontRenderContext = g2d.getFontRenderContext();
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(renderingHints);
        g2d.setFont(font);
        g2d.drawString("Cantidad de Aviones: " + planes.size(), 10, 20);
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

    private void drawAllPaths(Graphics2D g2d) {
        for (Plane plane : planes) {
            drawPath(plane, g2d);
        }
    }

    private void drawPath(Plane plane, Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        for (Plane plane1 : planes) {
            for (Point point : plane1.getPath()) {
                g2d.drawLine(point.x, point.y, point.x, point.y);
            }
        }
    }

    private void printInfoPlane(Plane plane) {
        //System.out.println("El avión está en: " + plane.getPosition().x + " " + plane.getPosition().y);
        //System.out.println("El avión tiene un ángulo de: " + plane.getAngle());
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
        g2d.setColor(Color.RED);
        Rectangle rectangle = plane.getRectangle();
        g2d.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2d.setTransform(tx);
        //System.out.println("Dibujando en: " + drawX + " " + drawY);
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
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setLayout(new BorderLayout());
        this.setLocation(0, 0);
        //System.out.println("El tamaño del PanelGame es: " + this.getWidth() + " " + this.getHeight());
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
        frame.selectedPlaneNull();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        frame.getPresenter().addPointToPath(e.getPoint());
        //Plane planeSelected = frame.getPresenter().getModel().getPlaneSelected(e.getPoint());
        //System.out.println("El avion seleccionado es: " + planeSelected);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == 0) {
            frame.showMenu();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
