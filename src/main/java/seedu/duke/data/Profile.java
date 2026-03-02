package seedu.duke.data;

import java.math.BigDecimal;

/**
 * Manages the user's personal financial data including salary, savings, and BTO contribution ratio.
 *
 */
public class Profile {
    private BigDecimal monthlySalary;
    private BigDecimal currentSavings;
    private BigDecimal spendingGoal;
    private BigDecimal btoGoal;
    private double contributionRatio;

    /**
     * Initializes a profile with zero Salary/Savings and a default 50/50 split ratio.
     */
    public Profile() {
        this.monthlySalary = BigDecimal.ZERO;
        this.currentSavings = BigDecimal.ZERO;
        this.contributionRatio = 0.5;
        this.spendingGoal = BigDecimal.ZERO;
        this.btoGoal = BigDecimal.ZERO;
    }

    /**
     * Updates the user's bto goal.
     * @param btoGoal The bto goal amount.
     */
    public void setBtoGoal(BigDecimal btoGoal) {
        this.btoGoal = btoGoal;
    }

    public BigDecimal getBtoGoal() {
        return btoGoal;
    }

    /**
     * Updates the user's monthly salary.
     * @param monthlySalary The new salary amount.
     */
    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * Updates the user's current liquid savings.
     * @param currentSavings The new savings amount.
     */
    public void setCurrentSavings(BigDecimal currentSavings) {
        this.currentSavings = currentSavings;
    }

    public BigDecimal getCurrentSavings() {
        return currentSavings;
    }

    /**
     * Sets the individual's share of the BTO cost relative to their partner.
     * @param contributionRatio A decimal representing user's share (e.g. 0.5 for 50%).
     */
    public void setContributionRatio(double contributionRatio) {
        this.contributionRatio = contributionRatio;
    }

    public double getContributionRatio() {
        return contributionRatio;
    }

    /**
     * Sets the user's spending goal.
     * @param spendingGoal The spending goal amount.
     */
    public void setSpendingGoal(BigDecimal spendingGoal) {
        this.spendingGoal = spendingGoal;
    }

    public BigDecimal getSpendingGoal() {
        return spendingGoal;
    }
}
