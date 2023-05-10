package co.edu.uptc.presenter;

import java.awt.*;

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
    public int isSelectedPlane(Point point) {
        return model.isSelectedPlane(point);
    }

    @Override
    public void addPointToPath(int id, Point point) {
        model.addPointToPath(id, point);
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
    public void selectedPlaneNull(int id) {
        model.selectedPlaneNull(id);
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
