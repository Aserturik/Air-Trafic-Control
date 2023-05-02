package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.util.List;

public class ManagerModel implements Contract.Model {
    private Contract.Presenter presenter;
    private OperationPlanes operationPlanes;

    public ManagerModel() {
        operationPlanes = new OperationPlanes();
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
