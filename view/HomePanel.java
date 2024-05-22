package view;

import javax.swing.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to Project Peak!"));
        add(new JLabel("This is the home panel."));
    }
}
