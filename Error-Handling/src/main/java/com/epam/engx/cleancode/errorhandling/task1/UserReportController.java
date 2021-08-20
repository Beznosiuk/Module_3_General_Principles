package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage;

        try {
            totalMessage = getUserTotalMessage(userId);
        } catch (TechnicalErrorException e) {
            return "technicalError";
        }

        model.addAttribute("userTotalMessage", totalMessage);
        return "userTotal";
    }

    private String getUserTotalMessage(String userId) throws TechnicalErrorException {
        Double amount;

        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (NoUserException e) {
            return "WARNING: User ID doesn't exist.";
        } catch (NoOrderException e) {
            return "WARNING: User have no submitted orders.";
        } catch (OrderAmountException e) {
            return "ERROR: Wrong order amount.";
        }

        return "User Total: " + amount + "$";
    }

    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
