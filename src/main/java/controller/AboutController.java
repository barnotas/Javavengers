package controller;

import model.About;
import view.AboutPanel;

/**
 * Controller for the About section of the application.
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class AboutController {
    private About model;
    private AboutPanel view;

    /**
     * Constructs an AboutController with the specified model and view.
     *
     * @param model the About model
     * @param view the AboutPanel view
     */
    public AboutController(About model, AboutPanel view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Updates the view with the data from the model.
     */
    public void updateView() {
        view.setVersion("Version: " + model.getVersion());
        view.setDevTeam("Developed by: " + model.getDevTeam());
        view.setDevs(model.getDevs());
    }
    public void exportAbout(String filePath) {
        model.exportAbout(filePath);
    }
}