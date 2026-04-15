// write a rock paper scissors game
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"pierre", "papier", "ciseaux"};
        System.out.println("Bienvenue dans le jeu Pierre-Papier-Ciseaux !");
        while (true) {
            System.out.print("Entrez votre choix (pierre, papier, ciseaux) ou 'quitter' pour arrêter : ");
            String userChoice = scanner.nextLine().toLowerCase();
            if (userChoice.equals("quitter")) {
                System.out.println("Merci d'avoir joué !");
                break;
            }
            if (!userChoice.equals("pierre") && !userChoice.equals("papier") && !userChoice.equals("ciseaux")) {
                System.out.println("Choix invalide. Réessayez.");
                continue;
            }
            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];
            System.out.println("L'ordinateur a choisi : " + computerChoice);

            if (userChoice.equals(computerChoice)) {
                System.out.println("Égalité !");
            } else if (
                    (userChoice.equals("pierre") && computerChoice.equals("ciseaux")) ||
                            (userChoice.equals("papier") && computerChoice.equals("pierre")) ||
                            (userChoice.equals("ciseaux") && computerChoice.equals("papier"))
            ) {
                System.out.println("Vous gagnez !");
            } else {
                System.out.println("Vous perdez !");
            }
        }
        scanner.close();
    }
}
