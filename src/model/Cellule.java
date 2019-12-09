package model;

/**
 * La classe Cellule modèlise une cellule vivante
 * Elle est définie par :
 *  - Son automate parent
 *  - Le terrain sur lequel elle est
 *  - Sa position sur le terrain
 *  - Son état
 *
 */
 class Cellule {

    //region Attributs
    private Automate _parent;
    private Grille _terrain;
    private int[] _position;
    private Etats _etat = Etats.VIVANT;
    //endregion

    //region Constructeurs
    /**
     * Constructeur de la classe Cellule.
     *
     * @param parent Automate parent de la cellule
     * @param terrain Grille sur laquelle évolue la cellule
     * @param position Position de la cellule sur la grille
     *
     * @throws IllegalArgumentException Si la longueur du tableau "position" n'est pas de 2.
     * @throws IndexOutOfBoundsException Si la taille est négative ou sort des bornes de la grille
     *
     */
     Cellule(Automate parent, Grille terrain, int[] position){
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }
        else if(position[0]<0 || position[0]>= terrain.getTaille()[0] || position[1]<0 || position[1]>= terrain.getTaille()[1]){
            throw new IndexOutOfBoundsException("La position indiquée sort des bornes de la grille");
        }

        _parent = parent;
        _terrain = terrain;
        _position = position;

        //On ajoute au parent et au terrain.
        terrain.addCellule(this);
        parent.getCellules().add(this);
    }
    //endregion

    //region Getteurs/Setteurs
    /**
     * Getteur du terrain de la cellule
     *
     * @return Le terrain sur lequel officie la cellule.
     *
     */
     Grille getTerrain() {
        return this._terrain;
    }

    /**
     * Getteur du parent de la cellule
     *
     * @return Automate à qui appartient la cellule
     *
     */
     Automate getParent() {
        return this._parent;
    }

    /**
     * Getteur de la position de la cellule
     *
     * @return La position de la cellule sur le terrain, sous forme d'un tableau d'entier.
     *
     */
     int[] getPosition() {
        return this._position;
    }
    //endregion

    //region Méthodes d'instances
    /**
     * Fonction qui déclare la mort d'une cellule.
     * Cela revient à la supprimer de son parent et du terrain.
     */
     void meurt(){
        //La cellule est supprimé de son parrent et du terrain
        _parent.getCellules().remove(this);
        _terrain.getListeCellules().remove(this);

        _parent = null;
        _terrain = null;
    }
    //endregion
}
