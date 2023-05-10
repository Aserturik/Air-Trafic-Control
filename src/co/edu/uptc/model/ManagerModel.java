package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.awt.*;
import java.util.List;

public class ManagerModel implements Contract.Model {
    private Contract.Presenter presenter;
    private OperationPlanes operationPlanes;

    public ManagerModel() {
        operationPlanes = new OperationPlanes(this);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPointToPath(int id,Point point) {
        operationPlanes.addPointToPath(id,point);
    }

    @Override
    public void setPlanes(List<Plane> planes) {
        presenter.getView().paintPlanes(planes);
    }

    @Override
    public void startGame() {
        operationPlanes.startGame();
    }

    @Override
    public void pauseGame() {
        operationPlanes.pauseGame();
    }

    @Override
    public int isSelectedPlane(Point point) {
        return operationPlanes.isSelectedPlane(point);
    }

    @Override
    public void viewIsReady() {
        operationPlanes.viewIsReady();
    }

    @Override
    public void selectedPlaneNull(int id) {
        operationPlanes.selectedPlaneNull(id);
    }

    @Override
    public void setLandedPlanes(int landedPlanes) {
        presenter.getView().setLandedPlanes(landedPlanes);
    }

    @Override
    public void gameOver() {
        presenter.getView().gameOver();
    }

    @Override
    public void setSpeed(int speed) {
        operationPlanes.setSpeed(speed);
    }
}
