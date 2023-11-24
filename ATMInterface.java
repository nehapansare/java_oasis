import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private JFrame frame;
    private JTextField accountField;
    private JTextField withdrawField;
    private JTextField transferField;
    private JTextArea transactionHistoryArea;

    private double balance = 10000;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ATMInterface();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ATMInterface() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblAccount = new JLabel("Account Number:");
        lblAccount.setBounds(10, 11, 100, 14);
        frame.getContentPane().add(lblAccount);

        accountField = new JTextField();
        accountField.setBounds(120, 8, 86, 20);
        frame.getContentPane().add(accountField);
        accountField.setColumns(10);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        checkBalanceButton.setBounds(10, 42, 150, 23);
        frame.getContentPane().add(checkBalanceButton);

        JLabel lblWithdraw = new JLabel("Withdraw Amount:");
        lblWithdraw.setBounds(10, 76, 120, 14);
        frame.getContentPane().add(lblWithdraw);

        withdrawField = new JTextField();
        withdrawField.setBounds(140, 73, 86, 20);
        frame.getContentPane().add(withdrawField);
        withdrawField.setColumns(10);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        withdrawButton.setBounds(10, 107, 120, 23);
        frame.getContentPane().add(withdrawButton);

        JLabel lblTransfer = new JLabel("Transfer Amount:");
        lblTransfer.setBounds(10, 141, 120, 14);
        frame.getContentPane().add(lblTransfer);

        transferField = new JTextField();
        transferField.setBounds(140, 138, 86, 20);
        frame.getContentPane().add(transferField);
        transferField.setColumns(10);

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });
        transferButton.setBounds(10, 172, 120, 23);
        frame.getContentPane().add(transferButton);

        JLabel lblTransactionHistory = new JLabel("Transaction History:");
        lblTransactionHistory.setBounds(10, 206, 150, 14);
        frame.getContentPane().add(lblTransactionHistory);

        transactionHistoryArea = new JTextArea();
        transactionHistoryArea.setBounds(10, 231, 414, 20);
        frame.getContentPane().add(transactionHistoryArea);

        frame.setVisible(true);
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(frame, "Your Balance: $" + balance);
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(withdrawField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                updateTransactionHistory("Withdraw: -$" + amount);
                JOptionPane.showMessageDialog(frame, "Withdrawal successful. New Balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid withdrawal amount");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
        }
    }

    private void transfer() {
        try {
            double amount = Double.parseDouble(transferField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                updateTransactionHistory("Transfer: -$" + amount);
                JOptionPane.showMessageDialog(frame, "Transfer successful. New Balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid transfer amount");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
        }
    }

    private void updateTransactionHistory(String transaction) {
        transactionHistoryArea.append(transaction + "\n");
    }
}