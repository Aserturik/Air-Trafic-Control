package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.util.List;

public class Presenter implements Contract.Presenter {
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
    public void startRecorride() {
         view.startRecorride();
    }

    @Override
    public List<Plane> getPlanes() {
        return model.getPlanes();
    }
}
