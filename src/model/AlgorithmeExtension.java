package model;

import java.util.ArrayList;

/**
 * Interface qui régule l'ajout des algorithmes d'exentions dans la grille
 * Permet l'implémentation du design patern Stratégie.
 *
 */
public interface AlgorithmeExtension {

    /**
     * Méthode qui renvoie les cellules qui sont présente sur une case de la grille donnée en paramètre.
     * La case peut être hors des limites du terrain, l'algorithme d'extension décidera quoi faire.
     *
     * @param grille Grille sur laquelle faire la recherche de cellule
     * @param position Case de la grille dont on veut connaitre les cellules.
     *
     * @return Liste cellules présente dans la case donnée en paramètre.
     *
     */
    ArrayList<Cellule> getCellules(Grille grille, int[] position);
}
