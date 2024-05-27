package view;

import javax.swing.*;

public class HomePanel extends JPanel {
    private JList<String> projectList;
    private DefaultListModel<String> projectListModel;

    public HomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to Project Peak!"));
        add(new JLabel("This is the home panel."));

        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        add(new JScrollPane(projectList));
    }

    public void addProject(String projectName, String projectDescription) {
        String listEntry = projectName + " - Description: " + projectDescription;
        projectListModel.addElement(listEntry);
      //  revalidate();
     //   repaint();
    }
}