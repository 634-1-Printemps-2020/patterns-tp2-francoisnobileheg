package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
        coin = Coin.getInstance();
        rules = Rules.getInstance();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
        history.put(player, new ArrayList<>());
    }

    /**
     * Faire jouer tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
        for (Player p : history.keySet()) {
            // tant que le joueur n'a pas gagné
            while (!rules.checkWin(history.get(p))) {
                // lancer la pièce
                p.play(coin);
                // récupère la liste du joueur
                List<CoinState> lstc = history.get(p);
                // ajoute le lancer du joueur
                lstc.add(coin.getState());
                //history.put(p, history.get(p).add(coin.getState()));
                // met la liste de lancer du joueur à jour
                history.put(p, lstc);
            }
        }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
        // instanciation à une très grande valeur pour le test suivant
        int fewerMovesToWin = 1000000000;
        int mostMovesToWin = 0;
        int totalNumberMoves = 0;


        for (Player p : history.keySet()) {
            totalNumberMoves += history.get(p).size();
            if (history.get(p).size() < fewerMovesToWin) {
                fewerMovesToWin = history.get(p).size();
            }
            if (history.get(p).size() > mostMovesToWin) {
                mostMovesToWin = history.get(p).size();
            }

        }

        float averageToWin = (float) totalNumberMoves / history.keySet().size();


        Statistics stat = new Statistics(averageToWin, fewerMovesToWin, mostMovesToWin, totalNumberMoves);
        return stat;
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
        return history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
        if (history.containsKey(player)) {
            return history.get(player);
        }
        return null;
    }
}
