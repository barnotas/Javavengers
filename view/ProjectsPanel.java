package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents JPanel window for displaying components.
 * This class provides window container and action 
 * listener to enable GUI components.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */

public class ProjectsPanel extends JPanel implements ActionListener {
    /**
     * A field for project name.
     */
    private JLabel projectNameLabel ;
    /**
     * A field for project description label.
     */
    private JLabel projectDescriptionLabel;

    /**
     * Constructs JPanel with GUI components.
     */
    public ProjectsPanel() {
        super();
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

    /**
     * Sets a project name.
     * @param name
     */
    public void setProjectName(String name) {
        projectNameLabel.setText("Project Name: " + name);
    }

    /**
     * Sets project description.
     * @param description
     */
    public void setProjectDescription(String description) {
        projectDescriptionLabel.setText("Project Description: " + description);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
     }
    }