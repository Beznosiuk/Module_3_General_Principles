package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) throws TechnicalErrorException {
        if (userDao == null) {
            throw new TechnicalErrorException();
        }

        User user = userDao.getUser(userId);
        if (user == null) {
            throw new NoUserException();
        }

        List<Order> orders = user.getAllOrders();
        if (orders.isEmpty()) {
            throw new NoOrderException();
        }

        return getOrderSum(orders);
    }

    private double getOrderSum(List<Order> orders)  throws TechnicalErrorException{
        double sum = 0.0;
        for (Order order : orders) {
            if (order.isSubmitted()) {
                Double total = order.total();
                if (total < 0) {
                    throw new OrderAmountException();
                }
                sum += total;
            }
        }
        return sum;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
