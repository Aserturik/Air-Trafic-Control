package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.view.globals.ValuesGlobals;
import co.edu.uptc.view.panels.PrincipalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class MyFrame extends JFrame implements Contract.View {
    private Contract.Presenter presenter;
    private PrincipalPanel principalPanel;

    public MyFrame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        initComponents();
        setSizes();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME + 36);
        this.setPreferredSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME + 36));
        this.setMinimumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME + 36));
        this.setMaximumSize(new Dimension(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME + 36));
        this.setLocationRelativeTo(null);
        //System.out.println("El tamaño del Frame es: " + this.getWidth() + " " + this.getHeight());
        this.setResizable(false);
    }

    private void initComponents() {
        principalPanel = new PrincipalPanel(this);
        this.add(principalPanel);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void startGame() {
        principalPanel.getPanelGame().paintRecorrides();
    }

    @Override
    public void restartGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void stopGame() {

    }

    @Override
    public void paintPlanes(List<Plane> planes) {
        principalPanel.getPanelGame().setPlanes(planes);
        principalPanel.getPanelGame().repaint();
    }

    public Contract.Presenter getPresenter() {
        return presenter;
    }

    public List<Plane> getModelPhoto() {
        return presenter.getPlanes();
    }

    public void showMenu() {
        principalPanel.showMenu();
    }

    public void showGame() {
        principalPanel.showGame();
    }

    public void selectedPlaneNull() {
        presenter.selectedPlaneNull();
    }
}
