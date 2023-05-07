package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.awt.*;
import java.time.LocalDate;
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
    public void addPointToPath(Plane plane, Point point) {
        operationPlanes.addPointToPath(plane, point);
    }

    @Override
    public List<Plane> getPlanes() {
        //return operationPlanes.getPlanes();}
         return null;
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
    public void RestartGame() {

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
    public String numberPlanesSetDown() {
        return null;
    }

    @Override
    public String numberPlanesInAir() {
        return null;
    }

    @Override
    public String timeGame() {
        return null;
    }

    @Override
    public void isSelectedPlane(Point point) {
        operationPlanes.isSelectedPlane(point);
    }

    @Override
    public Plane getPlaneSelected(Point point) {
        return null;
    }

    @Override
    public void viewIsReady() {
        operationPlanes.viewIsReady();
    }
}
