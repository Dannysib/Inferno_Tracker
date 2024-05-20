import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserNameWindow {
    private JFrame frame;
    private JTextField usernameField;

    public void createAndShowGUI() {
        frame = new JFrame("Inferno Run Tracker - Username");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Enter your username:");
        userLabel.setBounds(10, 20, 160, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(180, 20, 165, 25);
        panel.add(usernameField);

        JButton loginButton = new JButton("Submit");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    frame.dispose();
                    new TrackerWindow(username).createAndShowGUI();
                } else {
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
