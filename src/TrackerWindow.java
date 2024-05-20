import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class TrackerWindow {
    private JFrame frame;
    private String username;
    private JTextField totalAttemptsField;
    private JTextField highestWaveField;
    private JLabel currentDayLabel;
    private int currentDay = 1;
    private int totalAttempts;
    private int highestWave;

    public TrackerWindow(String username) {
        this.username = username;
        this.totalAttempts = FileHandler.readTotalAttempts();
        this.highestWave = FileHandler.readHighestWave();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Inferno Run Tracker - " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        currentDayLabel = new JLabel("Current day of attempts: 1");
        currentDayLabel.setBounds(10, 20, 200, 25);
        panel.add(currentDayLabel);

        JButton incrementDayButton = new JButton("Next Day");
        incrementDayButton.setBounds(220, 20, 100, 25);
        panel.add(incrementDayButton);

        incrementDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDay++;
                currentDayLabel.setText("Current day of attempts: " + currentDay);
            }
        });

        JLabel waveLabel = new JLabel("Current wave:");
        waveLabel.setBounds(10, 60, 160, 25);
        panel.add(waveLabel);

        JTextField waveField = new JTextField(20);
        waveField.setBounds(180, 60, 165, 25);
        panel.add(waveField);

        JLabel notesLabel = new JLabel("Notes (optional):");
        notesLabel.setBounds(10, 100, 160, 25);
        panel.add(notesLabel);

        JTextField notesField = new JTextField(20);
        notesField.setBounds(180, 100, 165, 25);
        panel.add(notesField);

        JLabel attemptsLabel = new JLabel("Total attempts:");
        attemptsLabel.setBounds(10, 140, 160, 25);
        panel.add(attemptsLabel);

        totalAttemptsField = new JTextField(20);
        totalAttemptsField.setBounds(180, 140, 165, 25);
        totalAttemptsField.setEditable(false);
        totalAttemptsField.setText(String.valueOf(totalAttempts));
        panel.add(totalAttemptsField);

        JLabel highestWaveLabel = new JLabel("Highest wave all time:");
        highestWaveLabel.setBounds(10, 180, 160, 25);
        panel.add(highestWaveLabel);

        highestWaveField = new JTextField(20);
        highestWaveField.setBounds(180, 180, 165, 25);
        highestWaveField.setEditable(false);
        highestWaveField.setText(String.valueOf(highestWave));
        panel.add(highestWaveField);

        JButton submitButton = new JButton("Save");
        submitButton.setBounds(10, 220, 80, 25);
        panel.add(submitButton);

        JButton openFileButton = new JButton("Open Log");
        openFileButton.setBounds(100, 220, 120, 25);
        panel.add(openFileButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String waveStr = waveField.getText();
                String notes = notesField.getText();

                if (waveStr.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Wave field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int wave = Integer.parseInt(waveStr);
                        if (wave > highestWave) {
                            highestWave = wave;
                            highestWaveField.setText(String.valueOf(highestWave));
                        }
                        totalAttempts++;
                        totalAttemptsField.setText(String.valueOf(totalAttempts));
                        FileHandler.saveToFile(username, currentDay, wave, notes, totalAttempts, highestWave);
                        JOptionPane.showMessageDialog(panel, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(panel, "Wave must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("inferno_run_tracker.txt");
                    if (file.exists()) {
                        Desktop.getDesktop().open(file);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Log file not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
