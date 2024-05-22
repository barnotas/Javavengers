package controller;

import model.NewProjectModel;
import view.NewProjectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectController {
    private NewProjectModel newProjectModel;
    private JFrame parentFrame;

    public NewProjectController(NewProjectModel newProjectModel) {
        this.newProjectModel = newProjectModel;
    }

    public void showNewProjectPopup() {
        NewProjectPanel newProjectPanel = new NewProjectPanel(parentFrame);
        newProjectPanel.addCreateProjectListener(new NewProjectListener(newProjectPanel));
        newProjectPanel.setVisible(true);
    }

    public class NewProjectListener implements ActionListener {
        private NewProjectPanel newProjectPanel;

        public NewProjectListener(NewProjectPanel newProjectPanel) {
            this.newProjectPanel = newProjectPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NewProjectCheck(newProjectPanel);
            newProjectPanel.dispose();
        }
    }

    public void NewProjectCheck(NewProjectPanel newProjectPanel) {
        int projectId = newProjectPanel.getProjectId();
        String projectName = newProjectPanel.getProjectName();
        String projectDescription = newProjectPanel.getProjectDescription();
        double projectBudget = newProjectPanel.getProjectBudget();

        newProjectModel.setId(projectId);
        newProjectModel.setName(projectName);
        newProjectModel.setDescription(projectDescription);
        newProjectModel.setBudget(projectBudget);

    }
}
