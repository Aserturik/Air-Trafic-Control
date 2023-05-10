package co.edu.uptc.view.panels;

import co.edu.uptc.model.Cronometer;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.view.MyFrame;
import util.ValuesGlobals;
import util.UtilImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private MyFrame frame;
    private Graphics2D g2d;
    private List<Plane> planes;
    private ImageIcon imagePlane;
    private ImageIcon imageAirPort;
    private JLabel imageLabel;
    private Font font;
    private int landedPlanes;
    private RenderingHints renderingHints;
    private String imagePlaneSelected = "assets/planeYellow.png";

    public PanelGame(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        planes = new ArrayList<Plane>();
        imagePlane = getImagePlane();
        imageAirPort = getImageAirPort();
        font = new Font("Arial", Font.BOLD, 12);
        this.setBackground(Color.BLACK);
        initComponents();
        setSizes();
    }

    private void initComponents() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        renderingHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.setVisible(true);
    }


    public void chargeBackground(Graphics2D g2d) {
        g2d.drawImage(imageAirPort.getImage(), 300, 250, null);
        g2d.setColor(Color.GREEN);
        g2d.drawRect(ValuesGlobals.LANDED_RECTANGLE.x, ValuesGlobals.LANDED_RECTANGLE.y, ValuesGlobals.LANDED_RECTANGLE.width, ValuesGlobals.LANDED_RECTANGLE.height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        chargeBackground(g2d);
        drawAllPlanes(g2d);
        drawAllPaths(g2d);
        g2d.drawRect(0, 0, ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHints(renderingHints);
        g2d.setFont(font);
        printNumberPlanes();
        frame.getPresenter().notifyModel();
    }

    private void printNumberPlanes() {
        g2d.drawString("Cantidad de Aviones: " + planes.size(), 10, 20);
        g2d.drawString("Cantidad de Aviones Aterrizados: " + landedPlanes, 220, 20);
        g2d.drawString("Tiempo de Juego: " + Cronometer.getInstance().getTime(), 10, 40);
    }

    public void drawAllPlanes(Graphics2D g2d) {
        for (Plane plane : planes) {
            drawImage(plane, g2d);
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
    }


    private ImageIcon getImageAirPort() {
        UtilImages utilImages = new UtilImages();
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 192, 48);
        Icon img = utilImages.loadScaleImage(ValuesGlobals.PHAT_AIRPORT_IMAGE_ORIGINAL, imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon(img);
        return new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT));
    }

    private ImageIcon getImagePlane() {
        UtilImages utilImages = new UtilImages();
        imageLabel = new JLabel();
        imageLabel.setBounds(10, 10, 40, 40);
        Icon img = utilImages.loadScaleImage(imagePlaneSelected, imageLabel.getWidth(), imageLabel.getHeight());
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
        this.setVisible(true);
    }

    public void setLandedPlanes(int landedPlanes) {
        this.landedPlanes = landedPlanes;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Se hizo click en: " + e.getPoint());
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

    public void gameOver() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("GAME OVER");
        if (optionPane.showConfirmDialog(this, "El numero de aviones aterrizados es " + landedPlanes + "\n El tiempo es: " + Cronometer.getInstance().getTime() + "\n¿Desea volver a jugar?", "GAME OVER", optionPane.YES_NO_OPTION) == optionPane.YES_OPTION) {
            frame.setVisible(false);
            frame.getPresenter().restartGame();
        } else {
            System.exit(0);
        }
    }

    public void setImagePlaneSelected(String colorPlaneSelected) {
        imagePlaneSelected = colorPlaneSelected;
        imagePlane.setImage(new ImageIcon((imagePlaneSelected)).getImage());
    }
}
