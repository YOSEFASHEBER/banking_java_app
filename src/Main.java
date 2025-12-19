import models.Account;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        int choice;
        boolean isActive = true;

        System.out.println("Welcome to our bank");

        while (isActive) {
            System.out.println("1, To create account");
            System.out.println("2, To access acount");
            System.out.println("3 to exit");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    createAccount(scanner, accounts);
                    break;
                }
                case 2: {
                    getAccount(scanner, accounts);
                    break;
                }
                case 3: {
                    isActive = false;
                    break;
                }
                default: {
                    System.out.println("invalid input!");
                }


            }


        }

    }
    public static void createAccount (Scanner scanner, Map < String, Account > accounts){
        String accountNumber;
        String initialBalance;
        String pin;
        System.out.print("Enter account number: ");
        accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }
        System.out.print("Enter pin number: ");
        pin = scanner.nextLine();

        System.out.print("Enter initial deposit: ");
        initialBalance = scanner.nextLine();
        System.out.println();


        Account account = new Account(accountNumber, pin, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("you have successfully created an account");
    }
    public static void getAccount(Scanner scanner, Map<String, Account> accounts) {

        scanner.nextLine(); // clear buffer

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account myAccount = accounts.get(accountNumber);

        if (myAccount == null) {
            System.out.println("Account not found!");
            return;
        }

        if (!myAccount.isAuthenticated(pin)) {
            System.out.println("Invalid PIN!");
            return;
        }

        boolean isTrue = true;
        while (isTrue) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Get Balance");
            System.out.println("4. Exit");

            int choice1 = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice1) {
                case 1 -> myAccount.deposit(scanner);
                case 2 -> myAccount.withdraw(scanner);
                case 3 -> myAccount.getBalance();
                case 4 -> isTrue = false;
                default -> System.out.println("Invalid input!");
            }
        }
    }

}
