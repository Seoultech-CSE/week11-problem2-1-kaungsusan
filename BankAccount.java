import java.util.Scanner;
import java.util.InputMismatchException;

class InsufficientBalanceException extends Exception {

    private double balance;
    private double amount;

    public InsufficientBalanceException(double balance, double amount) {

        super("Withdrawal amount $" + amount
                + " exceeds current balance $" + balance);

        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }
}

public class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be positive.");
        }

        balance += amount;

        System.out.println("$" + amount
                + " successfully deposited.");
    }

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {

            throw new InsufficientBalanceException(
                    balance, amount);
        }

        balance -= amount;

        System.out.println("$" + amount
                + " successfully withdrawn.");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        BankAccount account = new BankAccount(500.0);

        System.out.println(
                "=== Welcome to the Interactive Banking System ===");

        System.out.println("Initial Balance: $500.0");

        try {

            System.out.print(
                    "\nEnter the amount to DEPOSIT: ");

            double depositAmount = input.nextDouble();

            account.deposit(depositAmount);

        }
        catch (InputMismatchException e) {

            System.out.println(
                    "Invalid input. Please enter a numeric value.");

            input.nextLine(); 
        }
        catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
        finally {

            System.out.println(
                    "Current Balance: $" + account.getBalance());
        }

        try {

            System.out.print(
                    "\nEnter the amount to WITHDRAW: ");

            double withdrawAmount = input.nextDouble();

            account.withdraw(withdrawAmount);

        }
        catch (InputMismatchException e) {

            System.out.println(
                    "Invalid input. Please enter a numeric value.");

            input.nextLine(); 
        }
        catch (InsufficientBalanceException e) {

            System.out.println(e.getMessage());

            System.out.println(
                    "Available Balance: $" + e.getBalance());
        }
        finally {

            System.out.println(
                    "Current Balance: $" + account.getBalance());
        }

        System.out.println(
                "\n=== Thank you for using our service ===");

        input.close();
    }
}