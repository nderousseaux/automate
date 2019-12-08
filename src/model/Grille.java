package model;

import java.util.ArrayList;


/**
 * La classe Grille modèlise le terrain sur lequels vont se battre les automates
 * Elle est définie par :
 *  - Sa taille (tableau de int[x,y]
 *  - La liste des cellules vivantes sur le terrain
 *  - Sa méthode d'extension
 *
 */
public class Grille {

    //region Attributs
    private int[] _taille;
    private ArrayList<Cellule> _listeDesCellules;
    private MethodeExtension _methodeExtension;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe Grille.
     *
     * @param taille Taille de la grille
     * @param listeDesCellules Liste de cellules vivantes sur la grille
     * @param methodeExtension La méthode d'extension de la grille
     *
     * @throws IllegalArgumentException Si la longueur du tableau "taille" n'est pas de 2.
     * @throws IndexOutOfBoundsException Si la taille est négative
     */
    public Grille(int[] taille, ArrayList<Cellule> listeDesCellules, MethodeExtension methodeExtension){
        if(taille.length != 2){
            throw new IllegalArgumentException("La grille n'est pas de dimension deux.");
        }
        else if(taille[0] < 0 || taille[1]<1){
            throw new IllegalArgumentException("La taille indiquée est inférieure à zero.");
        }

        _taille = taille;
        _listeDesCellules = listeDesCellules;
        _methodeExtension = methodeExtension;
    }
    //endregion

    //region Getteurs/Setteurs
    /**
     * Getteur qui renvoie la taille de la grille.
     *
     * @return Taille de la grille.
     *
     */
    int[] getTaille() {
        return this._taille;
    }

    /**
     * Getteur qui renvoie toutes les cellules vivantes de la grille
     *
     * @return Liste des cellules vivantes de la grille.
     *
     */
    ArrayList<Cellule> getListeCellules() {
        return this._listeDesCellules;
    }
    //endregion

    //region Méthodes d'instances
    /**
     * Méthode qui renvoie une liste d'automates qui ont encore des cellules sur le terrain.
     *
     * @return Liste des automates encore en jeu
     *
     */
    ArrayList<Automate> getAutomatesEnJeu(){
        ArrayList<Automate> listeDesAutomates = new ArrayList<>();

        //Pour chaque cellule, si l'automate n'est pas encore aujouté dans la liste on l'ajoute.
        for(Cellule c:_listeDesCellules){
            if(!listeDesAutomates.contains(c._parent)){
                listeDesAutomates.add(c._parent);
            }
        }
        return listeDesAutomates;
    }

    /**
     * Méthode qui renvoie les cellules qui sont présente sur une case de la grille donnée en paramètre.
     * La case peut être hors des limites du terrain, l'algorithme d'extension décidera quoi faire.
     *
     * @param position Case de la grille dont on veut connaitre les cellules.
     *
     * @return Liste cellules présente dans la case donnée en paramètre.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "position" n'est pas de 2.
     */
    ArrayList<Cellule> getCellules(int[] position) {
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }
        return _methodeExtension.voisinage(this, position);
    }

    /**
     * Réecriture de la méthode toString
     *
     * @return Vue textuelle de la grille.
     *
     */
    @Override
    public String toString() {
        String chaineRetour = "";
        ArrayList<Automate> listeDesAutomates = getAutomatesEnJeu();
        //Pour chaque case de la grille
        for(int y = 0; y<_taille[1]; y++){
            for(int x = 0; x<_taille[0]; x++){


                //On récupère la première cellule trouvée dans cette case
                Cellule trouve = null;
                for (Cellule cellule: _listeDesCellules) {
                    if((cellule._position[0] == x) && (cellule._position[1] == y)){
                        trouve = cellule;
                        break;
                    }
                }

                //Si il existe une cellule, on inscrit le numéro de l'automate
                if(trouve != null){
                    int numeroDeLautomate = listeDesAutomates.indexOf(trouve._parent);
                    chaineRetour += "| "+ numeroDeLautomate+"  ";
                }
                //Sinon on inscrit rien.
                else{
                    chaineRetour += "|    ";
                }

            }
            chaineRetour += "|\n";
        }
        return chaineRetour;
    }
    //endregion
}
