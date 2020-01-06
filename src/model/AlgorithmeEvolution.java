package model;

/**
 * Interface qui régule l'évolution des automates sur leur grille
 * Permet l'implémentation du design patern Stratégie.
 *
 */
public interface AlgorithmeEvolution {

    String name = null;


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
