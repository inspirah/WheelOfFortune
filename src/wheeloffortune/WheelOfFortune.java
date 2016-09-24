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
//This is where I declare most of my finals and variables.

    private final static String SPIN = ("Spin the wheel");
    private final static String BUY = ("Buy a vowel");
    private final static String SOLVE = ("Solve the puzzle");
    private final static String QUIT = ("Quit");
    private final static String TEST = ("Test letter input");
    private final static String TOGGLE = ("Toggle");
    private static String userEntry = "";//global variable that is updated and used to display a letter that a user entered.
    private static String unmaskedAns = "";
    private static final String phrase = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
    private static final Scanner input = new Scanner(System.in);
    private static final Random ran_num = new Random();
    private static final List<String> questions = Arrays.asList(
            "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG",
            "SOME OTHER STUFF"
    );

    private static final List<String> wheelWedges = Arrays.asList(
            "$5000", "$600", "$500", "$300", "$500", "$800", "$550",
            "$400", "$300", "$900", "$500", "$300", "$900", "$BANKRUPT",
            "$600", "$400", "$300", "LOSE A TURN", "$800", "$350", "$450",
            "$700", "$300", "$600"
    );
    private static final List<String> _menuChoices = Arrays.asList(
            "\n1. Spin the wheel",
            "2. Buy a vowel",
            "3. Solve the puzzle",
            "4. Quit the game",
            "", // 5
            "", // 6
            "", // 7
            "8. Toggle",
            "9. Test letter input"
    );

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        while (true) //left as true because the user will only be able to exit the program by menu choice # 4.
        {
            try //try catch statement to make sure the user enters nothing else but a number choice from the menu.
            {
                displayLogo();
                mask();
                for (String menuChoice : _menuChoices) //iterates through _menuChoices in the form of the key menuChoice
                {
                    if (!menuChoice.equals("")) {
                        System.out.println(menuChoice);
                    }
                }
                System.out.println("Enter a choice: ");
                String userInput = input.nextLine();
                int caseValue = Integer.valueOf(userInput);//declared caseValue integer to the integer value of the userInput so that the switch case can be used.
                switch (caseValue) {
                    case 1:
                        userInput = SPIN;
                        System.out.println("You've chosen " + SPIN);
                        System.out.println("You landed on: " + wheelWedges.get(ran_num.nextInt(wheelWedges.size())));//uses ran_num from random class to generate random int from the array list wheelWedges.
                        revealLetter();

                        break;

                    case 2:
                        userInput = BUY;
                        System.out.println("You've chosen " + BUY);
                        break;

                    case 3:
                        userInput = SOLVE;
                        System.out.println("You've chosen " + SOLVE);
                        break;

                    case 4:
                        userInput = QUIT;
                        System.out.println("You've chosen " + QUIT);
                        System.exit(0);

                    case 8:
                        userInput = TOGGLE;
                        System.out.println("You've chosen " + TOGGLE);
                        unmask();
                        break;

                    case 9:
                        userInput = TEST;
                        System.out.println("You've chosen " + TEST);
                        System.out.println("You've entered: " + inputLetter());
                        break;
                }
            } catch (NumberFormatException NumFormExcept) {
                System.out.println("Please enter a number");
            }

        }
    }

    public static void displayLogo() {
        System.out.println("");
        System.out.println("                       ======================");
        System.out.println("                       =  Wheel of Fortune  =");
        System.out.println("                       ======================");
    }

    public static void unmask()//Here I try to create the toggling effect.
    {
        for (char letter : phrase.toCharArray()) {
            if (userEntry.indexOf(letter) != -1) {
                if (letter >= 'A' && letter <= 'Z') {
                    userEntry = phrase + " ";
                    //System.out.print(letter);
                } else {
                    System.out.print("  ");
                }
            }
        }
    }

    public static void mask()//Hides characters from the answer string that was converted to a CharArray
    {
        for (char letter : phrase.toCharArray())//This is saying for a character 'Letter' in the converted CharArray, 'answer' if the global variable userEntry
        {
            if (userEntry.indexOf(letter) == -1) {
                if (letter >= 'A' && letter <= 'Z') {
                    System.out.print("_ ");
                } else {
                    System.out.print("  ");
                }
            } else {
                System.out.print(letter);
            }
        }
    }

    private static char inputLetter() {
        char letter = ' ';
        boolean finished = false;//boolean value for while loop

        while (!finished) {
            System.out.print("Enter a letter: ");
            String line = input.nextLine().toUpperCase();
            if (line.length() != 1) {
                System.out.println("Enter just one letter");
            } else {
                letter = line.charAt(0);
                if (!Character.isLetter(letter)) {
                    System.out.println("That is not a letter");
                } else {
                    finished = true;
                }
            }
        }
        return letter;
    }

    private static char revealLetter()//modified inputLetter() method that allows me to add a character to the global variable userEntry to reveal letters.
    {
        char letter = ' ';
        boolean finished = false;

        while (!finished) {
            System.out.print("Enter a letter: ");
            String line = input.nextLine().toUpperCase();
            if (line.length() != 1) {
                System.out.println("Enter just one letter");
            } else {
                letter = line.charAt(0);
                userEntry += line;//global variable updated to add the input from user 'line' to userEntry to allow me to display that letter in the masking method
                if (!Character.isLetter(letter)) {
                    System.out.println("That is not a letter");
                } else {
                    finished = true;
                }
            }
        }
        return letter;
    }
}
