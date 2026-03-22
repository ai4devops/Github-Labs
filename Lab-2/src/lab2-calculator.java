public class Main {

    public static int calculator(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Division par zero impossible");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Operateur non supporte : " + operator);
        }
    }

    public static void main(String[] args) {
        int nombre1 = 10;
        int nombre2 = 5;
        String operateur = "*";

        int resultat = calculator(nombre1, nombre2, operateur);
        System.out.println("Resultat : " + resultat);
    }
}