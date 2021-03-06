package com.epam.domain;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Discount {

	private DiscountStrategy discountStrategy;

	public Discount(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public DiscountStrategy getDiscountStrategy() {
		return discountStrategy;
	}

	public enum DiscountStrategy {
        BIRTHDAY("User has birthday", 5.0),
        TICKET("Every 10th ticket", 50.0);

        private String name;
        private Double percent;

        DiscountStrategy(String name, Double percent) {
            this.name = name;
            this.percent = percent;
        }

		public String getName() {
			return name;
		}

		public Double getPercent() {
			return percent;
		}
	}
}
