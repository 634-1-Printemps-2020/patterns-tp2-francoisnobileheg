package materials;

import java.util.Random;

public enum CoinState {

    HEADS, TAILS;

    public static CoinState getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }


}
