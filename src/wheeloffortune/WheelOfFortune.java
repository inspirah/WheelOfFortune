/*
* IST 261, Section 1
* Fall 2016
* Instructor: Phil O'Connell
* Student: Alan Hua 
* ID: azh5318
*/
package wheeloffortune;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WheelOfFortune {
    

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      final String case1 = ("Spin the wheel");
      final String case2 = ("Buy a vowel");
      final String case3 = ("Solve the puzzle");
      final String case4 = ("Quit");
      final String case9 = ("Test letter input");
      
      displayLogo();
      String userLetter;
      Scanner in = new Scanner(System.in);
      
      System.out.println("1: Spin the wheel");
      System.out.println("2: Buy a vowel");
      System.out.println("3: Solve the puzzle");
      System.out.println("4: Quit");
      System.out.println("9: Test letter input");
      System.out.println("Enter a choice:");
      String input = in.nextLine();
      int msg = Integer.valueOf(input);
      
      switch(msg){
          case 1: input = case1;          
            System.out.println("You've chosen " + case1);
            break;
          case 2: input = case2;
            System.out.println("You've chosen " + case2);
            break;
          case 3: input = case3;
            System.out.println("You've chosen " + case3);
            break;
          case 4: input = case4;
            System.out.println("You've chosen " + case4);
            System.exit(0);
          case 9: input = case9;
            System.out.println("You've chosen " + case9);
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
            break;
      } 
    }
  public static void displayLogo()
  {
      System.out.println("                       ======================");
      System.out.println("                       =  Wheel of Fortune  =");
      System.out.println("                       ======================");
  }
}
    
  
  

