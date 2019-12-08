package model;

import java.util.ArrayList;

/**
 * Classe qui permet de définir quelle cellule renvoyer si on demande une position en dehors de la grille.
 * Est une implémentation du pattern Stratégie
 *
 */
public class AlgorithmeExtensionRepetition implements AlgorithmeExtension {


    /**
     * Méthode qui renvoie les cellules qui sont présente sur une case de la grille donnée en paramètre.
     * La case peut être hors des limites du terrain, l'algorithme d'extension décidera quoi faire.
     *
     * @param grille Grille sur laquelle faire la recherche de cellule
     * @param position Case de la grille dont on veut connaitre les cellules.
     *
     * @return Liste cellules présente dans la case donnée en paramètre.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "position" n'est pas de 2.
     *
     */
    public  ArrayList<Cellule> getCellules(Grille grille, int[] position) {
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }

        ArrayList<Cellule> listeDesCellules = new ArrayList<>();

        //Si la position sort du cadre, on donne la dernière cellule en bordure du cadre
        if(position[0]<0){
            position[0] = 0;
        }
        else if(position[0] >= grille.getTaille()[0]){
            position[0]= grille.getTaille()[0]-1;
        }
        if(position[1]<0){
            position[1] = 0;
        }
        else if(position[1] >= grille.getTaille()[1]){
            position[1]= grille.getTaille()[1]-1;
        }

        //Sinon, on renvoie simplement les cellules sur la position
        listeDesCellules.addAll(getCellulesSansExtension(grille, position));

        return listeDesCellules;
    }

    /**
     * Méthode qui renvoie les cellules qui sont présente sur une case de la grille donnée en paramètre.
     *
     * @param grille Grille sur laquelle faire la recherche de cellule
     * @param position Case de la grille dont on veut connaitre les cellules.
     *
     * @return Liste cellules présente dans la case donnée en paramètre.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "position" n'est pas de 2.
     * @throws IndexOutOfBoundsException Si la taille est négative ou sort des bornes de la grille
     *
     */
    public ArrayList<Cellule> getCellulesSansExtension(Grille grille, int[] position){
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }
        else if(position[0]<0 || position[0]>= grille.getTaille()[0] || position[1]<0 || position[1]>= grille.getTaille()[1]){
            throw new IndexOutOfBoundsException("La position indiquée sort des bornes de la grille");
        }

        //On renvoie toutes les cellules à cette position
        ArrayList<Cellule> cellulesALaPosition = (ArrayList<Cellule>) grille.getListeCellules().clone();
        cellulesALaPosition.removeIf(cellule -> (cellule._position[0] != position[0] || cellule._position[1] != position[1]));

        return cellulesALaPosition;
    }
}