/*
 * IST 261, Section 1
 * Fall 2016
 * Instructor: Phil O'Connell
 * Student: Alan Hua 
 * ID: azh5318
 */
package wheeloffortune;

import java.util.*;

public class WheelOfFortune {

    // To read from the keyboard
    private static final Scanner _keyboard = new Scanner(System.in);

    // Used to get random values for puzzle and wheel
    private static final Random _random = new Random();

    // True if we want to show all letters
    private static boolean revealLetters = false;

    //Variable set to 0 for use when needed to get the winnings amount a user has spun
    private static int winnings = 0;

    /*
  * These are the wedges that are part of the wheel.
  * There are 24.  Some values can appear more than once
     */
    private static final List<String> _wedges = Arrays.asList(
            /* 01 */"$5000",
            /* 02 */ "$600",
            /* 03 */ "$500",
            /* 04 */ "$300",
            /* 05 */ "$500",
            /* 06 */ "$800",
            /* 07 */ "$550",
            /* 08 */ "$400",
            /* 09 */ "$300",
            /* 10 */ "$900",
            /* 11 */ "$500",
            /* 12 */ "$300",
            /* 13 */ "$900",
            /* 14 */ "BANKRUPT",
            /* 15 */ "$600",
            /* 16 */ "$400",
            /* 17 */ "$300",
            /* 18 */ "LOSE A TURN",
            /* 19 */ "$800",
            /* 20 */ "$350",
            /* 21 */ "$450",
            /* 22 */ "$700",
            /* 23 */ "$300",
            /* 24 */ "$600"
    );

    /*
  * The number of wedges will not change throughout the game
  * We can cache the value so we're not calling .size() over and over
     */
    private static final int _wedgeCount = _wedges.size();

    private static String chooseRandomWedgeValue() {
        // Choose a random index
        int randomWedgeIndex = _random.nextInt(_wedgeCount);

        //This converts the strings in the array to an int and replaces the $ sign to "", 
        //so that the values obtained from the strings will be stored into winnings.
        //The if else if statements will compute wether the random index shows up as LOSE A TURN
        //or BANKRUPT and either do nothing for LOSE A TURN or set the winnings to 0 for BANKRUPT
        if (_wedges.get(randomWedgeIndex) != "LOSE A TURN" && _wedges.get(randomWedgeIndex) != "BANKRUPT" && !guessedLetters.containsKey(true)) {
            winnings += Integer.parseInt(_wedges.get(randomWedgeIndex).replace("$", ""));
        } else if (_wedges.get(randomWedgeIndex) == "BANKRUPT") {
            winnings = 0;
        }

        // Return the corresponding wedge
        return _wedges.get(randomWedgeIndex);
    }

    // The menu choices
    private static final List<String> _menuChoices = Arrays.asList(
            "1. Spin the wheel",
            "2. Buy a vowel",
            "3. Solve the puzzle",
            "4. Quit the game"
            //"", // 5 possibly used in the future
            //"", // 6 possibly used in the future
            //"", // 7 possibly used in the future
            //"8. Toggle puzzle reveal", commented out for clean look
            //"9. Test letter input" commented out for clean look
    );
    private static final int _quitChoiceNumber = 4;

    // The different puzzles to choose from
    private static final List<String> _puzzles = Arrays.asList(
            "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG",
            "PENN STATE ABINGTON",
            "INFORMATION SCIENCES AND TECHNOLOGY"
    );

    /*
  * The number of puzzles will not change throughout the game
  * We can cache the value so we're not calling .size() over and over
     */
    private static final int _puzzlesCount = _puzzles.size();

    /*
  * We will store the guessed letters in a hash map.
  * The "key" will be the character that was guessed
  * The "value" will be true/false
  *
  * Actually, the "value" aspect of this is not relevant.
  * Just the fact that a letter appears in the map as a key, is enough to imply
  * it was guessed.
     */
    private static Map<Character, Boolean> guessedLetters = new HashMap<>();
    /*
  * Given a puzzle, return a masked version, with hidden letters
     */
    private static String maskPuzzle(String puzzle, boolean revealLetters) {
        // Use a string builder, since Java strings are immutable
        StringBuilder maskedPuzzle = new StringBuilder();

        // For each letter in the puzzle
        for (int i = 0; i < puzzle.length(); i++) {
            // Current letter
            char c = puzzle.charAt(i);

            /*
      * Either we're revealing all letters, or we've already guessed the
      * letter
             */
            boolean isLetterGuessed = revealLetters || guessedLetters.containsKey(c);

            /*
      * If the letter is not blank (we don't mask blanks), and the letter
      * has not been guessed, then we will mask it.
             */
            if (c != ' ' && !isLetterGuessed) {
                c = '_';
            }

            // Put one space after each character (even a space) in the puzzle
            maskedPuzzle.append(c + " ");
        }

        // Convert the string builder to a string and return it
        return maskedPuzzle.toString();
    }

    // Choose a random puzzle
    private static String chooseRandomPuzzle() {
        // Choose a random puzzle index
        int randomPuzzleIndex = _random.nextInt(_puzzlesCount);

        //Return the corresponding puzzle
        return _puzzles.get(randomPuzzleIndex);
    }

    // Determine if the given number choice actually appears on the menu
    private static boolean isValidMenuChoice(int choice) {
        if ((choice < 1) || (choice > _menuChoices.size())) {
            return false;
        }

        // Subtrace 1 because arrays/lists are zero-based
        int index = choice - 1;
        String menuText = _menuChoices.get(index);

        return !menuText.equals("");
    }

    // Input a letter from the keyboard
    private static char inputLetter() {
        char letter = ' ';
        boolean finished = false;

        while (!finished) {
            System.out.print("Enter a letter: ");

            String line = _keyboard.nextLine();
            if (line.length() != 1) {
                System.out.println("Enter just one letter");
            } else {
                // Convert letter to upper case
                letter = Character.toUpperCase(line.charAt(0));
                if (!Character.isLetter(letter)) {
                    System.out.println("That is not a letter");
                } else{
                    // Will exit the loop
                    finished = true;
                }
             //the mechanism if a user entered a letter already it will ask to enter a different letter.
            }if(guessedLetters.containsKey(letter)){
                    System.out.println("You've already entered that! Please enter a different letter.");
                    //Keeps the loop going if same letter appears.
                    finished = false;
                }
        }

        return letter;
    }

    // Display the game menu, and handle the choices made
    private static void gameMenu() {
        // Choice from the menu
        int choice = 0;

        // Line entered from keyboard
        String line = "";

        // True when user wants to quit
        boolean quit = false;

        // Choose one of the puzzles at random
        String puzzle = chooseRandomPuzzle();

        // Repeat the menu until the user chooses to quit
        while (!quit) {
            System.out.println("                      ======================");
            System.out.println("                      =  Wheel Of Fortune  =");
            System.out.println("                      ======================");
            System.out.println("                                            ");

            System.out.println(maskPuzzle(puzzle, revealLetters));
            System.out.println("Winnings: $" + winnings);

            // Loop over the menu choices, and display each one
            for (String menuChoice : _menuChoices) {
                // Skip blank place-holder choices
                if (!menuChoice.equals("")) {
                    System.out.println(menuChoice);
                }
            }
            System.out.print("Enter choice: ");
            line = _keyboard.nextLine();
            try {
                // If the input was not an integer, then that error will be caught
                choice = Integer.parseInt(line);
            } catch (NumberFormatException nfe) {
                // Error message, then go to the top of the loop
                System.out.println("Invalid input");
                continue;
            }

            // If not valid, then go back to the top of the loop
            if (!isValidMenuChoice(choice)) {
                System.out.println("Not a menu choice");
                continue;
            }

            System.out.println("You chose: " + _menuChoices.get(choice - 1));
            switch (choice) {
                case _quitChoiceNumber:
                    // This will allow us to leave the menu loop
                    quit = true;
                    break;

                case 1:
                    System.out.println("You landed on: " + chooseRandomWedgeValue());
                    char letter = inputLetter();
                    //If statement to see if user enters a vowel
                    if (letter == 'A' || letter == 'E' || letter == 'I'
                            || letter == 'O' || letter == 'U') {
                        //If the user inputs a vowel they get an error message
                        System.out.print("\nCan only buy Vowels, sorry.\n");
                    }
                    //else a user enters a letter that is not a vowel, it will set that letter true and reveal.
                    else {
                        guessedLetters.put(letter, true);
                    }
                    System.out.println("Your letter is: " + letter);
                    break;
                case 2:
                    char vowelInput = inputLetter();
                    if (vowelInput == 'A' || vowelInput == 'E'|| vowelInput == 'I' 
                            || vowelInput == 'O' || vowelInput == 'U') {
                        //The user can only buy a vowel if they have $250 or more than $250.
                        if (winnings >= 250) {
                            //subtract $250 from user's winnings
                            winnings -= 250;
                            //Tells the user what vowel they bought.
                            System.out.print("\nYou bought:" + vowelInput + "\n");
                            //inputs the vowel into the puzzle if it is there
                            guessedLetters.put(vowelInput, true);
                        } else {
                            //Message to user for not having enough money to buy a vowel
                            System.out.print("\nNot enough money to buy a vowel. Each costs $250.");
                        }
                    } else {
                        //Message to user that user can only buy vowels
                        System.out.print("\nVowels only.");
                    }
                    break;
                case 3://here I try to do something with solving but failed.
                    while(true){
                        char solvingLetters = inputLetter();
                        int i;
                        for(i = 0; i<puzzle.length(); i++){
                        if(solvingLetters == puzzle.charAt(i))
                        {
                            System.out.println("GOOD");
                        }
                        guessedLetters.put(solvingLetters, true);
                        }
                    }//HERE I CAN MAKE IT MATCH A PUZZLE AND EXIT :).
//                    line = _keyboard.nextLine().toUpperCase();
//                    if(line.matches(puzzle))
//                    {
//                        System.out.println("You've won the game! Now exiting!");
//                        System.exit(0);
//                    }else{
//                        System.out.println("Nice try, please continue.");
//                    }

                /*
          Removed for requirements
          
          case 8: // Toggle reveal letters
          revealLetters = !revealLetters;
          break;

        case 9: // Test to read in a letter from the keyboard
          System.out.println("Your letter is: " + inputLetter());
          break;
                 */
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameMenu();
    }

}
