package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerModel implements Contract.Model {
    private Contract.Presenter presenter;
    private OperationPlanes operationPlanes;
    private ArrayList<Plane> planes;

    public ManagerModel() {
        planes = new ArrayList<>();
        operationPlanes = new OperationPlanes();
    }

    private void randomPlanesGenerator(){

    }

    private void createPlane(){
        // TODO: crear un hilo nuevo que le mande el Avion al presenter
    }

    public void addPointToPath(Plane plane, Point point) {
        plane.getPath().add(point);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<Plane> getPlanes() {
        return operationPlanes.getPlanes();
    }


}
