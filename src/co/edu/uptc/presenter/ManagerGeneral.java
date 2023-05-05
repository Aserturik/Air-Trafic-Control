package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModel;
import co.edu.uptc.view.MyFrame;

public class ManagerGeneral {
    private ManagerGeneral(){
    }
    private static ManagerGeneral instance;
    private Contract.View view;
    private Contract.Model model;
    private Contract.Presenter presenter;

    public static ManagerGeneral getInstance() {
        return instance == null ? instance = new ManagerGeneral() : instance;
    }

    private void CreateMVP(){
        model = new ManagerModel();
        presenter = new Presenter();
        view = new MyFrame();
        view.setPresenter(presenter);
        model.setPresenter(presenter);
        presenter.setModel(model);
        presenter.setView(view);
    }

    public void runProject(){
        CreateMVP();
        view.start();
        presenter.startGame();
    }
}
