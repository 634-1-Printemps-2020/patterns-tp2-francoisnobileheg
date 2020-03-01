package materials;

public class Coin {

    private CoinState coinState;

    private static Coin instance = null;

    private Coin() {
    }

    private Coin getInstance() {
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
}
