package HOSPITAL_MANAGEMENT_SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    // GUI Components
    private JComboBox<String> comboBox; // For ID type selection
    private JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite; // Patient details
    private JRadioButton r1, r2; // Gender selection
    private Choice c1; // Room selection
    private JLabel date; // Date label
    private JButton b1, b2; // Buttons for actions

    public NEW_PATIENT() {
        // Set up the JFrame
        setTitle("New Patient Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(850, 550);
        setLocation(300, 250);
        setLayout(null);

        // Create main panel
        JPanel panel = createMainPanel();
        add(panel);

        // Initialize UI components
        initializeComponents(panel);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        return panel;
    }

    private void initializeComponents(JPanel panel) {
        // Image for the patient icon
        addPatientIcon(panel);

        // Labels and Fields
        addLabelsAndFields(panel);

        // Room selection
        populateRoomChoice();

        // Date display
        addCurrentDate(panel);

        // Action buttons
        addButtons(panel);
    }

    private void addPatientIcon(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(550, 150, 200, 200);
        panel.add(label);
    }

    private void addLabelsAndFields(JPanel panel) {
        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelName);

        // ID type selection
        addLabel(panel, "ID :", 35, 76);
        comboBox = createComboBox(panel, new String[]{"Aadhar Card", "Voter Id", "Driving License"}, 271, 73);

        // Patient ID number input
        addLabel(panel, "Number :", 35, 111);
        textFieldNumber = createTextField(panel, 271, 111);

        // Patient name input
        addLabel(panel, "Name :", 35, 151);
        textName = createTextField(panel, 271, 151);

        // Gender selection
        addLabel(panel, "Gender :", 35, 191);
        addGenderRadioButtons(panel);

        // Patient Disease input
        addLabel(panel, "Disease :", 35, 231);
        textFieldDisease = createTextField(panel, 271, 231);

        // Room selection
        addLabel(panel, "Room :", 35, 274);
        c1 = new Choice();
        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(3, 45, 48));
        panel.add(c1);
    }

    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 14);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setForeground(Color.white);
        panel.add(label);
    }

    private JTextField createTextField(JPanel panel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 20);
        panel.add(textField);
        return textField;
    }

    private JComboBox<String> createComboBox(JPanel panel, String[] items, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, 150, 20);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(comboBox);
        return comboBox;
    }

    private void addGenderRadioButtons(JPanel panel) {
        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.white);
        r1.setBackground(new Color(109, 164, 170));
        r1.setBounds(271, 191, 80, 15);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.white);
        r2.setBackground(new Color(109, 164, 170));
        r2.setBounds(350, 191, 80, 15);
        panel.add(r2);

        // Group the radio buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
    }

    private void populateRoomChoice() {
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM Room");
            while (resultSet.next()) {
                c1.add(resultSet.getString("room_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCurrentDate(JPanel panel) {
        JLabel labelDate = new JLabel("Time :");
        labelDate.setBounds(35, 316, 200, 14);
        labelDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDate.setForeground(Color.white);
        panel.add(labelDate);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(271, 316, 250, 14);
        date.setForeground(Color.white);
        date.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(date);

        // Deposit Field
        addLabel(panel, "Deposite :", 35, 359);
        textFieldDeposite = createTextField(panel, 271, 359);
    }

    private void addButtons(JPanel panel) {
        b1 = new JButton("ADD");
        b1.setBounds(100, 430, 120, 30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.black);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(260, 430, 120, 30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.black);
        b2.addActionListener(this);
        panel.add(b2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            addNewPatient();
        } else {
            setVisible(false);
        }
    }

    private void addNewPatient() {
        String idType = (String) comboBox.getSelectedItem(); // ID type
        String idNumber = textFieldNumber.getText(); // ID number
        String patientName = textName.getText(); // Patient name
        String gender = r1.isSelected() ? "Male" : "Female"; // Gender
        String disease = textFieldDisease.getText(); // Patient Disease
        String roomNumber = c1.getSelectedItem(); // Room number
        String time = date.getText(); // Current date/time
        String deposit = textFieldDeposite.getText(); // Deposit amount

        try {
            conn c = new conn();
            // Insert into patient_info1 with correct column mapping
            String query = "INSERT INTO patient_info1 (ID, Name, Number, Gender, Patient_Disease, Room_Number, Time, Deposite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.connection.prepareStatement(query);
            preparedStatement.setString(1, idType); // ID type
            preparedStatement.setString(2, patientName); // Patient name
            preparedStatement.setString(3, idNumber); // ID number
            preparedStatement.setString(4, gender); // Gender
            preparedStatement.setString(5, disease); // Patient Disease
            preparedStatement.setString(6, roomNumber); // Room Number
            preparedStatement.setString(7, time); // Time
            preparedStatement.setString(8, deposit); // Deposit

            preparedStatement.executeUpdate(); // Execute the insert

            // Update room availability in the Room table
            String updateQuery = "UPDATE room SET Availability = 'Occupied' WHERE room_no = ?";
            PreparedStatement updateStatement = c.connection.prepareStatement(updateQuery);
            updateStatement.setString(1, roomNumber); // Room number to update
            updateStatement.executeUpdate(); // Execute the update

            JOptionPane.showMessageDialog(null, "Added Successfully");
            setVisible(false); // Close the window after success
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); // Show error message
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}