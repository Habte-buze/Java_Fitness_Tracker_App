package com.fitness.signuplogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpLogInPage extends JFrame {

	private JPanel mainPanel, loginPanel, signupPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;
    private JComboBox<String> roleSelector;
    
    public SignUpLogInPage() {
        setTitle("Fitness Tracker - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize main panel
        mainPanel = new JPanel(new CardLayout());
        
        // Setup Login Panel
        setupLoginPanel();
        
        // Setup Signup Panel (we'll come back to this)
        setupSignupPanel();
        
        // Add panels to main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "Signup");
        
        add(mainPanel);
        setLocationRelativeTo(null); // Center the window
    }
    
    private void setupLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        usernameField = new JTextField(15);
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        
        // Role selector for User or Admin
        roleSelector = new JComboBox<>(new String[]{"User", "Admin"});
        roleSelector.setMaximumSize(new Dimension(80, 20));
        
        // Buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Create Account");
        
        // Add components to panel
        loginPanel.add(titleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(new JLabel("Role:"));
        loginPanel.add(roleSelector);
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
    
        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        
        loginPanel.add(buttonPanel);
        
        // Add ActionListener to toggle signup button visibility based on role
        roleSelector.addActionListener(e -> {
        	String selectedRole = (String) roleSelector.getSelectedItem();
        	if("Admin".equals(selectedRole)) {
        		signupButton.setVisible(false);
        	} else {
        		signupButton.setVisible(true);
        	}
        });
        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login logic based on role (User/Admin)
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleSelector.getSelectedItem().toString();
                
                if (role.equals("Admin")) {
                    handleAdminLogin(username, password);
                } else {
                    handleUserLogin(username, password);
                }
            }
        });
        
        // Switch to signup panel
        signupButton.addActionListener(e -> switchToSignupPanel());
        
    }
    
   
    
    private void setupSignupPanel() {
    	    signupPanel = new JPanel();
    	    signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));

    	    JLabel titleLabel = new JLabel("Create Account");
    	    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    	    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    	    // Input Fields
    	    JTextField nameField = new JTextField(15);
    	    JTextField emailField = new JTextField(15);
    	    JTextField dobField = new JTextField(15);  // Ideally, use a date picker library for better UX
    	    JTextField phoneField = new JTextField(15);
    	    JTextField addressField = new JTextField(15);
    	    JPasswordField passwordField = new JPasswordField(15);
    	    JPasswordField confirmPasswordField = new JPasswordField(15);

    	    // Layout for each field
    	    signupPanel.add(titleLabel);
    	    signupPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    	    signupPanel.add(new JLabel("Name:"));
    	    signupPanel.add(nameField);
    	    signupPanel.add(new JLabel("Email:"));
    	    signupPanel.add(emailField);
    	    signupPanel.add(new JLabel("Date of Birth (DD/MM/YYYY):"));
    	    signupPanel.add(dobField);
    	    signupPanel.add(new JLabel("Phone Number:"));
    	    signupPanel.add(phoneField);
    	    signupPanel.add(new JLabel("Address:"));
    	    signupPanel.add(addressField);
    	    signupPanel.add(new JLabel("Password:"));
    	    signupPanel.add(passwordField);
    	    signupPanel.add(new JLabel("Confirm Password:"));
    	    signupPanel.add(confirmPasswordField);

    	    // Buttons
    	    JButton registerButton = new JButton("Register");
    	    JButton backButton = new JButton("Back to Login");

    	    // Panel for buttons
    	    JPanel buttonPanel = new JPanel();
    	    buttonPanel.add(registerButton);
    	    buttonPanel.add(backButton);

    	    signupPanel.add(buttonPanel);

    	    // Action Listener for Register Button
    	    registerButton.addActionListener(e -> {
    	        // Retrieve input values
    	        String name = nameField.getText();
    	        String email = emailField.getText();
    	        String dob = dobField.getText();
    	        String phone = phoneField.getText();
    	        String address = addressField.getText();
    	        String password = new String(passwordField.getPassword());
    	        String confirmPassword = new String(confirmPasswordField.getPassword());

    	        if (!password.equals(confirmPassword)) {
    	            JOptionPane.showMessageDialog(this, "Passwords do not match!");
    	            return;
    	        }

    	        // Generate a unique ID (for simplicity, using a random number)
    	        String userId = "USER" + System.currentTimeMillis();

    	        // Display or store user information (typically you’d save this to a database or file)
    	        JOptionPane.showMessageDialog(this, "Account created! Your User ID: " + userId);

    	        // Clear fields or navigate to login page
    	        nameField.setText("");
    	        emailField.setText("");
    	        dobField.setText("");
    	        phoneField.setText("");
    	        addressField.setText("");
    	        passwordField.setText("");
    	        confirmPasswordField.setText("");
    	        
    	        //switchToLoginPanel();  // Optional: navigate back to login page after successful registration
    	    });

    	    // Action Listener for Back Button
    	    //backButton.addActionListener(e -> switchToLoginPanel());
        
    }
    
    private void handleAdminLogin(String username, String password) {
        if ("admin".equals(username) && "admin2023".equals(password)) {
            JOptionPane.showMessageDialog(this, "Admin login successful!");
            // Redirect to Admin dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Admin credentials!");
        }
    }
    
    private void handleUserLogin(String username, String password) {
        // Logic to authenticate user (e.g., by checking a user database/file)
        JOptionPane.showMessageDialog(this, "User login successful!");
        // Redirect to User dashboard
    }
    
    private void switchToSignupPanel() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "Signup");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignUpLogInPage app = new SignUpLogInPage();
            app.setVisible(true);
        });
    }

}
