package lab5;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<OrderCalculator.Item> items = Arrays.asList(
                new OrderCalculator.Item("Notebook", 2, 4.90),
                new OrderCalculator.Item("Pen", 3, 1.20),
                new OrderCalculator.Item("Backpack", 1, 39.99)
        );

        double total = OrderCalculator.calculateTotal(items, "PREMIUM", "WELCOME10");
        System.out.println("Total = " + total);
    }
}