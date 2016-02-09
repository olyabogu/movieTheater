package com.epam.domain;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Discount {

    enum DiscountStrategy {
        BIRTHDAY("user has birthday", 5.0),
        TICKET("Every 10th ticket", 50.0);

        private String name;
        private Double percent;

        DiscountStrategy(String name, Double percent) {
            this.name = name;
            this.percent = percent;
        }
    }
}
