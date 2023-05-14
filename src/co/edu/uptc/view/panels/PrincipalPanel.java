package co.edu.uptc.view.panels;

import co.edu.uptc.view.MyFrame;
import util.ValuesGlobals;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private PanelGame panelGame;
    private MyFrame frame;
    private PanelMenu panelMenu;
    private GridBagConstraints gbc;

    public PrincipalPanel(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        this.setSizes();
        panelGame = new PanelGame(myFrame);
        this.add(panelGame, gbc);
        panelMenu = new PanelMenu(myFrame);
        this.add(panelMenu, gbc);
        panelGame.setFocusable(true);
        panelGame.requestFocus();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0, 0, 0));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.setVisible(true);
    }

    public PanelGame getPanelGame() {
        return panelGame;
    }

    public void showMenu() {
        panelGame.setVisible(false);
        panelMenu.setVisible(true);
        panelMenu.setFocusable(true);
        panelMenu.requestFocus();
        frame.getPresenter().pauseGame();
    }

    public void showGame() {
        panelMenu.setVisible(false);
        panelGame.setVisible(true);
        panelGame.setFocusable(true);
        panelGame.requestFocus();
        frame.getPresenter().pauseGame();
    }
}
