import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.security.SecureRandom;

public class House {

        // Money House Starts with = $100,000
        private Integer house_bank; //

        public House(Integer house_bank){
            this.house_bank = house_bank;
            System.out.printf("\tThe House Welcomes you.");
        }

        public Integer getHouse_bank() {
            return house_bank;
        }

        public void takeBetFromPlayer(Integer bet) {
                this.house_bank = this.house_bank + bet;
        }

        public boolean checkBetAgainstHouse(Integer bet, int betOdds) {
            boolean result = false;
            if (this.house_bank >= (bet * betOdds))
                result = true;
            return result;
        }

        public void payPlayer(Integer winnings) {
            this.house_bank = this.house_bank - winnings;
        }

    }