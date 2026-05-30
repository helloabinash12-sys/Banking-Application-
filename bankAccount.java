public class bankAccount {
    private int balance = 1000;

    private String username = "bcc2026";
    private String password = "pass2026";

    // Balance methods
    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (balance - amount < 1000) return false;
        balance -= amount;
        return true;
    }

    // Login methods
    public boolean checkLogin(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    // Change credentials
    public void setCredentials(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }



}