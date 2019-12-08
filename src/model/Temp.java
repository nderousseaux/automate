package model;

import java.util.ArrayList;
//TODO: Dans le model ou le controller ?
public class Temp {

    int _nbTour;
    int _nbTourMax;
    Grille _grille;

    public Temp(Grille grille, int nbTourMax){
        _grille = grille;
        _nbTourMax=nbTourMax;

        combat();
    }

    public void tour(){
        _nbTour+=1;
        ArrayList<Automate> listAutomates = _grille.automatesEnJeu();

        //On étand chaque automate
        for (Automate automate:listAutomates) {
            //On étand chaque automate
            automate.expense();
        }

        //On vérifie qu'aucune cellule ne se supperpose, si c'est le cas, combat
        combat();
    }

    public void combat(){
        //On vérifie qu'aucune cellule ne se supperpose, si c'est le cas, combat
        for(int y=0; y<= _grille._taille[1]; y++) {
            for(int x=0; x<= _grille._taille[0]; x++){
                int position[]= {x,y};

                //On liste toutes les cellules sur la position
                ArrayList<Cellule> cellulesSurLaPosition = new ArrayList<Cellule>();
                for(Cellule cellule:_grille._listeDesCellules){
                    if(cellule._position[0] == position[0] && cellule._position[1] == position[1]){
                        cellulesSurLaPosition.add(cellule);
                    }
                }



                //Si le nombre de cellule est supérieur 1, combat.
                if(cellulesSurLaPosition.size() >1){
                    int indexVivante = (int)(Math.random() * cellulesSurLaPosition.size());
                    Cellule celluleSurvivante = cellulesSurLaPosition.get(indexVivante);
                    for (Cellule cellule:cellulesSurLaPosition) {
                        if(cellule != celluleSurvivante){
                            cellule.meurt();
                        }
                    }
                }



            }



        }
    }

    public ArrayList<Automate>fin(){
        ArrayList<Automate> listAutomates = _grille.automatesEnJeu();

        //si personne n'a gagné, null
        ArrayList<Automate> gagnant = null;


        //Si il ne reste plus qu'un automate en vie
        ArrayList<Automate> listeAutomateTemporaire = _grille.automatesEnJeu();
        listeAutomateTemporaire.removeIf(a->(a._cellules.size() ==0));
        if(listAutomates.size() == 0){
            gagnant = new ArrayList<>();
        }
        else if(listAutomates.size() == 1){
            gagnant = new ArrayList<>();
            gagnant.add(listeAutomateTemporaire.get(0));
        }
        //Si les tours sont finit, l'automate avec le plus de cellule gagne
        else if(_nbTour >=_nbTourMax){
            gagnant = new ArrayList<>();
            int nbMax = listAutomates.get(0)._cellules.size();
            for (Automate auto : listAutomates) {
                if(auto._cellules.size()>nbMax){
                    nbMax = auto._cellules.size();
                }
            }

            //On trouve touts les automates gagnants
            int finalNbMax = nbMax;
            listAutomates.removeIf(a->(a._cellules.size() < finalNbMax));
            gagnant = listAutomates;
        }

        return gagnant;
    }


}
