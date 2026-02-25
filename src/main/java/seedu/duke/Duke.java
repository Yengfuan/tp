package seedu.duke;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.math.BigDecimal;

/**
 * Duke is running Fintrackpro, a CLI program that helps users
 * estimate savings required for their share of a BTO downpayment.
 */
public class Duke {

    private static final NumberFormat MONEY_FMT = NumberFormat.getCurrencyInstance(Locale.US);

    // to make sure that it is always 2dp + comma separator (eg 12,250.00)
    private static String formatMoney(BigDecimal amount){
        return MONEY_FMT.format(amount);
    }

    /**
     * Reads money amount with strict rules:
     * - digits only, optional decimal point
     * - max 2 decimal places
     * - non-negative
     * - no commas, no letters, no other symbols
     */
    private static BigDecimal readMoney(Scanner in, String prompt) {
        while (true) {
            System.out.println(prompt);
            String moneyString = in.nextLine().trim();

            // Only digits, optionally ".digits" (1-2 dp)
            // Allows: "10250", "10250.5", "10250.50"
            // Rejects: "-1", "10,250", "$100", "12.345", "12.", ".50", "12 3", "abc"
            if (!moneyString.matches("\\d+(\\.\\d{1,2})?")) {
                System.out.println("Bruh I need a valid amount like " +
                        "10250 or 10250.50 (numbers only, max 2 dp). Try again.");
                continue;
            }

            try {
                BigDecimal amount = new BigDecimal(moneyString);

                if (amount.compareTo(BigDecimal.ZERO) < 0){
                    System.out.println("The amount cannot be negative. Please try again.");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e){
                System.out.println("Invalid number. Please try again!");
            }
        }
    }

    private static LocalDate readFutureDate(Scanner in, String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = in.nextLine().trim();

            try {
                LocalDate date = LocalDate.parse(s);
                LocalDate today = LocalDate.now();

                if (!date.isAfter(today)) {
                    System.out.println("Deadline must be a future date. Try again.");
                    continue;
                }

                return date;

            } catch (DateTimeParseException e) {
                System.out.println("Date format needs to be YYYY-MM-DD (e.g., 2026-12-31). Try again.");
            }
        }
    }

    /**
     * Runs the Duke program which runs Fintrackpro
     */
    public static void main(String[] args) {
        String logo = """
            +---------+
            |   HDB   |
            |  [ ][ ] |
            |_________|
      Secure the Keys, Secure the Dream
            """;
        System.out.println("Welcome to Fintrack Pro!\n" + logo);
        System.out.println("I am FinBro's brother, FinTrack Pro!");

        Scanner in = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = in.nextLine().trim();
        if (name.isEmpty()){
            name = "friend";
        }
        System.out.println("Nice to meet you, " + name + "!");

        System.out.println("Hang tight... I have a few questions for you.");
        BigDecimal goal = readMoney(in,
                "What is the total amount that you and your partner have to pay for the downpayment? (in dollars)");

        BigDecimal legalFees = goal.multiply(new BigDecimal("0.025"));
        BigDecimal totalRequired = goal.add(legalFees);

        System.out.println("Sweeeett. Including 2.5% legal fees, you will need " + formatMoney(totalRequired));

        LocalDate deadline = readFutureDate(in, "When do you need to save this money by? (Enter in format YYYY-MM-DD)");
        LocalDate today = LocalDate.now();

        Period period = Period.between(today, deadline);

        // If deadline is in the past, period values can be negative — handle it nicely
        if (deadline.isBefore(today)) {
            System.out.println("That deadline is already in the past...Please pick a future date next time.");
        } else {
            System.out.println("You have " + period.getYears() + " years and "
                    + period.getMonths() + " months remaining.");
        }

        System.out.println("\nYou can now type anything you want");
        System.out.println("Type 'bye' to exit!\n");

        while (true) {
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye " + name + ". Stay disciplined and get that house that you always wanted!");
                break;
            }
            System.out.println("You said: " + input);
        }
        in.close();
    }
}
