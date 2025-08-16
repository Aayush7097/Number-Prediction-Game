import java.util.Random;
import java.util.Scanner;

public class NumberPredictionGame {

    private static Scanner scanner = new Scanner(System.in);
    private static int bestScore = Integer.MAX_VALUE; // Track best score across games

    public static void main(String[] args) {
        System.out.println("🎮 Welcome to the Number Prediction Game!");
        boolean playAgain;

        do {
            playGame();
            playAgain = restartPrompt();
        } while (playAgain);

        System.out.println("👋 Thanks for playing! Goodbye!");
    }

    // Main game logic
    private static void playGame() {
        System.out.println("\nSelect Difficulty Level:");
        System.out.println("1. Easy (1–50)");
        System.out.println("2. Medium (1–100)");
        System.out.println("3. Hard (1–200)");
        System.out.print("Enter choice (1/2/3): ");

        int choice = scanner.nextInt();
        int range;
        switch (choice) {
            case 1: range = 50; break;
            case 2: range = 100; break;
            case 3: range = 200; break;
            default:
                System.out.println("⚠ Invalid choice! Defaulting to Medium (1–100).");
                range = 100;
        }

        Random random = new Random();
        int numberToGuess = random.nextInt(range) + 1; // Random number
        int attempts = 0;
        int maxAttempts = 5; // Limit number of attempts
        boolean guessed = false;

        System.out.println("\nI have chosen a number between 1 and " + range + ".");
        System.out.println("You have " + maxAttempts + " attempts. Good luck!");

        while (attempts < maxAttempts && !guessed) {
            System.out.print("\nEnter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == numberToGuess) {
                System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                guessed = true;

                // Update best score
                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("🏆 New High Score! Best attempt so far: " + bestScore);
                } else {
                    System.out.println("⭐ Your best attempt so far is: " + bestScore);
                }
            } else if (userGuess > numberToGuess) {
                System.out.println("📉 Too high!");
            } else {
                System.out.println("📈 Too low!");
            }
        }

        if (!guessed) {
            System.out.println("\n❌ Out of attempts! The correct number was: " + numberToGuess);
            System.out.println("⭐ Your best attempt so far is: " + (bestScore == Integer.MAX_VALUE ? "N/A" : bestScore));
        }
    }

    // Ask user if they want to restart
    private static boolean restartPrompt() {
        System.out.print("\nDo you want to play again? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}
