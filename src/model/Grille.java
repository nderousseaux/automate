package model;

import java.util.ArrayList;


public class Grille {
    // Attribut contenant la méthode d'extension de la grille (pour obtenir le voisinage)
    // La méthode par défaut est celle de la Répétition
    private MethodeExtension _methodeExtension = new ExtensionRepet();

    public ArrayList<model.Automate> automatesEnJeu(){
        ArrayList<Automate> listeDesAutomates = new ArrayList<>();
        for(Cellule c:_listeDesCellules){
            if(!listeDesAutomates.contains(c._parent)){
                listeDesAutomates.add(c._parent);
            }
        }
        return listeDesAutomates;
    }
    public int _taille[] = new int[2];
    public ArrayList<Cellule> _listeDesCellules;

    public Grille(int taille[], ArrayList<Cellule> listeDesCellules){
        _taille = taille;
        _listeDesCellules = listeDesCellules;
    }

    // Méthode pour définir la méthode d'extension de la grille
    public void setMethodeExtension(MethodeExtension me) {
        this._methodeExtension = me;
    }

    // Méthode qui renvoie les cellule présente à une position donnée
    public ArrayList<Cellule> getCellule(int[] pos) {
        return _methodeExtension.voisinage(this, pos);


    }

    public int[] getTaille() {
        return this._taille;
    }

    public ArrayList<Cellule> getListeCellules() {
        return this._listeDesCellules;
    }

    @Override
    public String toString() {
        String retour = "";
        for(int y = 0; y<_taille[1]; y++){
            for(int x = 0; x<_taille[0]; x++){

                //On regarde si il existe une cellule dans la case
                Boolean trouve = false;
                for (Cellule cellule: _listeDesCellules) {
                    if((cellule._position[0] == x) && (cellule._position[1] == y)){
                        trouve = true;
                        break;
                    }
                }
                //Si oui, on inscrit un o
                if(trouve){
                    retour += "| o  ";
                }
                else{
                    retour += "|    ";
                }

            }
            retour += "|\n";
        }
        return retour;
    }
}
