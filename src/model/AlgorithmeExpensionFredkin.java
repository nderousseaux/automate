package model;

import java.util.ArrayList;

public class AlgorithmeExpensionFredkin implements AlgorithmeExpension {

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

                //On ne s'insteresse qu'aux notres, on supprime les cellules n'appartenant pas à l'automate
                cellulesEntourage.removeIf(n->(n._parent != automate));

                //On ne récupère que celles des nord/sud/est/ouest.

                //Une cellule morte qui possède exactement 3 cellules vivantes nai
                if(!automate.hasCellule(position) && cellulesEntourage.size() == 3){
                    new Cellule(automate, grille, position);
                }
                //Une cellule vivante qui possède moins de 2  ou plus de trois cellules vivantes, meurt
                if(automate.hasCellule(position) && (cellulesEntourage.size()!=2 && cellulesEntourage.size()!=3)){
                    automate.getCellule(position).meurt();
                }

            }
        }
    }
}
