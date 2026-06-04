package src;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    bankAccount account;
    JLabel balanceLabel, welcomeLabel;
    JTextField amountField;

    public Dashboard(String username, bankAccount account) {

        this.account = account;

        setTitle("Bank src.Dashboard");
        setSize(400, 350);
        setLayout(null);

        // 🔹 Logo
        ImageIcon logo = new ImageIcon("src/logo.png");
        Image img = logo.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH);
        logo = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(10, 0, 80, 50);
        add(logoLabel);

        // 🔹 Title
        JLabel title = new JLabel("Bank of Coffee Coders");
        title.setBounds(90, 20, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        // 🔹 Welcome Label
        welcomeLabel = new JLabel("Welcome, " + username + " to BCC");
        welcomeLabel.setBounds(90, 50, 250, 30);
        add(welcomeLabel);

        // 🔹 Balance Label (Hidden)
        balanceLabel = new JLabel("Balance: ******");
        balanceLabel.setBounds(120, 80, 200, 30);
        add(balanceLabel);

        // 🔹 Check Balance
        JButton checkBtn = new JButton("Check Balance");
        checkBtn.setBounds(120, 110, 150, 30);
        add(checkBtn);

        // 🔹 Amount Field
        amountField = new JTextField();
        amountField.setBounds(120, 150, 150, 30);
        add(amountField);

        // 🔹 Deposit
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(50, 190, 120, 30);
        add(depositBtn);

        // 🔹 Withdraw
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(200, 190, 120, 30);
        add(withdrawBtn);

        // 🔹 Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(50, 230, 120, 30);
        add(logoutBtn);

        // 🔹 Change Credentials
        JButton changeBtn = new JButton("Change Credentials");
        changeBtn.setBounds(200, 230, 170, 30);
        add(changeBtn);

        // 🔹 Footer (Your Name)
        JLabel footer = new JLabel("Made by Abinash");
        footer.setBounds(130, 280, 150, 20);
        footer.setFont(new Font("Arial", Font.ITALIC, 10));
        footer.setForeground(Color.GRAY);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        add(footer);

        // 🔹 Actions

        checkBtn.addActionListener(e -> {
            balanceLabel.setText("Balance: ₹" + account.getBalance());
        });

        depositBtn.addActionListener(e -> {
            try {
                int amt = Integer.parseInt(amountField.getText());

                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter positive amount!");
                    return;
                }

                account.deposit(amt);
                balanceLabel.setText("Balance: ******");

                amountField.setText("");
                JOptionPane.showMessageDialog(this, "Deposited ₹" + amt);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                int amt = Integer.parseInt(amountField.getText());

                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter positive amount!");
                    return;
                }

                if (account.withdraw(amt)) {
                    balanceLabel.setText("Balance: ******");

                    amountField.setText("");
                    JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amt);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Minimum balance ₹1000 required!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        });

        logoutBtn.addActionListener(e -> {
            new loginPage();
            dispose();
        });

        changeBtn.addActionListener(e -> {
            String newUser = JOptionPane.showInputDialog(this, "Enter new username:");
            String newPass = JOptionPane.showInputDialog(this, "Enter new password:");

            if (newUser != null && newPass != null &&
                    !newUser.isEmpty() && !newPass.isEmpty()) {

                account.setCredentials(newUser, newPass);
                welcomeLabel.setText("Welcome, " + account.getUsername() + " to BCC");

                JOptionPane.showMessageDialog(this, "Credentials Updated!");

            } else {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}