package ssu.bank.utils;

public class Exchange {
	public static final double USD_TO_RUB = 79.23;
	public static final double EUR_TO_RUB = 88.36;
	public static final double RUB_TO_USD = 1.0/USD_TO_RUB;
	public static final double RUB_TO_EUR = 1.0/EUR_TO_RUB;
	public static final double EUR_TO_USD = EUR_TO_RUB/USD_TO_RUB;
	public static final double USD_TO_EUR = 1.0/EUR_TO_USD;

	public static Double getRate(String from, String to) {
		if (from.equals("USD")) {
			if (to.equals("RUB")) {
				return USD_TO_RUB;
			}
			if (to.equals("EUR")) {
				return USD_TO_EUR;
			}
		} else if(from.equals("EUR")) {
			if (to.equals("RUB")) {
				return EUR_TO_RUB;
			}
			if (to.equals("USD")) {
				return EUR_TO_USD;
			}
		} else if(from.equals("RUB")) {
			if (to.equals("USD")) {
				return RUB_TO_USD;
			}
			if (to.equals("EUR")) {
				return RUB_TO_EUR;
			}
		}
		return null;
	}
}
