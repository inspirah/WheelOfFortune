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
    private static final List<String> questions = Arrays.asList
        (
            "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG",
            "SOME OTHER STUFF"
        );
    
    private static final List<String> wheelWedges = Arrays.asList
        (
            "$5000","$600","$500","$300","$500","$800","$550",
            "$400","$300","$900","$500","$300","$900","$BANKRUPT",
            "$600","$400","$300","LOSE A TURN","$800","$350","$450",
            "$700","$300","$600"
        );
    

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
      displayMenu();
      playGame();}
      //System.out.println(wheelWedges);
  public static void playGame(){
      while(true)
      {
      try{
      displayMenu();
      System.out.println(questions.get(0));
      Random random = new Random();
      String userLetter;
      Scanner in = new Scanner(System.in);
      String input = in.nextLine();
      int value = Integer.valueOf(input);
      
      switch(value)
      {
          case 1: input = spin;          
            System.out.println("You've chosen " + spin);
            System.out.println("You landed on: " + wheelWedges.get(random.nextInt(wheelWedges.size())));
            System.out.println("Please enter a letter");
            userLetter = in.next().toUpperCase();
            if(userLetter.matches(questions.remove(0)))
            {
                questions.clear();
            }
            break;
          case 2: input = buy;
            System.out.println("You've chosen " + buy);
            break;
          case 3: input = solve;
            System.out.println("You've chosen " + solve);
            break;
          case 4: input = quit;
            System.out.println("You've chosen " + quit);
            System.exit(0);
          case 9: input = test;
            System.out.println("You've chosen " + test);
            System.out.println("Please enter a letter");
            
            userLetter = in.next().toUpperCase();
            if(userLetter.matches("[a-zA-Z]"))
                System.out.println("You've entered: " + userLetter);
            else{
                do{
                System.out.println("Invald input, please enter a letter!");
                userLetter = in.next().toUpperCase();
                }while(!userLetter.matches("[a-zA-Z]"));
                System.out.println("You've entered: " + userLetter);
            }
          
      }}
      catch(NumberFormatException e)
          {
              System.out.println("Please enter a number");
          }
          
      }
  }
  public static void displayMenu()
  {
      System.out.println("                       ======================");
      System.out.println("                       =  Wheel of Fortune  =");
      System.out.println("                       ======================");
      
      
      
      System.out.println("1: Spin the wheel");
      System.out.println("2: Buy a vowel");
      System.out.println("3: Solve the puzzle");
      System.out.println("4: Quit");
      System.out.println("9: Test letter input");
      System.out.println("Enter a choice:");
  }
  public static String revealLetter(String userLetter)
  {
      userLetter = "213";
      return userLetter;
      
  }
}
    
  
  

