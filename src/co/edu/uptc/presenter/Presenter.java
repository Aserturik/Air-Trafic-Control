package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.awt.*;
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
    public void startGame() {
         model.startGame();
    }

    @Override
    public List<Plane> getPlanes() {
        return model.getPlanes();
    }

    @Override
    public void isSelectedPlane(Point point) {
        model.isSelectedPlane(point);
    }

    @Override
    public void addPointToPath(Plane plane, Point point) {

    }

    @Override
    public Contract.Model getModel() {
        return model;
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
}
