import java.util.Scanner;

public class BankingApp {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            clearScreen();
            System.out.println("Welcome to the Awesome Banking App!");
            System.out.println();

            System.out.println("Enter your name: ");
            String customerName = sc.nextLine();
            System.out.println("Enter your ID: ");
            String customerId = sc.nextLine();

            BankAccount bank1 =  new BankAccount(customerName, customerId);
            clearScreen();
            bank1.showMenu();
        }

        private static void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    class BankAccount{
        private int balance;
        private int prevTran;//previous transaction
        private final String customerName;
        private final String customerId;

        BankAccount(String cname, String cid){
            customerName = cname;
            customerId = cid;
            balance = 0;
            prevTran = 0;
        }
        private void deposit(int amt){
            if(amt!=0){
                balance += amt;
                prevTran = amt;
            }
        }
        private void withdraw(int amt){
            if(amt!=0){
                balance -= amt;
                prevTran = -amt;
            }
        }

        private void getprevTransaction(){
            if(prevTran>0){
                System.out.println("Deposited : " + prevTran);
            }
            else if(prevTran<0){
                System.out.println("Withdraw : "+ Math.abs(prevTran));
            }
            else {
                System.out.println("No transaction is occurred !!!");
            }
        }

        private static final double currencyConversionRate = 0.014; // Example conversion rate (1 USD to INR)

        private double convertToUSD(double amount) {
            return amount / currencyConversionRate;
        }

        private double convertToINR(double amount) {
            return amount * currencyConversionRate;
        }

        private void handleInvalidInput() {
            System.out.println("Invalid input! Please try again.");
            pause();
        }

        void showMenu(){
            char option;
            Scanner sc = new Scanner(System.in);
            do {
                clearScreen();
                System.out.println("Welcome " + customerName + " (ID: " + customerId + ")");
                System.out.println("A. Check balance\nB. Deposit\nC. Withdraw\nD. Previous Transaction\nE. convert balance to USD\nF. Convert balance to INR\nG. Exit");

                System.out.print("Enter your choice: ");
                option = Character.toUpperCase(sc.next().charAt(0));

                switch (option) {
                    case 'A' -> {
                        clearScreen();
                        System.out.println("Balance : " + balance);
                        pause();
                    }
                    case 'B' -> {
                        clearScreen();
                        System.out.println("Enter the amount to deposit: ");
                        int amt = sc.nextInt();
                        deposit(amt);
                        System.out.println("Deposited: " + amt);
                        pause();
                    }
                    case 'C' -> {
                        clearScreen();
                        System.out.println("Enter the amount to withdraw: ");
                        int amt = sc.nextInt();
                        if (amt > balance || amt < 0) {
                            handleInvalidInput();
                        } else {
                            withdraw(amt);
                            System.out.println("Withdrawn: " + amt);
                        }
                        pause();
                    }
                    case 'D' -> {
                        clearScreen();
                        getprevTransaction();
                        pause();
                    }
                    case 'E' -> {
                        clearScreen();
                        System.out.println("Balance in USD: " + convertToUSD(balance));
                        pause();
                    }
                    case 'F' -> {
                        clearScreen();
                        System.out.println("Balance in INR: " + convertToINR(balance));
                        pause();
                    }
                    case 'G' -> clearScreen();
                    default -> {
                        System.out.println("Invalid option!! Please try again!!");
                        pause();
                    }
                }
            }
            while(option!='G');
        }

        private void pause() {
            System.out.print("Press Enter to continue...");
            new Scanner(System.in).nextLine();
        }

        private static void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

