package view;

import javax.swing.*;
import java.awt.*;
import model.About;

/**
 * A screen displaying information about the application.
 *
 * @version 0.1
 */
public class AboutScreen {
    /** Information about the application. */
    private final About myAbout;

    /**
     * Creates the about page.
     *
     * @param theDevTeam the team name of the developers.
     * @param theDevs the developers' names.
     */
    public AboutScreen(String theDevTeam, String[] theDevs) {
        myAbout = new About(theDevTeam, theDevs);
    }

    /**
     * Opens the about page.
     */
    public void open() {
        JFrame frame = new JFrame("About");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel versionLabel = new JLabel("Version: " + About.getVersion());
        panel.add(versionLabel, BorderLayout.NORTH);

        JLabel devTeamLabel = new JLabel("This app provided by: " + myAbout.getDevTeam());
        panel.add(devTeamLabel, BorderLayout.CENTER);

        String teamDescription = "Our team of experienced developers and designers aims to create a user-friendly project management tool " +
                "that allows DIY enthusiasts, homeowners, family members, and landscapers to efficiently plan, organize, and " +
                "execute projects within their budget.";
        JTextArea descriptionTextArea = new JTextArea(teamDescription);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setBackground(panel.getBackground());
        panel.add(descriptionTextArea, BorderLayout.SOUTH);

        // Create a panel to hold the authors' names
        JPanel authorsPanel = new JPanel(new GridBagLayout());
        authorsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a label for the authors section with a larger font and bold style
        JLabel authorsLabel = new JLabel("Authors:");
        authorsLabel.setFont(authorsLabel.getFont().deriveFont(Font.BOLD, 16));
        authorsPanel.add(authorsLabel, getGridBagConstraints(0, 0, 1, 1, GridBagConstraints.WEST));

        // Iterate over the developers' names and add them as labels to the authors panel
        for (int i = 0; i < myAbout.getDevs().length; i++) {
            String dev = myAbout.getDevs()[i];
            JLabel devLabel = new JLabel(dev);
            devLabel.setFont(devLabel.getFont().deriveFont(Font.PLAIN, 14));
            authorsPanel.add(devLabel, getGridBagConstraints(0, i + 1, 1, 1, GridBagConstraints.WEST));
}

        // Add the authors panel to the main panel on the EAST side
        panel.add(authorsPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = gridx;
        g.gridy = gridy;
        g.gridwidth = gridwidth;
        g.gridheight = gridheight;
        g.anchor = anchor;
        g.insets = new Insets(5, 5, 5, 5);
        return g;
    }
}