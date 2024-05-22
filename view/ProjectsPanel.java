package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
// 

public class ProjectsPanel extends JPanel implements ActionListener {
    private JLabel projectNameLabel ;
    private JLabel projectDescriptionLabel;
    public ProjectsPanel() {
        //setLayout(new GridBagLayout());
        projectNameLabel = new JLabel();
        projectDescriptionLabel = new JLabel();
        add(projectNameLabel);
        add(projectDescriptionLabel);
        add(new JLabel("Projects"));
        add(new JLabel("This is where you can manage your projects."));
       // GridBagConstraints constraints = new GridBagConstraints();
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        add(new JButton("Add new project"));
        
    }


    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

     }
    }