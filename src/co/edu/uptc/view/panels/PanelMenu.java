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

    public PanelMenu(MyFrame frame) {
        super();
        this.frame = frame;
        this.gbc = new GridBagConstraints();
        this.addKeyListener(this);
        this.setSizes();
        initComponents();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU);
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_MENU, ValuesGlobals.HEIGHT_MENU));
        this.setVisible(false);
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.add(new JLabel("Menu"));
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
