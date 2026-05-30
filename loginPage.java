import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn, exitBtn;
    JLabel userLabel, passLabel;

    int attempts = 3;

    bankAccount account = new bankAccount(); // shared account

    public loginPage() {
        setTitle("Bank of Coffee Coders");
        setSize(400, 250);
        setLayout(null);

        // 🔹 LEFT LOGO PANEL
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(0, 0, 150, 250);
        logoPanel.setBackground(new Color(200, 220, 255));
        add(logoPanel);

        // 🔹 LOGO IMAGE
        ImageIcon logo = new ImageIcon("logo.png");
        Image img = logo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        logo = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logo);
        logoPanel.add(logoLabel);

        // 🔹 LOGIN FORM (RIGHT SIDE)

        userLabel = new JLabel("Username:");
        userLabel.setBounds(170, 50, 100, 25);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(250, 50, 120, 25);
        add(userField);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(170, 90, 100, 25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(250, 90, 120, 25);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(170, 140, 90, 30);
        add(loginBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(270, 140, 90, 30);
        add(exitBtn);

        loginBtn.addActionListener(this);
        exitBtn.addActionListener(e -> System.exit(0));

        // 🔹 FOOTER (Your Name)
        JLabel footer = new JLabel("Made by Abinash");
        footer.setBounds(200, 200, 180, 17);
        footer.setFont(new Font("Arial", Font.ITALIC, 10));
        footer.setForeground(Color.GRAY);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        add(footer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (account.checkLogin(user, pass)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new Dashboard(user, account);
            dispose();
        } else {
            attempts--;

            userField.setText("");
            passField.setText("");

            if (attempts > 0) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Credentials! Attempts left: " + attempts);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Too many failed attempts! Application will exit.");
                System.exit(0);
            }
        }
    }
}