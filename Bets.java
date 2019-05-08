import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Bets {

    // History of Spins for the roulette wheel;
    // 10 most recent spins are kept, most recent is last in list
    private List<String> spins = new ArrayList<String>();

    // Multiple wagers can be put on any spin;
    // All wagers are cleared with each new spin
    private List<List<String>> wagers = new ArrayList<List<String>>();

    private static final String[] ROW = new String[]{"0","00"};
    private static final String[] TOPLINE = new String[]{"0","00","1","2","3"};
    private static final String[] FIRST_DOZEN = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
    private static final String[] SECOND_DOZEN = new String[]{"13","14","15","16","17","18","19","20","21","22","23","24"};
    private static final String[] THIRD_DOZEN = new String[]{"25","26","27","28","29","30","31","32","33","34","35","36"};
    private static final String[] ODD = new String[]{"1","3","5","7","9","11","13","15","17","19","21","23","25","27","29","31","33","35"};
    private static final String[] EVEN = new String[]{"2","4","6","8","10","12","14","16","18","20","22","24","26","28","30","32","34","36"};
    private static final String[] RED = new String[]{"1","3","5","7","9","12","14","16","18","19","21","23","25","27","30","32","34","36"};
    private static final String[] BLACK = new String[]{"2","4","6","8","10","11","13","15","17","20","22","24","26","28","29","31","33","35"};
    private static final String[] ONE_EIGHTEEN = new String[]{"1","2","3","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
    private static final String[] NINETEEN_36 = new String[]{"19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36"};
    private static final String[] FIRST_COLUMN = new String[]{"3","6","9","12","15","18","21","24","27","30","33","36"};
    private static final String[] SECOND_COLUMN = new String[]{"2","5","8","11","14","17","20","23","26","29","32","35"};
    private static final String[] THIRD_COLUMN = new String[]{"1","4","7","10","13","16","19","22","25","28","31","34"};
    private static final String[][] SIX_LINE = new String[][]{{"1","2","3","4","5","6"},{"4","5","6","7","8","9"},{"7","8","9","10","11","12"},{"10","11","12","13","14","15"},{"13","14","15","16","17","18"},{"16","17","18","19","20","21"},{"19","20","21","22","23","24"},{"22","23","24","26","26","27"},{"25","26","27","28","29","30"},{"28","29","30","31","32","33"},{"31","32","33","34","35","36"}};
    private static final String[][] CORNERS = new String[][]{{"3","6","2","5"},{"2","5","1","4"},{"6","9","5","8"},{"5","8","4","7"},{"9","12","8","11"},{"8","11","7","10"},{"12","15","11","14"},{"11","14","10","13"},{"12","18","14","17"},{"14","17","13","16"},{"18","21","17","20"},{"17","20","16","19"},{"21","24","20","23"},{"20","23","19","22"},{"24","27","23","26"},{"23","26","22","25"},{"27","30","26","29"},{"26","29","25","28"},{"30","33","29","32"},{"29","32","28","31"},{"33","36","32","35"},{"32","35","31","34"}};
    private static final String[] ALL_NUMBERS = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","0","00"};
    private static final String[][] ALL_NUMS = new String[][]{{"1",},{"2",},{"3",},{"4",},{"5",},{"6",},{"7",},{"8",},{"9",},{"10",},{"11",},{"12",},{"13",},{"14",},{"15",},{"16",},{"17",},{"18",},{"19",},{"20",},{"21",},{"22",},{"23",},{"24",},{"25",},{"26",},{"27",},{"28",},{"29",},{"30",},{"31",},{"32",},{"33",},{"34",},{"35",},{"36",},{"0",},{"00",}};
    private static final String[][] WAGER_STYLE = new String[][]{getROW(),getTOPLINE(),getFirstDozen(),getSecondDozen(),getThirdDozen(),getODD(),getEVEN(),getRED(),getBLACK(),getOneEighteen(),getNineteen36(),getFirstColumn(),getSecondColumn(),getThirdColumn(),getSixLine()[0],getSixLine()[1],getSixLine()[2],getSixLine()[3],getSixLine()[4],getSixLine()[5],getSixLine()[6],getSixLine()[7],getSixLine()[8],getSixLine()[9],getSixLine()[10],getCORNERS()[0],getCORNERS()[1],getCORNERS()[2],getCORNERS()[3],getCORNERS()[4],getCORNERS()[5],getCORNERS()[6],getCORNERS()[7],getCORNERS()[8],getCORNERS()[9],getCORNERS()[10],getCORNERS()[11],getCORNERS()[12],getCORNERS()[13],getCORNERS()[14],getCORNERS()[15],getCORNERS()[16],getCORNERS()[17],getCORNERS()[18],getCORNERS()[19],getCORNERS()[20],getCORNERS()[21],getAllNums()[0],getAllNums()[1],getAllNums()[2],getAllNums()[3],getAllNums()[4],getAllNums()[5],getAllNums()[6],getAllNums()[7],getAllNums()[8],getAllNums()[9],getAllNums()[10],getAllNums()[11],getAllNums()[12],getAllNums()[13],getAllNums()[14],getAllNums()[15],getAllNums()[16],getAllNums()[17],getAllNums()[18],getAllNums()[19],getAllNums()[20],getAllNums()[21],getAllNums()[22],getAllNums()[23],getAllNums()[24],getAllNums()[25],getAllNums()[26],getAllNums()[27],getAllNums()[28],getAllNums()[29],getAllNums()[30],getAllNums()[31],getAllNums()[32],getAllNums()[33],getAllNums()[34],getAllNums()[35],getAllNums()[36],getAllNums()[37]};

    private static final int STRAIGHT_UP_ODDS =  35;
    private static final int ZERO_ODDS = 35;
    private static final int ZERO_ZERO_ODDS = 35;
    private static final int ROW_ODDS = 17;
    private static final int TOPLINE_ODDS = 6;
    private static final int FIRST_DOZEN_ODDS = 2;
    private static final int SECOND_DOZEN_ODDS = 2;
    private static final int THIRD_DOZEN_ODDS = 2;
    private static final int ODD_ODDS = 1;
    private static final int EVEN_ODDS = 1;
    private static final int RED_ODDS = 1;
    private static final int BLACK_ODDS = 1;
    private static final int ONE_EIGHTEEN_ODDS = 1;
    private static final int NINETEEN_THIRTYSIX_ODDS = 2;
    private static final int FIRST_COLUMN_ODDS = 2;
    private static final int SECOND_COLUMN_ODDS = 2;
    private static final int THIRD_COLUMN_ODDS = 2;
    private static final int SIX_LINE_ODDS = 5;
    private static final int CORNERS_ODDS = 8;

    public Bets(){}

    public void clearWagers(){
        wagers.clear();
    }

    public void remove_bet(){
        System.out.println("\n\tWhich bet do you want to remove? ");
        Scanner removeNumber = new Scanner(System.in);
        int userRequest = removeNumber.nextInt();
        wagers.remove(userRequest - 1);
    }

    public void display_bets() {
    boolean displayMenu = true;
    while(displayMenu){
        System.out.println("\n\tHere are the bets you can place:");
        select_bet();
        System.out.println("\n\tEnter a number to see details for that bet");
        System.out.println("\tor press 0 (zero) to exit");
        Scanner userWants = new Scanner(System.in);
        int userRequest = userWants.nextInt();
        switch(userRequest) {
            case 1:
                show_row();
                break;
            case 2:
                show_topline();
                break;
            case 3:
                show_first_dozen();
                break;
            case 4:
                show_second_dozen();
                break;
            case 5:
                show_third_dozen();
                break;
            case 6:
                show_odd();
                break;
            case 7:
                show_even();
                break;
            case 8:
                show_red();
                break;
            case 9:
                show_black();
                break;
            case 10:
                show_1_18();
                break;
            case 11:
                show_19_36();
                break;
            case 12:
                show_first_column();
                break;
            case 13:
                show_second_column();
                break;
            case 14:
                show_third_column();
                break;
            case 15:
                show_six_line();
                break;
            case 16:
                show_corners();
                break;
            case 17:
                show_straight_up();
                break;
            case 0:
                displayMenu = false;
                break;
        }
        passNext();
        }
    }

    public void passNext() {
        System.out.printf("\tPress Enter to Continue...");
        Scanner userPass = new Scanner(System.in);
        String userNext = userPass.nextLine();
    }

    public void show_straight_up(){
        System.out.println("\n\tAny single one of these numbers can be a straight-up bet:");
        System.out.println("\t" + Arrays.toString(getAllNumbers()));
        //System.out.println("0,00");
        System.out.printf("\tBet odds for straight-up bet are %d to 1\n", getStraightUpOdds());
        //System.out.println(""Bet odds for straight-up bet are 35 to 1");
    };

    public void show_row(){
        System.out.println("\n\tNumbers included in a row bet are:");
        System.out.println("\t" + Arrays.toString(getROW()));
        //System.out.println("0,00");
        System.out.printf("\tBet odds for row bet are %d to 1\n", getRowOdds());
        //System.out.println(""Bet odds for row bet are 17 to 1");
    };

    public void show_topline(){
        System.out.println("\n\tNumbers included in a row bet are:");
        System.out.println("\t" + Arrays.toString(getTOPLINE()));
        //System.out.println("0,00,1,2,3");
        System.out.printf("\tBet odds for top-line bet are %d to 1\n", getToplineOdds());
        //System.out.println("Bet odds for top-line bet are 6 to 1");
    }

    public void show_first_dozen(){
        System.out.println("\n\tNumbers included in a first-dozen bet are:");
        System.out.println("\t" + Arrays.toString(getFirstDozen()));
        //System.out.println("1,2,3,4,5,6,7,8,9,10,11,12");
        System.out.printf("\tBet odds for first-dozen bet are %d to 1\n", getFirstDozenOdds());
        //System.out.println("Bet odds for first-dozen bet are 2 to 1");
    }

    public void show_second_dozen(){
        System.out.println("\n\tNumbers included in a second-dozen bet are:");
        System.out.println("\t" + Arrays.toString(getSecondDozen()));
        //System.out.println("13,14,15,16,17,18,19,20,21,22,23,24");
        System.out.printf("\tBet odds for second-dozen bet are %d to 1\n", getSecondDozenOdds());
        //System.out.println("Bet odds for second-dozen bet are 2 to 1");
    }

    public void show_third_dozen(){
        System.out.println("\n\tNumbers included in a third-dozen bet are:");
        System.out.println("\t" + Arrays.toString(getThirdDozen()));
        //System.out.println("25,26,27,28,29,30,31,32,33,34,35,36");
        System.out.printf("\tBet odds for third-dozen are %d to 1\n", getThirdDozenOdds());
        //System.out.println("Bet odds for third-dozen bet are 2 to 1");
    }

    public void show_odd(){
        System.out.println("\n\tNumbers included in an odd number bet are:");
        System.out.println("\t" + Arrays.toString(getODD()));
        //System.out.println("1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35");
        System.out.printf("\tBet odds for odd number bet are %d to 1\n", getOddOdds());
        //System.out.println("Bet odds for odd number bet are 1 to 1");
    }

    public void show_even(){
        System.out.println("\n\tNumbers included in an even number bet are:");
        System.out.println("\t" + Arrays.toString(getEVEN()));
        //System.out.println("2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36");
        System.out.printf("\tBet odds for even number bet are %d to 1\n", getEvenOdds());
        //System.out.println("Bet odds for even number bet are 1 to 1");
    }

    public void show_red(){
        System.out.println("\n\tNumbers included in a red-slot bet are:");
        System.out.println("\t" + Arrays.toString(getRED()));
        //System.out.println("1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36");
        System.out.printf("\tBet odds for red-slot bet are %d to 1\n", getRedOdds());
        //System.out.println("Bet odds for red-slot bet are 1 to 1");
    }

    public void show_black(){
        System.out.println("\n\tNumbers included in a black-slot bet are:");
        System.out.println("\t" + Arrays.toString(getBLACK()));
        //System.out.println("2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35");
        System.out.printf("\tBet odds for black-slot bet are %d to 1\n", getBlackOdds());
        //System.out.println("Bet odds for black-slot bet are 1 to 1");
    }

    public void show_1_18(){
        System.out.println("\n\tNumbers included in a 1 - 18 bet are:");
        System.out.println("\t" + Arrays.toString(getOneEighteen()));
        //System.out.println("1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18");
        System.out.printf("\tBet odds for 1 - 18 bet are %d to 1\n", getOneEighteenOdds());
        //System.out.println("Bet odds for 1 - 18 bet are 1 to 1");
    }

    public void show_19_36(){
        System.out.println("\n\tNumbers included in a 19 - 36 bet are:");
        System.out.println("\t" + Arrays.toString(getNineteen36()));
        //System.out.println("19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36");
        System.out.printf("\tBet odds for 19 - 36 bet are %d to 1\n", getNineteenThirtysixOdds());
        //System.out.println("Bet odds for 19 - 36 bet are 1 to 1");
    }

    public void show_first_column(){
        System.out.println("\n\tNumbers included in a first column bet are:");
        System.out.println("\t" + Arrays.toString(getFirstColumn()));
        //System.out.println("1,4,7,10,13,16,19,22,25,28,31,34");
        System.out.printf("\tBet odds for first column bet are %d to 1\n", getFirstColumnOdds());
        //System.out.println("Bet odds for first column bet are 2 to 1");
    }

    public void show_second_column(){
        System.out.println("\n\tNumbers included in a second column bet are: 0,00");
        System.out.println("\t" + Arrays.toString(getSecondColumn()));
        //System.out.println("2,5,8,11,14,17,20,23,26,29,32,35");
        System.out.printf("\tBet odds for second column bet are %d to 1\n", getSecondColumnOdds());
        //System.out.println("Bet odds for second column bet are 2 to 1");
    }

    public void show_third_column(){
        System.out.println("\n\tNumbers included in a third column bet are: 0,00");
        System.out.println("\t" + Arrays.toString(getThirdColumn()));
        //System.out.println("3,6,9,12,15,18,21,24,27,30,33,36");
        System.out.printf("\tBet odds for third column bet are %d to 1\n", getThirdColumnOdds());
        //System.out.println("Bet odds for third column bet are 2 to 1");
    }

    public void show_six_line(){
        System.out.println("\n\tNumbers included in a six-line bet can be:");
        six_line_bets();
        //System.out.println("Bet 1 = [1, 2, 3, 4, 5, 6]		Bet 2 = [4, 5, 6, 7, 8, 9]")
        //System.out.println("Bet 3 = [7, 8, 9, 10, 11, 12]		Bet 4 = [10, 11, 12, 13, 14, 15]")
        //System.out.println("Bet 5 = [13, 14, 15, 16, 17, 18]	Bet 6 = [16, 17, 18, 19, 20, 21]")
        //System.out.println("Bet 7 = [19, 20, 21, 22, 23, 24]	Bet 8 = [22, 23, 24, 26, 26, 27]")
        //System.out.println("Bet 9 = [25, 26, 27, 28, 29, 30]	Bet 10 = [28, 29, 30, 31, 32, 33]")
        //System.out.println("Bet 11 = [31, 32, 33, 34, 35, 36]")
        System.out.printf("\n\tBet odds for six-line bet are %d to 1\n", getSixLineOdds());
        //System.out.println("Bet odds for a six-line bet are 5 to 1");
    }

    public void show_corners(){
        System.out.println("\n\tNumbers included in a four-corner bet can be:");
        four_corner_bets();
        //System.out.println("1 =  [3, 6, 2, 5]		2 = [2, 5, 1, 4]
        //System.out.println("3 =  [6, 9, 5, 8]		4 = [5, 8, 4, 7]
        //System.out.println("5 =  [9, 12, 8, 11]	6 = [8, 11, 7, 10]
        //System.out.println("7 =  [12, 15, 11, 14]	8 = [11, 14, 10, 13]
        //System.out.println("9 =  [12, 18, 14, 17]	10 = [14, 17, 13, 16]
        //System.out.println("11 = [18, 21, 17, 20]	12 = [17, 20, 16, 19]
        //System.out.println("13 = [21, 24, 20, 23]	14 = [20, 23, 19, 22]
        //System.out.println("15 = [24, 27, 23, 26]	16 = [23, 26, 22, 25]
        //System.out.println("17 = [27, 30, 26, 29]	18 = [26, 29, 25, 28]
        //System.out.println("19 = [30, 33, 29, 32]	20 = [29, 32, 28, 31]
        //System.out.println("21 = [33, 36, 32, 35]	22 = [32, 35, 31, 34]
        System.out.printf("\tBet odds for four-corner bet are %d to 1\n", getCornersOdds());
        //System.out.println("Bet odds for four-corner bet are 17 to 1");
    }

    public static String[][] getWagerStyle() {
        return WAGER_STYLE;
    }

    public static String[] getBLACK() {
        return BLACK;
    }

    public static String[] getAllNumbers() {
        return ALL_NUMBERS;
    }

    public static String[][] getAllNums() {
        return ALL_NUMS;
    }

    public static String[] getEVEN() {
        return EVEN;
    }

    public static String[] getFirstColumn() {
        return FIRST_COLUMN;
    }

    public static String[] getFirstDozen() {
        return FIRST_DOZEN;
    }

    public static String[] getNineteen36() {
        return NINETEEN_36;
    }

    public static String[] getODD() {
        return ODD;
    }

    public static String[] getOneEighteen() {
        return ONE_EIGHTEEN;
    }

    public static String[] getRED() {
        return RED;
    }

    public static String[] getROW() {
        return ROW;
    }

    public static String[] getSecondColumn() {
        return SECOND_COLUMN;
    }

    public static String[] getSecondDozen() {
        return SECOND_DOZEN;
    }

    public static String[] getThirdColumn() {
        return THIRD_COLUMN;
    }

    public static String[] getThirdDozen() {
        return THIRD_DOZEN;
    }

    public static String[] getTOPLINE() {
        return TOPLINE;
    }

    public static String[][] getCORNERS() {
        return CORNERS;
    }

    public static String[][] getSixLine() {
        return SIX_LINE;
    }

    public static int getStraightUpOdds() {
        return STRAIGHT_UP_ODDS;
    }

    public static int getZeroOdds() {
        return ZERO_ODDS;
    }

    public static int getZeroZeroOdds() {
        return ZERO_ZERO_ODDS;
    }

    public static int getRowOdds() {
        return ROW_ODDS;
    }

    public static int getToplineOdds() {
        return TOPLINE_ODDS;
    }

    public static int getFirstDozenOdds() {
        return FIRST_DOZEN_ODDS;
    }

    public static int getSecondDozenOdds() {
        return SECOND_DOZEN_ODDS;
    }

    public static int getThirdDozenOdds() {
        return THIRD_DOZEN_ODDS;
    }

    public static int getOddOdds() {
        return ODD_ODDS;
    }

    public static int getEvenOdds() {
        return EVEN_ODDS;
    }

    public static int getRedOdds() {
        return RED_ODDS;
    }

    public static int getBlackOdds() {
        return BLACK_ODDS;
    }

    public static int getOneEighteenOdds() {
        return ONE_EIGHTEEN_ODDS;
    }

    public static int getNineteenThirtysixOdds() {
        return NINETEEN_THIRTYSIX_ODDS;
    }

    public static int getFirstColumnOdds() {
        return FIRST_COLUMN_ODDS;
    }

    public static int getSecondColumnOdds() {
        return SECOND_COLUMN_ODDS;
    }

    public static int getThirdColumnOdds() {
        return THIRD_COLUMN_ODDS;
    }

    public static int getSixLineOdds() {
        return SIX_LINE_ODDS;
    }

    public static int getCornersOdds() {
        return CORNERS_ODDS;
    }

    public String spinWheel(){
        // Set up Secure Random
        SecureRandom randomNumbers = new SecureRandom();
        System.out.println("\tNo more Bets!!");
        System.out.println("\t===============");
        System.out.println("\t...the roulette wheel is in motion...");
        // timer in try catch to sleep for five seconds
        for (int x = 0; x<5; x++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\t.....");
            // random number is generated, and connected to all numbers
        }
        int dropIndex = randomNumbers.nextInt(getAllNumbers().length);
        String thisSpin = getAllNumbers()[dropIndex];
        System.out.printf("\n\tResult of this wheel spin is: %s\n", thisSpin);
        updateSpins(thisSpin);
        return thisSpin;
    }


    public List<List<String>> getWagers() {
        return this.wagers;
    }

    public void showWagers(){
        System.out.println("\n\tWagers for upcoming Spin");
        int wagersSoFar = getWagers().size();
        for (int x = 0; x < wagersSoFar; x++) {
            //System.out.printf(getWagers()[x].toString());
            List<String> wagerAmount = getWagers().get(x);
            String wageAmount = wagerAmount.get(0).toString();
            String wageString = wagerAmount.get(1).toString();
            String wageOdds = wagerAmount.get(2).toString();
            // calculate payout
            Integer payAmount = Integer.valueOf(wageAmount);
            Integer payOdds = Integer.valueOf(wageOdds);
            Integer wagePayout = (payAmount * payOdds);
            // get numbers string to show user what numbers are active
            Integer getNumbers = Integer.valueOf(wageString);
            // display results
            System.out.printf("\n\t$%s will pay $%d,", wageAmount, wagePayout);
            System.out.printf("\n\tif any of these numbers are rolled: ");
            System.out.printf("\n\t" + Arrays.toString(WAGER_STYLE[getNumbers]) + "\n");
        }
    }

    public void updateWagers(String wagerAmount, String wagerStyle, String wagerOdds) {
        // if less than 10 spins so far, add next spin to array
        List<String> aWager = new ArrayList<String>();
        aWager.add(wagerAmount);
        aWager.add(wagerStyle);
        aWager.add(wagerOdds);
        this.wagers.add(aWager);
     }

    public List<String> getSpins() {
        return spins;
    }

    public void showSpins(){
        System.out.println("\n\tMost Recent Spins:");
        System.out.println("\t" + getSpins().toString());
    }

    public void updateSpins(String spin) {
        int spinsSoFar = getSpins().size();
        // if less than 10 spins so far, add next spin to array
        if (spinsSoFar < 10){
            this.spins.add(spin);
        } else {
            // 10 spins are already recorded, pop the first element
            this.spins.remove(0); // removes the first item
            // add the new spin as the last element to the list
            this.spins.add(spin);
        } }

    public void select_bet(){
        System.out.println("\t1) Row           10) One - Eighteen");
        System.out.println("\t2) Top-line      11) Nineteen - Thirty-Six");
        System.out.println("\t3) First Dozen   12) First Column");
        System.out.println("\t4) Second Dozen  13) Second Column");
        System.out.println("\t5) Third Dozen   14) Third Column");
        System.out.println("\t6) Odd           15) Six Line");
        System.out.println("\t7) Even          16) Corners");
        System.out.println("\t8) Red           17) Exact Number/Straight-Up");
        System.out.println("\t9) Black");
        }

    public int getWagerOdds(int wagerStyle){
        int oddsAre = 0;
        switch(wagerStyle) {
            case 1:
                oddsAre = getRowOdds();
                break;
            case 2:
                oddsAre = getToplineOdds();
                break;
            case 3:
                oddsAre = getFirstDozenOdds();
                break;
            case 4:
                oddsAre = getSecondDozenOdds();
                break;
            case 5:
                oddsAre = getThirdDozenOdds();
                break;
            case 6:
                oddsAre = getOddOdds();
                break;
            case 7:
                oddsAre = getEvenOdds();
                break;
            case 8:
                oddsAre = getRedOdds();
                break;
            case 9:
                oddsAre = getBlackOdds();
                break;
            case 10:
                oddsAre = getOneEighteenOdds();
                break;
            case 11:
                oddsAre = getNineteenThirtysixOdds();
                break;
            case 12:
                oddsAre = getFirstColumnOdds();
                break;
            case 13:
                oddsAre = getSecondColumnOdds();
                break;
            case 14:
                oddsAre = getThirdColumnOdds();
                break;
            case 15:
                oddsAre = getSixLineOdds();
                break;
            case 16:
                oddsAre = getCornersOdds();
                break;
            case 17:
                oddsAre = getStraightUpOdds();
                break;
            case 0:
                //displayMenu = false;
                break;
        }
    return oddsAre;
    }

    public void six_line_bets() {
        for (int i = 0; i < getSixLine().length; i += 2) {
            System.out.printf("\tBet %d = ", i + 1);
            System.out.print(Arrays.toString(getSixLine()[i]));
            if (i < 2) {
                System.out.printf("\t\t\t\tBet %d = ", i + 2);
                System.out.println(Arrays.toString(getSixLine()[i + 1]));}
            if (i >= 2 && i < 4) {
                System.out.printf("\t\t\tBet %d = ", i + 2);
                System.out.println(Arrays.toString(getSixLine()[i + 1]));}
            if (i >= 4 && i < 10) {
                System.out.printf("\t\tBet %d = ", i + 2);
                System.out.println(Arrays.toString(getSixLine()[i + 1]));
                }
            }
       }

    public void four_corner_bets() {
        for (int i = 0; i < getCORNERS().length; i+=2) {
            System.out.printf("\t%d = ", i + 1);
            System.out.print(Arrays.toString(getCORNERS()[i]));
            if (i < 6) {
                System.out.printf("\t\t%d = ", i + 2);
                System.out.println(Arrays.toString(getCORNERS()[i + 1]));}
            if (i >= 6) {
                System.out.printf("\t%d = ", i + 2);
                System.out.println(Arrays.toString(getCORNERS()[i + 1]));}
        }
    }


    }