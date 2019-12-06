package model;

import java.lang.invoke.CallSite;
import java.util.ArrayList;


public class Grille {
    // Attribut contenant la méthode d'extension de la grille (pour obtenir le voisinage)
    // La méthode par défaut est celle de la Répétition
    private MethodeExtension _methodeExtension = new ExtensionRepetition();

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

    // Méthode pour récupérer le voisinage d'une cellule à partir de sa position
    public ArrayList<Cellule> voisinage(int position[]) {
        // Appel à la méthode de la stratégie de la grille (Pattern Strategie)
        return _methodeExtension.voisinage(this, position);
    }

    // Méthode qui renvoie la cellule présente à une position donnée
    public Cellule getCellule(int[] pos) {
        Cellule res = null;
        int c = 0;
        while(res == null && c < _listeDesCellules.size()) {
            int x = _listeDesCellules.get(c).getPosition()[0];
            int y = _listeDesCellules.get(c).getPosition()[1];
            if(x == pos[0] && y == pos[1]) {
                res = _listeDesCellules.get(c);
            }
            c++;
        }
        return res;
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
        for(int y = 0; y<_taille[0]; y++){
            for(int x = 0; x<_taille[1]; x++){

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
