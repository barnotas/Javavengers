package controller;

import model.NewProjectModel;
import view.NewProjectPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectController {
    private NewProjectModel newProjectModel;
    private NewProjectPanel newProjectView;

    public NewProjectController(NewProjectModel newProjectModel, NewProjectPanel newProjectView) {
        this.newProjectModel = newProjectModel;
        this.newProjectView = newProjectView;

        newProjectView.addCreateProjectListener(new NewProjectListener());
    }

    public class NewProjectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            NewProjectCheck();
        }
    }

    public void NewProjectCheck() {
        String projectName = newProjectView.getProjectName();
        String projectDescription =  newProjectView.getProjectDescription();

        newProjectModel.setName(projectName);
        newProjectModel.setDescription(projectDescription);
    }
}
