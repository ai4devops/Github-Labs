// write a rock paper scissors game
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String[] choices = {"rock", "paper", "scissors"};
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.print("Enter your choice (rock, paper, scissors): ");
        String userChoice = scanner.nextLine().toLowerCase();
        
        if (!isValidChoice(userChoice)) {
            System.out.println("Invalid choice. Please choose rock, paper, or scissors.");
            return;
        }
        
        String computerChoice = choices[rand.nextInt(choices.length)];
        System.out.println("Computer chose: " + computerChoice);
        
        String result = determineWinner(userChoice, computerChoice);
        System.out.println(result);
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }

    private static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}