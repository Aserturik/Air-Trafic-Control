package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.view.globals.ValuesGlobals;
import co.edu.uptc.view.panels.PrincipalPanel;

import javax.swing.*;
import java.util.List;

public class MyFrame extends JFrame implements Contract.View {
    private Contract.Presenter presenter;
    private PrincipalPanel principalPanel;

    public MyFrame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        setSizes();
    }

    private void setSizes() {
        this.setSize(ValuesGlobals.WIDTH_FRAME, ValuesGlobals.HEIGHT_FRAME);
        this.setPreferredSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.setMaximumSize(this.getSize());
        this.setLocationRelativeTo(null);
        System.out.println("El tamaño del Frame es: " + this.getWidth() + " " + this.getHeight());
        //this.setResizable(false);
    }

    private void initComponents() {
        principalPanel = new PrincipalPanel(this);
        this.add(principalPanel);
    }

    @Override
    public void start() {
        initComponents();
        this.setVisible(true);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void startRecorride() {
        principalPanel.getPanelGame().paintRecorride();
    }

    public Contract.Presenter getPresenter() {
        return presenter;
    }

    public List<Plane> getModelPhoto() {
         return presenter.getPlanes();
    }
}
