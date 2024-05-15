package org.example.app.utils;

public class PriceValidator {
    private final static String PRICE_RGX =
            "^\\d+(\\.\\d{2})?$";

    private PriceValidator() {
    }

    public static boolean isPriceValid(Double price) {
        return price.isNaN() || !String.valueOf(price).matches(PRICE_RGX);
    }
}
