package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerModel implements Contract.Model {
    private Contract.Presenter presenter;
    private OperationPlanes operationPlanes;

    public ManagerModel() {
        operationPlanes = new OperationPlanes();
    }

    public void addPointToPath(Plane plane, Point point) {
        operationPlanes.addPointToPath(plane, point);
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
