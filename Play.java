import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Play {

    public static void main(String[] args) {

        String name;
        System.out.println("\tWelcome to Roulette.");

        // Initialize the House
        House TheHouse;
        TheHouse = new House(100000);

        // Initialize the Player
        System.out.println("\n\n\tPlease enter your name.");
        Scanner ansr = new Scanner(System.in);
        String userName = ansr.nextLine();
        Player P1;
        P1 = new Player(userName, 100);

        // Initialize a Betting Object
        Bets Bet;
        Bet = new Bets();

        // Start Main Menu Loop
        boolean play = true;
        while (play) {
            System.out.println("\n\tMain Menu: ");
            System.out.println("\t===========");
            System.out.println("\tEnter 1)  Spin the Wheel ");
            System.out.println("\tEnter 2)  See Betting Options ");
            System.out.println("\tEnter 3)  Place a Bet ");
            System.out.println("\tEnter 4)  Check your Bank");
            System.out.println("\tEnter 5)  See Last 10 Wheel Results");
            System.out.println("\n\t\t\tEnter 0 to Exit");
            Scanner userSays = new Scanner(System.in);
            int userRequest = userSays.nextInt();
            switch (userRequest) {
                case 1:
                    String thisSpin = Bet.spinWheel();
                    payPlayer(Bet, thisSpin, P1, TheHouse);
                    break;
                case 2:
                    Bet.display_bets();
                    break;
                case 3:
                    handleWagers(TheHouse, Bet, P1);
                    break;
                case 4:
                    P1.checkBank();
                    break;
                case 5:
                    Bet.showSpins();
                    break;
                case 0:
                    play = false;
                    break;
            }
        }
    }

    public static void handleWagers(House TheHouse, Bets Bet, Player P1){
    boolean moreBets = true;
    while (moreBets) {
        System.out.println("\n\tPlace Your Bets");
        System.out.println("\t===============");
        System.out.println("\tEnter 1)  Make a Wager");
        System.out.println("\tEnter 2)  View All Wagers");
        System.out.println("\tEnter 3)  Remove a Wager");
        System.out.println("\t===============");
        System.out.println("\tEnter 0 to exit");
        Scanner userChoice = new Scanner(System.in);
        int betChoice = userChoice.nextInt();
        switch (betChoice) {
            case 1:
                place_bet(TheHouse, Bet, P1);
                break;
            case 2:
                Bet.showWagers();
                break;
            case 3:
                Bet.remove_bet();
                break;
            case 0:
                moreBets = false;
                break;
            }
        }
    }

    public static void place_bet(House TheHouse, Bets Bet, Player P1) {
        System.out.println("\n\tHow much will your wager be? ");
        Integer wagerLength = Bet.getWagerStyle().length;
        //System.out.printf("\n\tsize of array is: %d", wagerLength);
        Integer wagerSpot = -1;
        Scanner waitForEnter = new Scanner(System.in);
        // get input from the user
        Scanner userWager = new Scanner(System.in);
        Integer thisAmount = userWager.nextInt();
        // check to see if the user has enough money
        if (P1.checkBetAgainstBank(thisAmount) == false) {
            System.out.println("\n\tSorry you don't have enough money for that.");
            System.out.println("\n\tPress enter to continue...");
            String goReturn = waitForEnter.nextLine();
            // end function if user provides invalid input
            return;
        }
        // get type of bet from the user
        System.out.println("\n\tWhat will you wager on?");
        Bet.select_bet();
        Integer lineBet = -1;
        Integer cornerBet = -1;
        System.out.println("\n\tEnter 0 to cancel your bet");
        Scanner userStyle = new Scanner(System.in);
        Integer wagerStyle = userStyle.nextInt();
        // allow user to bail on the bet
        if (wagerStyle == 0) {
            return;
        }
        // get odds on style of bet user selected
        Integer wagerOdds = Bet.getWagerOdds(wagerStyle);
        // check bet against House ability to pay
        if (TheHouse.checkBetAgainstHouse(thisAmount, wagerOdds) == false) {
            System.out.println("\n\tSorry the house can't cover that wager.");
            System.out.println("\n\tPress enter to continue...");
            String goReturn = waitForEnter.nextLine();
            // end function if user provides invalid input
            return;
        }
        if (wagerStyle < 15){
            wagerSpot = wagerStyle - 1; } // goes from spot 0-13 } or 14 spots
        // if wager was six-lines, ask which one they want
        if (wagerStyle == 15) {
            System.out.println("\n\tThere are multiple options for six-lines.");
            Bet.six_line_bets();
            // get six line preference
            System.out.println("\tPlease pick one of them. ");
            Scanner lineStyle = new Scanner(System.in);
            lineBet = lineStyle.nextInt();
            if (lineBet >= 1 && lineBet <= 11){
                System.out.println("\tYou selected Line-bet style:");
                System.out.println("\t" + Arrays.toString(Bet.getSixLine()[lineBet - 1]));
                wagerSpot = 14 + (lineBet - 1); // goes from spot 13 spot 24 or 11 spots
            } else {
                // end function if user provides invalid input
                return;
            }
        }
        // if wager was corners ask which one they want
        if (wagerStyle == 16) {
            System.out.println("\n\tThere are multiple options for corners.");
            Bet.four_corner_bets();
            System.out.println("\tPlease pick one of them. ");
            // get corner preference
            Scanner cornerStyle = new Scanner(System.in);
            cornerBet = cornerStyle.nextInt();
            if (cornerBet >= 1 && cornerBet <= 22) {
                System.out.println("\tYou selected Corner-bet style:");
                System.out.println("\t" + Arrays.toString(Bet.getCORNERS()[cornerBet - 1]));
                wagerSpot = 25 + (cornerBet -1); // goes from spot 25 spot 46 or 22 spots
            } else {
                // end function if user provides invalid input
                return;
            }
        }
        String straightUp = "";
        Integer straightUpInt = 0;
        if (wagerStyle == 17) {
            System.out.println("\n\tThere are multiple options for straight up bets.");
            Bet.show_straight_up();
            System.out.println("\tPlease pick one of them. ");
            // get exact Number
            Scanner exactNumber = new Scanner(System.in);
            straightUp = exactNumber.nextLine();
            straightUpInt = Integer.valueOf(straightUp);
            System.out.printf("\tYou selected a straight up bet of %s", straightUp);
            // end function if user provides invalid input
            if (Integer.valueOf(straightUp) > 0 && Integer.valueOf(straightUp) <= 36) {
                wagerSpot = 47 + (straightUpInt - 1);  // goes from spot 47 spot 83 or 36 spots
            } else if (straightUp.equals("0")) {
                wagerSpot = 83;
            } else if (straightUp.equals("00")) {
                wagerSpot = 84;
            } else {
                return;
            }
        }

        // betAmount and betStyle are determined
        // update the wagerStyle variable
        // offset simple wagers to correspond to zero-based index
        // add this bet to the wagers list
        String wagerOddString = wagerOdds.toString();
        Bet.updateWagers(thisAmount.toString(), wagerSpot.toString(), wagerOdds.toString());
        }

    public static void payPlayer(Bets Bet, String spin, Player P1, House TheHouse){
        System.out.println("\n\tChecking for payouts...");
        Integer thisSpin = Integer.valueOf(spin);
        List<List<String>> allWagers = Bet.getWagers();
        int wageMax = allWagers.size();
        for (int x = 0; x < wageMax; x++) {
            List<String> wagedNumbers = allWagers.get(x);
            String wageNumbers = wagedNumbers.get(1);
            Integer getNumbers = Integer.valueOf(wageNumbers);
            String theseNumbers = Arrays.toString(Bet.getWagerStyle()[getNumbers]);
            String wageAmount = wagedNumbers.get(0);
            Integer payBase = Integer.valueOf(wageAmount);
            // what were the odds
            String wageString = wagedNumbers.get(2);
            Integer payMultiple = Integer.valueOf(wageString);
            // how much did the player win
            Integer wagePayout = (payBase * payMultiple);
            boolean contains = false;
            if (theseNumbers.contains(spin)) {
              // spin was a winner
            System.out.printf("\n\tWe have a Winner...!");
            // make a dramatic pause
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            // pay the player
            // how much did the player wager
            P1.payPlayer(wagePayout);
            TheHouse.payPlayer(wagePayout);
            System.out.printf("\tPlayer wins $%d.00", wagePayout);
            } else {
                System.out.printf("\tHouse takes bet %d for $%d.00", (x + 1),payBase);
                TheHouse.takeBetFromPlayer(payBase);
                P1.takeBet(payBase);
            }
       }
    Bet.clearWagers();
    }

}

