import java.util.Random;
import java.util.Scanner;

class Main {
  int money = 50;
  int meat = 0;
  int earned = 0;
  int packs = 0;
  int score = 0;
  int turn = 0;
  int police = 0;
  int bribe = 0;
  boolean end = false;
  Random rand = new Random();
  Scanner input = new Scanner(System.in);
  // Star: Destroy stars to get random amounts of meat
  // Once you have 10 meat, you can turn it into one sellable package
  // Sell the meat to get money, allowing you to destroy more stars
  // Bribe the police, or they'll confiscate all your meat!
  // Accepting sponsorships gives you money, but future sales earn you less!
  // Keep your economy going as long as possible!
  public void destroyStar() {
    if (money >= 10){
    money -= 10;
    score += 5;
    System.out.println("BOOM! Star destroyed!");
    System.out.println("-$10");
    earned = rand.nextInt(3, 8);
    System.out.println("+"+earned+" meat");
    meat += earned;
    earned = 0;
    }
    else {System.out.println("You can't afford to do that!");}
  }

  public void make() {
    if (meat < 10) {
      System.out.println("You don't have enough meat! You need 10 meat to make a package.");    
  }
    else {
      System.out.print("Number of packs to make: ");
      int answer = input.nextInt();
      if (answer <= meat/10) {
        System.out.println("You have made "+answer+" packs!");
        packs += answer;
        score += 5;
        meat -= answer*10;
      }
      else {
        System.out.println("You don't have enough meat to make that many!");
        System.out.println("We'll just make the most we can...");
        answer = meat/10;
        System.out.println("You have made "+answer+" packs!");
        packs += answer;
        score += 5;
        meat -= answer*10;
      }
    }
  }
  public void sell(int low) {
    if (packs <= 0) {
      System.out.println("You don't have any packages to sell!");
    
    } else {
      earned = packs * rand.nextInt(10-low/2, 30-low);
      System.out.println("All your packs have been sold!");
      System.out.println("You earned $"+earned+"!");
      money += earned;
      packs = 0;
      score += 10;
      earned = 0;
    }
  }
  public void giveUp() {
    end = true;
    System.out.println("GAME OVER!");
    score *= 10;
    System.out.println("Your score: "+score);
    System.out.println("Thank you for playing this capitalism commentary game!");
  }
  public static void main(String[] args) {
    int turn = 0;
    int decr = 0;
    System.out.print("\033[H\033[2J");
    System.out.flush();
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to Star Meat Catcher Wrapper Deluxe!");
    System.out.println("Blow up Stars to get meat! Package the meat and sell it!");
    System.out.println("Commands: 'shoot', 'make', 'sell', 'giveup'");
    System.out.println("For Police: 'pay', For Sponsor: 'accept'");
    System.out.println("Shooting starts costs $10 and gives you a random amount of meat!");
    System.out.println("Making packages costs $10! Packages can be sold for a random amount of money!");
    Main game = new Main();
    while (!game.end) {
      System.out.println("\n\n\n");
      System.out.println("You have $"+game.money+".");
      System.out.println("You have "+game.meat+" meat.");
      System.out.println("You have "+game.packs+" packages.");
      boolean wait = true;
      while (wait) {
      System.out.print("What would you like to do? Input: ");
      String user = input.nextLine();
      System.out.println();
      if (user.equals("shoot")) {
        game.destroyStar();
        wait = false;
      }
      else if (user.equals("make")){
        game.make();
        wait = false;
      }
      else if (user.equals("sell")){
        game.sell(decr);
        wait = false;
      }
      else if (user.equals("giveup")){
        game.giveUp();
        wait = false;
        }
      else {System.out.println("ERROR - INVALID COMMAND");}
      }
      if (turn >= 8 && game.money >= 10) 
      {
        game.police = game.rand.nextInt(0, 5);
        if (game.police == 0) 
        {
          int bribe = game.rand.nextInt(game.money/2, game.money);
          System.out.println("The policeman is here!");
          System.out.println("Pay his bribe or you'll lose your meat!");
          System.out.println("Bribe: "+bribe);
          System.out.print("What will you do: ");
          String resp = input.nextLine();
          if (resp.equals("pay")) 
          {
            game.money -= bribe;
            System.out.println("The officer has left... for now...");
          }
          else 
          {
            game.meat = 0;
            System.out.println("Your meat was confiscated!");
          }
          turn = 2;
        }
      }
      if (turn >= 12 && decr <= 10) 
      {
        int spons = game.rand.nextInt(0, 3);
        if (spons == 0) 
        {
          int offer = game.rand.nextInt(30, 80);
          System.out.println("You've got a sponsership!");
          System.out.println("Take it to get money, but future sales won't give you as much!");
          System.out.println("Offer: "+offer);
          System.out.print("What will you do: ");
          String resp = input.nextLine();
          if (resp.equals("accept")) 
          {
            game.money += offer;
            decr += game.rand.nextInt(1, 5);
            if (decr > 10) {decr = 10;}
            System.out.println("You accepted!");
          }
          else 
          {
            System.out.println("You refused.");
          }
          turn = 0;
        }
      }
      turn++;
    }
  }
}