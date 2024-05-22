package controller;

import model.About;
import view.AboutPanel;

public class AboutController {
    private About model;
    private AboutPanel view;

    public AboutController(About model, AboutPanel view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.setVersion("Verison: " + model.getVersion());
        view.setDevTeam("Developed by: " + model.getDevTeam());
        view.setDevs(model.getDevs());
    }
}