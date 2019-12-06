package model;

import java.lang.invoke.CallSite;
import java.util.ArrayList;

public class Grille {

    public ArrayList<Automate> automatesEnJeu(){
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

    //Retourne toutes les cellules entourant la cellule indiquée par la position, dans l'ordre :
    //exemple : 1 2 3       avec 5 la cellule dont on veut l'entourage : arrayListRetour : 1,2,3,4,6,7,8,9
    //          4 5 6
    //          7 8 9
    public ArrayList<Cellule> entourage(int position[]){
        //TODO:Un pattern stratégie, qui permet de renvoyer l'entourage en fonction de l'algorithme d'expension.
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
