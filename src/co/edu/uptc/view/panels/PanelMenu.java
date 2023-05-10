package co.edu.uptc.view.panels;

import co.edu.uptc.view.MyFrame;
import util.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelMenu extends JPanel implements KeyListener {
    private MyFrame frame;
    private GridBagConstraints gbc;
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0, 0.5f);

    public PanelMenu(MyFrame frame) {
        super();
        this.frame = frame;
        this.addKeyListener(this);
        this.setSizes();
        initComponents();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU);
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setVisible(false);
    }

    private void initComponents() {
        title();
        velocityPlane();
        colorPlanes();
        selectPlane();
        optionButtons();
    }

    private void configGBC(int x, int y, int width, int height, int fill, int anchor) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        //gbc.fill = fill;
        gbc.anchor = anchor;
    }

    public void title() {
        configGBC(0, 0, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel title = new JLabel("Juego en Pausa");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(title, gbc);
    }

    public void velocityPlane() {
        configGBC(0, 1, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel velocityPlane = new JLabel("Velocidad del avión");
        velocityPlane.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(velocityPlane, gbc);
        configGBC(0, 2, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        sliderFeatures(speedSlider);
        this.add(speedSlider, gbc);
    }

    private void sliderFeatures(JSlider speedSlider) {
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBackground(BACKGROUND_COLOR);
        speedSlider.setForeground(Color.white);
        speedSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int speed = source.getValue();
                //presenter.setPlaneSpeed(planeSelected.getId(), speed);
                System.out.println(speed);
            }
        });
    }

    public void colorPlanes() {
        configGBC(0, 3, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel colorPlane = new JLabel("Color del avión");
        colorPlane.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(colorPlane, gbc);
        configGBC(0, 4, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel labelImagePlaneRed = new JLabel();
        labelImagePlaneRed.setIcon(new ImageIcon(("assets/plane.png")));
        this.add(labelImagePlaneRed, gbc);
        configGBC(1, 4, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel labelImagePlaneBlue = new JLabel();
        labelImagePlaneBlue.setIcon(new ImageIcon(("assets/plane.png")));
        this.add(labelImagePlaneBlue, gbc);
        configGBC(2, 4, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel labelImagePlaneGreen = new JLabel();
        labelImagePlaneGreen.setIcon(new ImageIcon(("assets/plane.png")));
        this.add(labelImagePlaneGreen, gbc);
    }

    public void selectPlane() {
        configGBC(0, 5, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel imageSelectedPlane = new JLabel("Avión seleccionado");
        imageSelectedPlane.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(imageSelectedPlane, gbc);

        configGBC(1, 5, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel labelImagePlaneSelected = new JLabel();
        labelImagePlaneSelected.setIcon(new ImageIcon(("assets/plane.png")));
        this.add(labelImagePlaneSelected, gbc);
    }

    public void optionButtons() {
        configGBC(0, 6, 2, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
         JButton buttonResume = new JButton("Reanudar");
         buttonResume.setFont(new Font("Arial", Font.BOLD, 20));
         buttonResume.setBackground(Color.white);
         buttonResume.setForeground(Color.black);
         buttonResume.addActionListener(e -> {
             frame.showGame();
         });
         this.add(buttonResume, gbc);

         configGBC(2, 6, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
         JButton buttonRestart = new JButton("Reiniciar");
         buttonRestart.setFont(new Font("Arial", Font.BOLD, 20));
         buttonRestart.setBackground(Color.white);
         buttonRestart.setForeground(Color.black);
         buttonRestart.addActionListener(e -> {
             frame.getPresenter().restartGame();
         });
         this.add(buttonRestart, gbc);

         configGBC(0, 7, 3, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
         JButton buttonExit = new JButton("Salir");
         buttonExit.setFont(new Font("Arial", Font.BOLD, 20));
         buttonExit.setBackground(Color.white);
         buttonExit.setForeground(Color.black);
         buttonExit.addActionListener(e -> {
             System.exit(0);
         });
         this.add(buttonExit, gbc);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == 0) {
            frame.showGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
