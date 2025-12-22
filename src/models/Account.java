package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Account {
    private final String accountNumber;
    private String pin; // can be updated!
    private BigDecimal balance;

    public Account(String accountNumber, String pin, String initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = new BigDecimal(initialBalance).setScale(2, RoundingMode.HALF_UP);

    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getPin(){
        return pin;
    }
    public String getBalance(){
        return balance.toString();
    }
    public void getCurrentBalance(){
        System.out.println("your current balance is: "+balance);
    }

    public boolean isAuthenticated(String pin) {
        return this.pin.equals(pin);
    }
    public void deposit(Scanner sc) {
        System.out.print("Enter amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine());
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance = balance.add(amount);
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(Scanner sc) {
        System.out.print("Enter amount to withdraw: ");
        BigDecimal amount = new BigDecimal(sc.nextLine());
        // Step 1: invalid amount (0 or negative)
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        // Step 2: sufficient balance check
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            System.out.println("Successfully withdraw!");
            return;
        }

        // Step 3: insufficient balance
        System.out.println("insufficient balance");
    }

}
