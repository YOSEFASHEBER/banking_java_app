import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
         String accountNumber;
         String initialBalance;
         String pin;
         int choice;
         boolean isActive = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our bank");
        System.out.print("Enter account number: ");
        accountNumber = scanner.nextLine();

        System.out.print("Enter pin number: ");
        pin = scanner.nextLine();

        System.out.print("Enter initial deposit: ");
        initialBalance = scanner.nextLine();
        System.out.println();

        Account a1 = new Account(accountNumber, pin, initialBalance);
        System.out.println("you have successfully created an account");
        System.out.print("Enter PIN to login: ");
        String inputPin = scanner.nextLine();

        if (!a1.isAuthenticated(inputPin)) {
            System.out.println("Invalid PIN. Access denied.");
            return;
        }
        while (isActive) {
            System.out.println("1, To get account number");
            System.out.println("2, To Deposit");
            System.out.println("3, to withdraw");
            System.out.println("4, to get balance");
            System.out.println("5 to exit");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    a1.getAccountNumber();
                    break;
                }
                case 2: {
                    System.out.print("Enter amount to deposit: ");
                    String depositAmount = scanner.nextLine();
                    a1.deposit(new BigDecimal(depositAmount));
                    break;
                }
                case 3: {
                    System.out.print("Enter amount to withdraw: ");
                    String withdrawAmount = scanner.nextLine();
                    a1.withdraw(new BigDecimal(withdrawAmount));
                    break;
                }
                case 4 : {
                    a1.getBalance();
                    break;
                }
                case 5 : {
                    isActive = false;
                    break;
                }
                default:{
                    System.out.println("invalid input!");
                }

            }
        }

    }
}
class Account{
    private final String accountNumber;
    private String pin; // can be updated!
    private BigDecimal balance;
    public Account (String accountNumber, String pin, String initialBalance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = new BigDecimal(initialBalance).setScale(2, RoundingMode.HALF_UP);

    }
    public void getAccountNumber(){
        System.out.println("Your account number is "+accountNumber);

    }
    public boolean isAuthenticated(String pin){
        return this.pin.equals(pin);
    }
    public void getBalance() {
        System.out.println("$ " + balance);
    }
    public void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance = balance.add(amount);
        System.out.println("Deposited: " + amount);
            }
    public boolean withdraw(BigDecimal amount) {

        // Step 1: invalid amount (0 or negative)
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount");
            return false;
        }

        // Step 2: sufficient balance check
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return true;
        }

        // Step 3: insufficient balance
        System.out.println("insufficient balance");
        return false;
    }

}
