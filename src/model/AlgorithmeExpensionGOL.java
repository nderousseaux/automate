package model;

import java.util.ArrayList;

public class AlgorithmeExpensionGOL implements AlgorithmeExpension {

    @Override
    public void expense(Automate automate) {
        //On récupère la grille
        Grille grille = automate._cellules.get(0)._terrain;

        //On regarde chaque case de la grille, et on détermine ce qu'il faut faire.
        for(int y=0; y<= grille._taille[1]; y++) {
            for(int x=0; x<= grille._taille[0]; y++){
                int position[]= {x,y};

                //On récupère son entourage
                ArrayList<Cellule> cellulesEntourage = grille.entourage(position);

            }
        }
    }
}
