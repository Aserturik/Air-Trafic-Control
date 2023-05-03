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
    private JFrame frame;
    private Graphics2D g2d;
    private List<Plane> planes;
    private Plane selectedPlane;
    private Point lastMousePos;
    private ImageIcon imagePlane;
    private JLabel imageLabel;

    public PanelGame(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        planes = new ArrayList<>();
        this.setBackground(Color.BLACK);
        imagePlane = getImagePlane();
        setSizes();
        initComponents();
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

    private void initComponents() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setVisible(true);
    }

    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        for (Plane plane : planes/home/alex/Desarrollo/Cuarto Semestre/JAVA/Proyecto Aviones Final MVP/src/co/edu/uptc/view/panels) {
            plane.draw(g2d);
        }
    }
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Dibujar aviones y cualquier otro objeto en el panel
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        drawAllPlanes();

        // Dibujar una línea de puntos roja desde el avión seleccionado hasta la posición del ratón
        if (selectedPlane == null) {
            g2d.setColor(Color.white);
            g2d.fillRect(getCenterPanel().x, getCenterPanel().y, 10, 10);
        }
    }

    private void drawAllPlanes() {
        for (Plane plane : planes) {

        }
    }

    private Point getCenterPanel() {
        int x = ValuesGlobals.getCenterFrame().x;
        int y = ValuesGlobals.getCenterFrame().y;
        return new Point(x, y);
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }


    private JFrame getFrame() {
        return this.frame;
    }

    public void paintRecorride() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void drawImage(Plane plane) {
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

    public boolean isColliding(Point point) {
        int x = point.x;
        int y = point.y;
        int imgWidth = imagePlane.getIconWidth();
        int imgHeight = imagePlane.getIconHeight();
        //return x >= position.x && x <= position.x + imgWidth && y >= position.y && y <= position.y + imgHeight;
         return false;
    }

    private void reOrderPlane() {

    }

    private Plane getPlaneSelected(int x, int y) {
        for (Plane plane : planes) {
            if (x >= plane.getPosition().x && x <= plane.getPosition().x + 40 && y >= plane.getPosition().y && y <= plane.getPosition().y + 40) {
                return plane;
            }
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: los metodos de pausa deben llamarse aqui
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
        selectedPlane = getPlaneSelected(e.getX(), e.getY());
        if (selectedPlane != null) {
            System.out.println("Has ganado");
        } else {
            System.out.println("Has perdido");
        }
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
        // TODO: los metodos de pintar el camino que sigue el avion deben llamarse aqui
        if (selectedPlane != null) {
            selectedPlane.addPoint(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
