package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    public static final double NUMBER_OF_MONTH = 12;
    public static final double ZERO_VALUE = 0;

    /**
     * @param loanAmount   principal amount
     * @param termDuration term of mortgage in years
     * @param rate         rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int loanAmount, int termDuration, double rate) {

        if (isInvalidInputData(loanAmount, termDuration, rate)) {
            throw new InvalidInputException("Negative values are not allowed");
        } else {
            rate /= 100.0;
            return calculateMonthlyPaymentAmount(loanAmount, termDuration, rate);
        }
    }

    private static boolean isInvalidInputData(int loanAmount, int termDuration, double rate) {
        return loanAmount < ZERO_VALUE || termDuration <= ZERO_VALUE || rate < ZERO_VALUE;
    }

    private static double calculateMonthlyPaymentAmount(double loanAmount, double termDuration, double rate) {
        double monthlyRate = rate / NUMBER_OF_MONTH;
        double termInMonth = termDuration * NUMBER_OF_MONTH;

        if (rate == ZERO_VALUE) {
            return loanAmount / termInMonth;
        } else {
            return loanAmount * monthlyRate / (1 - Math.pow(1 + monthlyRate, -termInMonth));
        }
    }
}
