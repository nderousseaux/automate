package model;

import java.util.ArrayList;

/**
 * La classe Automate modèlise les automates qui vont se battre.
 * Ils sont définis par :
 *  - La liste des cellules vivantes
 *  - Sa méthode d'évolution
 *
 */
public class Automate {

    //region Attributs
    private ArrayList<Cellule> _cellules = new ArrayList<>();
    private AlgorithmeEvolution _algoEvolution;
    private String _name;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe Automate.
     *
     * @param algorithmeEvolution Algorithme d'évolution de l'automate.
     * @param name Nom de l'automate
     *
     */
    public Automate(AlgorithmeEvolution algorithmeEvolution, String name){
        _algoEvolution = algorithmeEvolution;
        _name = name;
    }
    //endregion

    //region Getteur/Setteur
    /**
     * Getteur qui renvoie la liste des cellules
     *
     */
    public ArrayList<Cellule> getCellules(){
        return _cellules;
    }

    /**
     * Getteur qui renvoie la cellule de l'automate à la position demandé.
     * La position peut être hors du cadre de la grille, la méthode d'expension sera appelée.
     *
     * @param position Position sur la grille.
     * @param terrain Terrain sur lequel faire la recherche.
     *
     * @return Cellule qui est présente sur la case demandée. Null si il n'y pas de cellule.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "taille" n'est pas de 2.
     *
     */
    private Cellule getCellule(int[] position, Grille terrain){
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }

        Cellule res = null;

        ArrayList<Cellule> cellules = terrain.getCellules(position);
        cellules.removeIf(cellule -> (cellule.getParent() != this));

        if(cellules.size()>0){
            res = cellules.get(0);
        }

        return res;
    }

    /**
     * Getter qui renvoie l'alogrithme d'évolution de l'automate
     *
     */
    public AlgorithmeEvolution getAlgoEvolution() {
        return _algoEvolution;
    }

    /**
     * Getter qui renvoie le nom de l'automate
     *
     */
    public String getName() {
        return _name;
    }

    //endregion

    //region Méthodes d'instances
    /**
     * Méthode qui étend l'automate.
     * La méthode sera appelé à chaque tour.
     *
     */
    public void evolution(){
        _algoEvolution.evolution(this);
    }

    /**
     * Méthode qui renvoie vrai si un cellule vivante de l'automate est présente sur la position pointée.
     * La position peut être hors du cadre de la grille, la méthode d'expension sera appelée.
     *
     * @param position Position sur la grille.
     * @param terrain Terrain sur lequel faire la recherche.
     *
     * @return Booléen : Vrai si il y a une cellule de l'automate à cet endroit.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "taille" n'est pas de 2.
     *
     */
     boolean hasCellule(int[] position, Grille terrain){
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }

        boolean res = false;
        //On récupére la liste des cellules de l'automate sur cette position
        Cellule cellule = getCellule(position, terrain);

        if(cellule != null){
            res = true;
        }
        return res;
    }
    //endregion

}
