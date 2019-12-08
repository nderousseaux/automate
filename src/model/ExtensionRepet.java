package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtensionRepet implements MethodeExtension {

    public  ArrayList<Cellule> voisinage(Grille grille, int[] pos) {

        ArrayList<Cellule> retour = new ArrayList<>();
        //Si la position sort du cadre, on donne la dernière cellule en bordure du cadre
        if(pos[0]<0){
            pos[0] = 0;
        }
        else if(pos[0] >= grille.getTaille()[0]){
            pos[0]= grille.getTaille()[0]-1;
        }
        if(pos[1]<0){
            pos[1] = 0;
        }
        else if(pos[1] >= grille.getTaille()[1]){
            pos[1]= grille.getTaille()[1]-1;
        }

        //Sinon, on renvoie simplement les cellules sur la position
        retour.addAll(getCellules(grille, pos));

        return retour;
    }


    public ArrayList<Cellule> getCellules(Grille grille, int[] pos){
        //Si la position sort du cadre : erreur
        if(pos[0]<0 || pos[0]>= grille.getTaille()[0] || pos[1]<0 || pos[1]>= grille.getTaille()[1]){
            throw new IndexOutOfBoundsException();
        }

        //Sinon on renvoie toutes les cellules à cet endroit
        ArrayList<Cellule> cellulesALaPosition = (ArrayList<Cellule>) grille.getListeCellules().clone();
        cellulesALaPosition.removeIf(cellule -> (cellule._position[0] != pos[0] || cellule._position[1] != pos[1]));
        return cellulesALaPosition;
    }
}