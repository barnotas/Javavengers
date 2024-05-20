package view;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    private JLabel versionLabel;
    private JLabel teamLabel;
    private JTextArea devsTextArea;


    public AboutPanel() {
        setLayout(new BorderLayout());
        // Create the main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add the application name and version
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

        // Add the application description
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2; // Span across 2 columns
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        constraints.weightx = 1.0; // Give the text area more horizontal weight

        JTextArea descriptionArea = new JTextArea(
            "Project Peak is a project management tool designed for DIY enthusiasts, " +
            "homeowners, family members, and landscapers. It allows you to efficiently " +
            "plan, organize, and execute projects within your budget." );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 13));
        contentPanel.add(descriptionArea, constraints);

        // Add the development team information
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        teamLabel = new JLabel();
        teamLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        contentPanel.add(teamLabel, constraints);

        // Add the developers' names
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

        // Add the copyright information
        constraints.gridy = 5;
        constraints.gridx = 0;
        JLabel copyrightLabel = new JLabel("© 2024 Javavengers. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPanel.add(copyrightLabel, constraints);

        // Add the main content panel to the center of the AboutPanel
        add(contentPanel, BorderLayout.CENTER);
    }

    public void setVersion(String version) {
        versionLabel.setText(version);
    }

    public void setDevTeam(String devTeam) {
        teamLabel.setText(devTeam);
    }

    public void setDevs(String[] devs) {
        devsTextArea.setText(String.join("\n", devs));
    }

}