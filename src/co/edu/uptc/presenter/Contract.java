package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;

public interface Contract {
    public interface View {
        void start();

        void setPresenter(Presenter presenter);

        void startGame();
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        List<Plane> getPlanes();
    }

    public interface Model {
        void setPresenter(Presenter presenter);

        List<Plane> getPlanes();
        void addPointToPath(Plane plane, Point point);

        void startGame();

        void RestartGame();
        void pauseGame();
        void resumeGame();

        void stopGame();

        String numberPlanesSetDown();

        String numberPlanesInAir();

        String timeGame();
    }
}
