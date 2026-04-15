package lab5;

import java.util.Arrays;

public class SelfCheck {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        passed += checkEquals(
                "STANDARD + shipping",
                24.99,
                OrderCalculator.calculateTotal(
                        Arrays.asList(new OrderCalculator.Item("Book", 1, 20.00)),
                        "STANDARD",
                        null
                )
        );
        total += 1;

        passed += checkEquals(
                "PREMIUM + WELCOME10 + free shipping from original subtotal",
                49.50,
                OrderCalculator.calculateTotal(
                        Arrays.asList(new OrderCalculator.Item("Headphones", 1, 65.00)),
                        "PREMIUM",
                        "WELCOME10"
                )
        );
        total += 1;

        passed += checkEquals(
                "Unknown promo is ignored",
                19.99,
                OrderCalculator.calculateTotal(
                        Arrays.asList(new OrderCalculator.Item("Mouse", 1, 15.00)),
                        "STANDARD",
                        "BADCODE"
                )
        );
        total += 1;

        passed += checkThrows(
                "Negative quantity is rejected",
                new Runnable() {
                    @Override
                    public void run() {
                        OrderCalculator.calculateTotal(
                                Arrays.asList(new OrderCalculator.Item("Broken item", -1, 10.00)),
                                "STANDARD",
                                null
                        );
                    }
                }
        );
        total += 1;

        /////////////////
        // ici Etape 2 //
        /////////////////

        System.out.println();
        System.out.println("Result: " + passed + "/" + total + " checks passed");
    }

    private static int checkEquals(String label, double expected, double actual) {
        double roundedActual = Math.round(actual * 100.0) / 100.0;

        if (Math.abs(expected - roundedActual) < 0.001) {
            System.out.println("[PASS] " + label);
            return 1;
        }

        System.out.println("[FAIL] " + label + " -> expected " + expected + " but got " + roundedActual);
        return 0;
    }

    private static int checkThrows(String label, Runnable runnable) {
        try {
            runnable.run();
            System.out.println("[FAIL] " + label + " -> expected an exception");
            return 0;
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] " + label);
            return 1;
        } catch (Exception e) {
            System.out.println("[FAIL] " + label + " -> wrong exception type: " + e.getClass().getSimpleName());
            return 0;
        }
    }
}