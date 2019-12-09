package model;

/**
 * Interface qui régule l'évolution des automates dans sur leur grille
 * Permet l'implémentation du design patern Stratégie.
 *
 */
public interface AlgorithmeEvolution {

    /**
     * Méthode qui définit comment les automates s'étendent.
     *
     * @param automate Automate à etendre
     *
     */
    void evolution(Automate automate);
}
