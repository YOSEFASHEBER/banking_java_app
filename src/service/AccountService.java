package service;

import models.Account;

import java.io.*;
import java.util.*;

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
        BufferedReader reader = null;
        try (BufferedReader reader1 = new BufferedReader(new FileReader("accounts.txt"))) {
            // check if the account exists
            boolean isAccountExist = false;


            String line;
            while ((line = reader1.readLine()) != null) {
                String[] breakdown = line.split(",");
                if (breakdown[0].equals(accountNumber)) {
                    System.out.println("Account already exist!");
                    isAccountExist = true;
                    break;
                }
            }
            if (!isAccountExist) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
                writer.write(accountNumber + "," + pin + "," + initialBalance + "\n");
                writer.close();
                System.out.println("you have successfully created an account");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void getAccount(Scanner scanner) {
        BufferedWriter writer = null ;
        List<String> accounts = new ArrayList<>();
        System.out.print("Enter Account Number: ");
        String inputAccountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String inputPin = scanner.nextLine();
        String currentAccount = null;

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                // split CSV line
                String[] data = line.split(",");

                String accountNumber = data[0];
                String pin = data[1];
                String balance = data[2];

                // check credentials
                if (accountNumber.equals(inputAccountNumber) && pin.equals(inputPin)) {
                    System.out.println("Login successful!");
                    currentAccount = line;
                    found = true;

                    // Menu
                    boolean isTrue = true;
                    Account myAccount = new Account(accountNumber, pin, balance);
                    while (isTrue) {
                        System.out.println("\n1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Get Balance");
                        System.out.println("4. Exit");

                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        switch (choice) {
                            case 1 -> myAccount.deposit(scanner);
                            case 2 -> myAccount.withdraw(scanner);
                            case 3 -> myAccount.getCurrentBalance();
                            case 4 -> isTrue = false;
                            default -> System.out.println("Invalid input!");
                        }
                    }
                    String updatedAccount = myAccount.getAccountNumber() + "," + myAccount.getPin() + "," + myAccount.getBalance();
                    accounts.add(updatedAccount);

                }else{
                    accounts.add(line);
                }
            }
            writer = new BufferedWriter(new FileWriter("accounts.txt")) ;
            for(String account : accounts){
                writer.write(account + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (!found) {
            System.out.println("Invalid account number or PIN!");
        }

    }
}