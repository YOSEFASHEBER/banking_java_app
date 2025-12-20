package service;

import models.Account;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class AccountService {
    public static void createAccount(Scanner scanner) {
        String accountNumber;
        String initialBalance;
        String pin;
        System.out.print("Enter account number: ");
        accountNumber = scanner.nextLine();
        System.out.print("Enter pin number: ");
        pin = scanner.nextLine();

        System.out.print("Enter initial deposit: ");
        initialBalance = scanner.nextLine();
        System.out.println();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
            writer.write(accountNumber + "," + pin + "," + initialBalance + "\n");
            writer.close();
            System.out.println("you have successfully created an account");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void getAccount(Scanner scanner, Map<String, Account> accounts) {

        System.out.print("Enter Account Number: ");
        String inputAccountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String inputPin = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                // split CSV line
                String[] data = line.split(",");

                String accountNumber = data[0];
                String pin = data[1];
                double balance = Double.parseDouble(data[2]);

                // check credentials
                if (accountNumber.equals(inputAccountNumber) && pin.equals(inputPin)) {
                    System.out.println("Login successful!");
                    found = true;

                    // Menu
                    boolean isTrue = true;
                    while (isTrue) {
                        System.out.println("\n1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Get Balance");
                        System.out.println("4. Exit");

                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

//                        switch (choice) {
//                            case 1 -> myAccount.deposit(scanner);
//                            case 2 -> myAccount.withdraw(scanner);
//                            case 3 -> myAccount.getBalance();
//                            case 4 -> isTrue = false;
//                            default -> System.out.println("Invalid input!");
//                        }
                   }
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }

        if (!found) {
            System.out.println("Invalid account number or PIN!");
        }
    }
}
