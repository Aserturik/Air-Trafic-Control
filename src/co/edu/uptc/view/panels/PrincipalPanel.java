package co.edu.uptc.view.panels;

import co.edu.uptc.view.MyFrame;
import co.edu.uptc.view.globals.ValuesGlobals;
import co.edu.uptc.view.panels.PanelGame;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private PanelGame panelGame;
    private MyFrame frame;

    public PrincipalPanel(MyFrame myFrame) {
        super();
        this.frame = myFrame;
        panelGame = new PanelGame(myFrame);
        this.add(panelGame);
        this.setSizes();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME));
        this.setLayout(new BorderLayout());
        this.setLocation(0, 0);
        //System.out.println("El tamaño del PrincipalPanel es: " + this.getWidth() + " " + this.getHeight());
        this.setVisible(true);
    }

    public PanelGame getPanelGame() {
        return panelGame;
    }
}
