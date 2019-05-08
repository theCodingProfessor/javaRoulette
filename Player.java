public class Player {


    private Integer bank; // Money Player has, starts with $100.00
    private String name; // Player's Name (i.e., Johnny Cash" )

    public Player(String name, Integer bank) {
        this.name = name;
        this.bank = bank;
        System.out.printf("\n\tWelcome %s you have $%d.00 in your bank\n", name, bank);
    }

    public String getName() {
        return name;
    }

    public Integer getBank() {
        return bank;
    }

    public void checkBank() {
        System.out.printf("\n\tYou have $%d.00 in your bank\n", bank);
    }

    public void payPlayer(Integer payout) {
        this.bank = bank + payout;
    }

    public boolean checkBetAgainstBank(Integer bet) {
        boolean result = false;
        if (this.bank >= bet)
            result = true;
        return result;
    }

    public void takeBet(Integer bet) {
        this.bank = bank - bet;
    }

}