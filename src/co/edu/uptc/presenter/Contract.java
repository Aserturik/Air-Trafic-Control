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

        int isSelectedPlane(Point point);
        void addPointToPath(int id, Point point);
        View getView();

        void notifyModel();

        void pauseGame();

        void selectedPlaneNull(int id);

        void restartGame();

        void setPlaneSpeed(int speed);
    }

    public interface Model {
        void setPresenter(Presenter presenter);
        void setPlanes(List<Plane> planes);

        void addPointToPath(int id, Point point);

        void startGame();

        void pauseGame();

        int isSelectedPlane(Point point);

        void viewIsReady();

        void selectedPlaneNull(int id);

        void setLandedPlanes(int landedPlanes);

        void gameOver();

        void setSpeed(int speed);
    }
}
