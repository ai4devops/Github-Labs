import java.util.Random;
public class Main {
    public static void main(String[] args) {
        int randomNumber = generateRandomNumber(100, 1);
        System.out.println("Nombre aléatoire généré : " + randomNumber);
    }

    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}