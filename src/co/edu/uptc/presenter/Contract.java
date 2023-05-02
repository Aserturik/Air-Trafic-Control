package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.util.List;

public interface Contract {
    public interface View {
        void start();

        void setPresenter(Presenter presenter);
        void startRecorride();
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);
        void startRecorride();

        List<Plane> getPlanes();
    }

    public interface Model {
        void setPresenter(Presenter presenter);
        List<Plane> getPlanes();
    }
}
