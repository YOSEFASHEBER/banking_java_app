import models.Account;

import java.util.*;

import static service.AccountService.createAccount;
import static service.AccountService.getAccount;

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
                    createAccount(scanner);
                    break;
                }
                case 2: {
                    getAccount  (scanner, accounts);
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


}
