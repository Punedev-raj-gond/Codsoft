
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private int maxAttempts;
    private int maxRounds;
    private int totalScore;

    public NumberGuessingGame(int maxAttempts, int maxRounds) {
        this.maxAttempts = maxAttempts;
        this.maxRounds = maxRounds;
        this.totalScore = 0;
    }

    // Method to generate a random number within a specified range
    private int generateRandomNumber(int min, int max) {
        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }

    // Method to start the game
    public void startGame() {
        Scanner sc = new Scanner(System.in);
        int currentRound = 1;

         //this loop is used for multiple round user can attempt
        while (currentRound <= maxRounds) {
            System.out.println("\n### Round " + currentRound + " ###");
            int randomNumber = generateRandomNumber(1, 100);
            boolean isCorrect = false;
            int attemptsUsed = 0;

             // this loop ensures user guessed within max attempt
            while (attemptsUsed < maxAttempts && !isCorrect) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess;

                // Validating  input from user
                try {
                    userGuess = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    continue;
                }

                attemptsUsed++;

                if (userGuess == randomNumber) {
                    System.out.println("Correct! You've guessed the number.");
                    totalScore += (maxAttempts - attemptsUsed + 1); // Higher score for fewer attempts
                    isCorrect = true;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            // If the user couldn't guess correctly within max attempts
            if (!isCorrect) {
                System.out.println("Sorry! You've used all attempts. The correct number was " + randomNumber + ".");
            }

            System.out.println("Score for this round: " + (isCorrect ? (maxAttempts - attemptsUsed + 1) : 0));

            // Check if user wants to play another round
            if (currentRound < maxRounds) {
                System.out.print("Do you want to play the next round? (yes/no): ");
                String response = sc.nextLine().toLowerCase();
                if (!response.equals("yes")) {
                    break;
                }
            }
            currentRound++;
        }

        // End of the game and displaying final score to the user
        System.out.println("\nGame Over! Your total score is: " + totalScore);
        sc.close();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Set maximum attempts and rounds for the game
        System.out.print("Enter the maximum number of attempts per round: ");
        int maxAttempts = input.nextInt();
        System.out.print("Enter the maximum number of rounds: ");
        int maxRounds = input.nextInt();

        NumberGuessingGame game = new NumberGuessingGame(maxAttempts, maxRounds);
        game.startGame();

        input.close();
    }
}









