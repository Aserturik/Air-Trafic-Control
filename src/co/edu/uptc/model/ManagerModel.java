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
    public void addPointToPath(Point point) {
        operationPlanes.addPointToPath(point);
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
    public void isSelectedPlane(Point point) {
        operationPlanes.isSelectedPlane(point);
    }

    @Override
    public void viewIsReady() {
        operationPlanes.viewIsReady();
    }

    @Override
    public void selectedPlaneNull() {
        operationPlanes.selectedPlaneNull();
    }

    @Override
    public void setLandedPlanes(int landedPlanes) {
        presenter.getView().setLandedPlanes(landedPlanes);
    }

    @Override
    public void gameOver() {
        presenter.getView().gameOver();
    }
}
