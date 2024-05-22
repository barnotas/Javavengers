package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewProjectPanel extends JDialog {
    private JLabel idLabel;
    private JTextField idField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel descriptionLabel;
    private JTextArea descriptionArea;
    private JLabel budgetLabel;
    private JTextField budgetField;
    private JButton createButton;

    public NewProjectPanel(Frame parent) {
        super(parent, "Create New Project", true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        idLabel = new JLabel("Project ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(idLabel, constraints);

        idField = new JTextField(10);
        constraints.gridx = 1;
        add(idField, constraints);

        nameLabel = new JLabel("Project Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        add(nameField, constraints);

        descriptionLabel = new JLabel("Description:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(descriptionLabel, constraints);

        descriptionArea = new JTextArea(5, 20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(descriptionArea), constraints);

        budgetLabel = new JLabel("Budget:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(budgetLabel, constraints);

        budgetField = new JTextField(10);
        constraints.gridx = 1;
        add(budgetField, constraints);

        createButton = new JButton("Create Project");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        add(createButton, constraints);

        pack();
        setLocationRelativeTo(parent);
    }

    public int getProjectId() {
        return Integer.parseInt(idField.getText());
    }

    public String getProjectName() {
        return nameField.getText();
    }

    public String getProjectDescription() {
        return descriptionArea.getText();
    }

    public double getProjectBudget() {
        return Double.parseDouble(budgetField.getText());
    }

    public void addCreateProjectListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }
}
