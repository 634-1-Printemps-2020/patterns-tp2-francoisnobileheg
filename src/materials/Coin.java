package materials;

import java.util.Objects;

public class Coin {

    private CoinState coinState;

    private static Coin instance = null;

    private Coin() {
    }

    public static Coin getInstance() {
        if (instance == null) {
            instance = new Coin();
        }
        return instance;
    }

    /**
     * Change l'état de la pièce.
     * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
     */
    public void throwCoin() {
        coinState = CoinState.getRandom();
    }

    public CoinState getState() {
        return coinState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return coinState == coin.coinState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinState);
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coinState=" + coinState +
                '}';
    }
}
