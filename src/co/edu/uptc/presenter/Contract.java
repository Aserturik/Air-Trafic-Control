package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;

public interface Contract {
    public interface View {
        void start();

        void setPresenter(Presenter presenter);

        void paintPlanes(List<Plane> planes);

        void setLandedPlanes(int landedPlanes);

        void gameOver();

        void dispose();
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        void isSelectedPlane(Point point);
        void addPointToPath(Point point);
        View getView();

        void notifyModel();

        void pauseGame();

        void selectedPlaneNull();

        void restartGame();

        void setPlaneSpeed(int speed);
    }

    public interface Model {
        void setPresenter(Presenter presenter);
        void setPlanes(List<Plane> planes);

        void addPointToPath(Point point);

        void startGame();

        void pauseGame();

        void isSelectedPlane(Point point);

        void viewIsReady();

        void selectedPlaneNull();

        void setLandedPlanes(int landedPlanes);

        void gameOver();

        void setSpeed(int speed);
    }
}
