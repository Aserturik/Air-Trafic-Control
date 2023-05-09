package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;

public interface Contract {
    public interface View {
        void start();

        void setPresenter(Presenter presenter);

        void startGame();

        void restartGame();

        void pauseGame();

        void resumeGame();

        void stopGame();

        void paintPlanes(List<Plane> planes);

        void chargeBackground();
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        List<Plane> getPlanes();

        void isSelectedPlane(Point point);
        void addPointToPath(Point point);

        Contract.Model getModel();

        View getView();

        void notifyModel();

        void pauseGame();

        void selectedPlaneNull();
    }

    public interface Model {
        void setPresenter(Presenter presenter);

        List<Plane> getPlanes();
        void setPlanes(List<Plane> planes);

        void addPointToPath(Point point);

        void startGame();

        void RestartGame();

        void pauseGame();

        void resumeGame();

        void stopGame();

        String numberPlanesSetDown();

        String numberPlanesInAir();

        String timeGame();

        void isSelectedPlane(Point point);

        Plane getPlaneSelected(Point point);

        void viewIsReady();

        void selectedPlaneNull();
    }
}
