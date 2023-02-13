package revision;

import java.util.Random;
import java.util.Scanner;

public final class App implements Runnable{
    private App() {
    }

    public static void main(String[] args) {
        
        // Need the random class to carry out randomised operation
        Random random = new Random();

        // generate random number between 0 and 99 (specified number is exclusive)
        int randomNumber = random.nextInt(100);

        // create variable to store guess
        int myGuess = 0;

        // Expect input from keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 0 and 99");
        while(myGuess != randomNumber){
            myGuess = scanner.nextInt();
            if (myGuess < randomNumber){
                System.out.println("Your guess is too low");
            } else if (myGuess > randomNumber){
                System.out.println("Your guess is too high");
            } else {
                System.out.println("You have guessed it right!");
                scanner.close();
            }
        }

    }
}
