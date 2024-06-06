package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying information about the application.
 *
 * @author Bernard Bega, Barno Tashpulatova, Ahmed Hassan, Mahri Yalkapova
 */
public class AboutPanel extends JPanel {
    private JLabel versionLabel;
    private JLabel teamLabel;
    private JTextArea devsTextArea;

    /**
     * Constructs an AboutPanel and sets up its components.
     */
    public AboutPanel() {
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel appNameLabel = new JLabel("Project Peak");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        contentPanel.add(appNameLabel, constraints);

        constraints.gridy = 1;
        versionLabel = new JLabel();
        versionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        contentPanel.add(versionLabel, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        JTextArea descriptionArea = new JTextArea(
                "Project Peak is a project management tool designed for DIY enthusiasts, " +
                "homeowners, family members, and landscapers. It allows you to efficiently " +
                "plan, organize, and execute projects within your budget."
        );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 13));
        contentPanel.add(descriptionArea, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        teamLabel = new JLabel();
        teamLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        contentPanel.add(teamLabel, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        devsTextArea = new JTextArea();
        devsTextArea.setLineWrap(true);
        devsTextArea.setWrapStyleWord(true);
        devsTextArea.setEditable(false);
        devsTextArea.setBackground(getBackground());
        devsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(devsTextArea, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        JLabel copyrightLabel = new JLabel("© 2024 Javavengers. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPanel.add(copyrightLabel, constraints);

        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the version text in the AboutPanel.
     *
     * @param version the version text to display
     */
    public void setVersion(String version) {
        versionLabel.setText(version);
    }

    /**
     * Sets the development team text in the AboutPanel.
     *
     * @param devTeam the development team text to display
     */
    public void setDevTeam(String devTeam) {
        teamLabel.setText(devTeam);
    }

    /**
     * Sets the developers' names in the AboutPanel.
     *
     * @param devs an array of developer names to display
     */
    public void setDevs(String[] devs) {
        devsTextArea.setText(String.join("\n", devs));
    }
}