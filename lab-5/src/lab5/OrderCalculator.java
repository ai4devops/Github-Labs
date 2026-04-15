package lab5;

import java.util.List;

public class OrderCalculator {

    public static class Item {
        private final String name;
        private final int quantity;
        private final double unitPrice;

        public Item(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }
    }

    public static double calculateTotal(List<Item> items, String customerType, String promoCode) {
        double subtotal = 0;

        for (Item item : items) {
            subtotal += item.getQuantity() * item.getUnitPrice();
        }

        if ("WELCOME10".equals(promoCode)) {
            subtotal = subtotal - 10;
        } else if (promoCode != null && !promoCode.isBlank()) {
            throw new IllegalArgumentException("Unknown promo code: " + promoCode);
        }

        if ("PREMIUM".equals(customerType)) {
            subtotal = subtotal * 0.9;
        }

        double shipping = subtotal > 50 ? 0 : 4.99;
        return subtotal + shipping;
    }
}