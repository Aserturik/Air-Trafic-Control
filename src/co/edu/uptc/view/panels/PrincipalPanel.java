package co.edu.uptc.view.panels;

import co.edu.uptc.view.MyFrame;
import co.edu.uptc.view.globals.ValuesGlobals;
import co.edu.uptc.view.panels.PanelGame;

import javax.swing.*;

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
        this.setPreferredSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setLayout(null);
        System.out.println("El tamaño del PrincipalPanel es: " + this.getWidth() + " " + this.getHeight());
        this.setVisible(true);
    }

    public PanelGame getPanelGame() {
        return panelGame;
    }
}
