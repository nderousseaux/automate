package model;

/**
 * Interface qui régule l'évolution des automates sur leur grille
 * Permet l'implémentation du design patern Stratégie.
 *
 */
public interface AlgorithmeEvolution {

    /**
     * Méthode du design patern singleton, qui permet de prendre une seule instance de la classe AlgorithmeEvolution
     *
     *
     * @return Nouvelle instance de l'algorithme d'évolution associé à l'automate.
     *
     * @throws ExceptionInInitializerError Renvoyé si l'algorithme à déjà été associé à l'automate.
     *
     */
    static AlgorithmeEvolution getInstance() {
        return null;
    }

    /**
     * Méthode qui définit comment les automates s'étendent.
     *
     */
    void evolution(Automate automate);

    /**
     * Getter du nom de l'algorithme d'évolution
     *
     */
    String getName();
}
