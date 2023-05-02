package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.view.globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener {
    private JFrame frame;
    private Graphics2D g2d;
    private List<Plane> planes;
    private Plane selectedPlane;
    private Point lastMousePos;
    private List<Point> temporalPath;

    public PanelGame(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        planes = new ArrayList<>();
        temporalPath = new ArrayList<>();
        this.setBackground(Color.BLACK);
        setSizes();
        initComponents();
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
        for (Plane plane : planes) {
            plane.draw(g2d);
        }
    }
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Dibujar aviones y cualquier otro objeto en el panel
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        for (Plane plane : planes) {
            plane.draw(g2d);
        }

        // Dibujar una línea de puntos roja desde el avión seleccionado hasta la posición del ratón
        if (selectedPlane == null) {
            g2d.setColor(Color.white);
            g2d.fillRect(getCenterPanel().x, getCenterPanel().y, 10, 10);
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
            temporalPath.add(e.getPoint());
            selectedPlane.setPath(temporalPath);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
