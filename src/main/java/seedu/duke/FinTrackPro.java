package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.util.InputUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.math.BigDecimal;

public class FinTrackPro {

    private final Ui ui;

    public FinTrackPro(Ui ui){
        this.ui = ui;
    }

    public void run() {

        ui.showWelcome();

        Scanner in = new Scanner(System.in);


        String name = ui.readLine(in, "What is your name?");

        if (name.isEmpty()){
            name = "friend";
        }

        ui.greet(name.trim());

        ui.printLine("");
        ui.printLine("Hang tight... I have a few questions for you.");

        BigDecimal goal = InputUtil.readMoney(ui, in,
                "What is the total amount that you and your partner have to pay for "
                        + "the downpayment? (in dollars)");

        BigDecimal legalFees = goal.multiply(new BigDecimal("0.025"));
        BigDecimal totalRequired = goal.add(legalFees);

        ui.printLine("Sweeeett. Including 2.5% legal fees, you will need "
                + InputUtil.formatMoney(totalRequired));

        LocalDate deadline = InputUtil.readFutureDate(
                ui,
                in,
                "When do you need to save this money by? "
                        + "(Enter in format YYYY-MM-DD)"
        );
        LocalDate today = LocalDate.now();

        Period period = Period.between(today, deadline);

        ui.printLine("You have " + period.getYears() + " years and "
                + period.getMonths() + " months remaining.");

        ui.printLine("");
        ui.printLine("Type 'help' to view my currently supported commands!");
        ui.printLine("Any non-command word would be echoed back to you you you");
        ui.printLine("");

        String userInput = ui.readLine(in, "");
        while (!userInput.equalsIgnoreCase("bye")) {
            handleCommand(userInput);
            userInput = ui.readLine(in, "");
        }
        ui.goodBye(name);
        in.close();
    }

    private void handleCommand(String userInput) {
        if (userInput.trim().isEmpty()) {
            ui.printLine("Cannot process empty description!");
            return;
        }
        String command = Parser.parseCommand(userInput);

        switch (command) {
        case "help":
            ui.showHelpMessage();
            break;
        case "category":
//            addCategoryToExpense();
            break;
        default:
            ui.printLine("You said: " + userInput);
        }
    }
}
