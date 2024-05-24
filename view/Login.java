// package view;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// import model.User;

// /**
//  * A login page where a name and email get inputted.
//  *
//  * @version 0.1
//  */
// public class Login extends JDialog implements ActionListener{
//     private JTextField nameField;
//     private JTextField emailField;
//     private JButton loginButton;
//     /**
//      * Creates the login page.
//      */
//     public Login(JFrame parent) {
//         super(parent, "Login", true);
//         setLayout(new GridLayout(3, 2));
//         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

//         nameField = new JTextField(20);
//         emailField = new JTextField(20);
//         loginButton = new JButton("Login");

//         add(new JLabel("Name:"));
//         add(nameField);
//         add(new JLabel("Email:"));
//         add(emailField);
//         add(new JPanel());
//         add(loginButton);

//         loginButton.addActionListener(this);
//         pack();
//         setLocationRelativeTo(parent);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == loginButton) {
//             User user = new User(nameField.getText(), emailField.getText());
//             user.export();  // Assuming User class has this method to serialize data
//             //JOptionPane.showMessageDialog(this, "Login successful for: " + user.getName());
//             dispose();  // Close the dialog
//         }
//     }


//     /**
//      * Opens the login page and collects the inputted information to make a
//      * User.
//      *
//      * @return A User made with the inputted information.
//      */
//     public User open() {

//         return null;
//     }
// }
