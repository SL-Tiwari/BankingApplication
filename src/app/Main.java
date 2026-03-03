package app;

import service.BankService;
import service.impl.BankServiceImpl;
import util.Validation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BankService bankService = new BankServiceImpl();

        System.out.println("Welcome to Console Bank");
        boolean exitApp = true;

        while(exitApp) {
            System.out.println("""
                    1. Open Account
                    2. Deposit
                    3. Withdraw
                    4. Transfer
                    5. Account Statement
                    6. List Accounts
                    7, Search Account by Customer Name
                    0. Exit
                    """);
            System.out.println("Choose an option: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Selected option: " + choice);
            switch (choice) {
                case 1 -> openAccount(scanner,bankService);
                case 2 -> depositFunds(scanner,bankService);
                case 3 -> withDrawFunds(scanner,bankService);
                case 4 -> transferAmount(scanner,bankService);
                case 5 -> generateStatement(scanner,bankService);
                case 6 -> listAccountDetails(scanner,bankService);
                case 7 -> findCustomerByName(scanner,bankService);
                case 0 -> exitApp = false;
                default -> System.out.println("Invalid choice");
            }
        }
        System.out.println("Thank you for using Console Bank");
    }

    private static void openAccount(Scanner scanner, BankService bankService) {
       System.out.println("Enter Customer Name: ");
       String name = scanner.nextLine().trim();
       System.out.println("Enter Customer Email: ");
       String email = scanner.nextLine().trim();
       System.out.println("Account Type (SAVINGS / CURRENT): ");
       String accountType = scanner.nextLine().trim();
       System.out.println("Initial Deposit Amount: ");
       double amount = scanner.nextDouble();
       scanner.nextLine();
       String accountNumber = bankService.createAccount(name,email,accountType);
       if(amount > 0) {
           bankService.depositAmount(accountNumber,amount,"DEPOSIT");
       }
        System.out.println("Account Opened: " + accountNumber);
    }


    private static void depositFunds(Scanner scanner,BankService bankService) {
        System.out.println("Enter Account number: ");
        String accountNumber = scanner.nextLine().trim();
        System.out.println("Enter Amount to deposit: ");
        Double depositAmount = Double.valueOf(scanner.nextDouble());
        System.out.println("Deposited Funds: "+ depositAmount);
        bankService.depositAmount(accountNumber,depositAmount,"DEPOSIT");
    }

    private static void withDrawFunds(Scanner scanner,BankService bankService) {
        System.out.println("Enter Account number: ");
        String accountNumber = scanner.nextLine().trim();
        System.out.println("Enter Amount to Withdraw: ");
        Double withdrawAmount = Double.valueOf(scanner.nextDouble());
        bankService.withdraw(accountNumber,withdrawAmount,"WITHDRAW");
        System.out.println("Amount withdrawn successfully");

    }

    private static void transferAmount(Scanner scanner,BankService bankService) {
        System.out.println("Enter From Account number: ");
        String accountFrom = scanner.nextLine().trim();
        System.out.println("Enter To Account number: ");
        String accountTo = scanner.nextLine().trim();
        System.out.println("Enter Amount to transfer: ");
        double amount = scanner.nextDouble();
        bankService.transferFunds(accountFrom,accountTo,amount,"TRANSFER");
    }

    private static void generateStatement(Scanner scanner, BankService bankService) {
        System.out.println("Enter Account number: ");
        String accountNumber = scanner.nextLine().trim();
        bankService.getStatementDetails(accountNumber).forEach(r->{
            System.out.println(r.getAccountNumber() + " | " + r.getAmount() + " | " + r.getNote());
        });
    }

    private static void findCustomerByName(Scanner scanner, BankService bankService) {
        System.out.println("Enter Customer Name you want to find: ");
        String customerName = scanner.nextLine().trim();
        bankService.searchAccountByCustomerName(customerName).forEach(account->{
            System.out.println("Account Number :" + account.getAccountNumber()+ " | " + " Customer Id: "  +account.getCustomerId());
        });
    }

    private static void listAccountDetails(Scanner scanner, BankService bankService) {
        bankService.listOfAccounts().forEach(
                account -> {
                    System.out.println(account.getAccountNumber() + " - " + account.getAccountType() + " - " + account.getBalance());
                }
        );
    }

}
