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

    private final static String spin = ("Spin the wheel");
    private final static String buy = ("Buy a vowel");
    private final static String solve = ("Solve the puzzle");
    private final static String quit = ("Quit");
    private final static String test = ("Test letter input");
    private static final Scanner input = new Scanner(System.in);
    private static final Random ran_num = new Random();
    private static final List<String> questions = Arrays.asList
        (
            "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG",
            "SOME OTHER STUFF"
        );

    private static final List<String> wheelWedges = Arrays.asList
        (
            "$5000", "$600", "$500", "$300", "$500", "$800", "$550",
            "$400", "$300", "$900", "$500", "$300", "$900", "$BANKRUPT",
            "$600", "$400", "$300", "LOSE A TURN", "$800", "$350", "$450",
            "$700", "$300", "$600"
        );
    private static final List<String> _menuChoices = Arrays.asList
        (
            "1. Spin the wheel",
            "2. Buy a vowel",
            "3. Solve the puzzle",
            "4. Quit the game",
            "", // 5
            "", // 6
            "", // 7
            "", // 8
            "9. Test letter input"
        );

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        playGame();
        
       
        
    }

    public static void playGame() 
    {
        while (true) 
        {
            try 
            {
                displayLogo();
                for (String menuChoice : _menuChoices) 
                {
                    if (!menuChoice.equals("")) 
                    {
                        System.out.println(menuChoice);
                    }
                }
                System.out.println("Enter a choice: ");
                String userInput = input.nextLine();
                int caseValue = Integer.valueOf(userInput);
                switch (caseValue) 
                {
                    case 1:
                        userInput = spin;
                        System.out.println("You've chosen " + spin);
                        System.out.println("You landed on: " + wheelWedges.get(ran_num.nextInt(wheelWedges.size())));
                        System.out.println("You've entered:" + revealLetter());
                        break;

                    case 2:
                        userInput = buy;
                        System.out.println("You've chosen " + buy);
                        break;

                    case 3:
                        userInput = solve;
                        System.out.println("You've chosen " + solve);
                        break;

                    case 4:
                        userInput = quit;
                        System.out.println("You've chosen " + quit);
                        System.exit(0);

                    case 9:
                        userInput = test;
                        System.out.println("You've chosen " + test);
                        System.out.println("You've entered: " + inputLetter());
                }
            } 
            catch (NumberFormatException NumFormExcept) 
            {
                System.out.println("Please enter a number");
            }

        }
    }

    public static void displayLogo() 
    {
        System.out.println("                       ======================");
        System.out.println("                       =  Wheel of Fortune  =");
        System.out.println("                       ======================");
        System.out.println(questions.get(0).replaceAll("[a-zA-Z]","_ "));
    }

    private static char inputLetter() 
    {
        char letter = ' ';
        boolean finished = false;

        while (!finished) {
            System.out.print("Enter a letter: ");

            String line = input.nextLine();
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

    private static String revealLetter() 
    {
        System.out.println("Tryme");
        String line = input.nextLine();
        String letter = "";
        if (line.matches("[a-z]"))
        {
            letter = questions.get(0).replace("_", "[a-zA-Z]");
        }
        return letter;
    }
}
