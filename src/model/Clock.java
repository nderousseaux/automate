package model;

import java.util.ArrayList;

public class Temp {

    Grille _grille;

    public Temp(Grille grille){
        _grille = grille;
    }

    public void tour(){

        ArrayList<Automate> listAutomates = _grille.automatesEnJeu();

        //On étand chaque automate
        for (Automate automate:listAutomates) {
            //On étand chaque automate
            automate.expense();
        }

        //On vérifie qu'aucune cellule ne se supperpose, si c'est le cas, combat
        HashMap : instance de cellule sur chaque position' :
        clé : [x, y]
        donnée : arraylist<cellule>;

        Ensuite, si donnée.len supérieur à 1, combat, seul un survivra.


        //On vérifie les condition de fin
        Si


    }


}
