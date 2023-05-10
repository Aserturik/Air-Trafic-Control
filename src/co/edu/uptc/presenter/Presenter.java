package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;

public class Presenter implements Contract.Presenter {
    private ManagerGeneral managerGeneral;

    public Presenter(ManagerGeneral managerGeneral) {
        this.managerGeneral = managerGeneral;
    }

    Contract.View view;
    Contract.Model model;

    @Override
    public void setModel(Contract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(Contract.View view) {
        this.view = view;
    }

    @Override
    public void startGame() {
        model.startGame();
    }

    @Override
    public void isSelectedPlane(Point point) {
        model.isSelectedPlane(point);
    }

    @Override
    public void addPointToPath(Point point) {
        model.addPointToPath(point);
    }

    @Override
    public Contract.View getView() {
        return view;
    }

    @Override
    public void notifyModel() {
        model.viewIsReady();
    }

    @Override
    public void pauseGame() {
        model.pauseGame();
    }

    @Override
    public void selectedPlaneNull() {
        model.selectedPlaneNull();
    }

    @Override
    public void restartGame() {
        managerGeneral.restartGame();
    }

    @Override
    public void setPlaneSpeed(int speed) {
        model.setSpeed(speed);
    }
}
