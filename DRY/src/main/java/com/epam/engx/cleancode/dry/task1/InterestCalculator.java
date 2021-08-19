package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private int durationBetweenDatesInYears(Date startDate, Date endDate) {
        return calculateDuration(startDate, endDate);
    }

    private int calculateDuration(Date startDate, Date endDate) {
        Calendar startCalendar = setCalendar(startDate);
        Calendar endCalendar = setCalendar(endDate);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);

        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR)) {
            return diffYear - 1;
        }
        return diffYear;
    }

    private Calendar setCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        double interest = 0;
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            if (AGE <= accountDetails.getAge()) {
                interest = calculateInterest(accountDetails, SENIOR_PERCENT);
            } else {
                interest = calculateInterest(accountDetails, INTEREST_PERCENT);
            }
        }
        return BigDecimal.valueOf(interest);
    }

    private double calculateInterest(AccountDetails accountDetails, double percent) {
        //interest = (PrincipalAmount * DurationInYears * AnnualInterestRate) / 100
        return (accountDetails.getBalance().doubleValue()
                * durationSinceStartDateInYears(accountDetails.getStartDate()) * percent) / 100;
    }

    private int durationSinceStartDateInYears(Date startDate) {
        return calculateDuration(startDate, new Date());
    }
}
