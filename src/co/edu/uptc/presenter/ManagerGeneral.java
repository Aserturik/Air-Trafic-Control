package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModel;
import co.edu.uptc.view.MyFrame;

public class ManagerGeneral {
    private ManagerGeneral() {
    }

    private static ManagerGeneral instance;
    private Contract.View view;
    private Contract.Model model;
    private Contract.Presenter presenter;

    public static ManagerGeneral getInstance() {
        return instance == null ? instance = new ManagerGeneral() : instance;
    }

    private void CreateMVP() {
        model = new ManagerModel();
        presenter = new Presenter(this);
        view = new MyFrame();
        view.setPresenter(presenter);
        model.setPresenter(presenter);
        presenter.setView(view);
        presenter.setModel(model);
    }

    public void runProject() {
        CreateMVP();
        new Thread(() -> {
            view.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //view.chargeBackground();
        }).start();
        presenter.startGame();
    }

    public void restartGame() {
        presenter = null;
        view = null;
        model = null;
        runProject();
    }
}
