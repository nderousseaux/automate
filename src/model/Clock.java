package model;

import java.util.ArrayList;
//TODO: Dans le model ou le controller ?

/**
 * La classe Clock gère les tours dans la partie
 * Elle est définie par :
 *  - La grille qu'elle gère
 *  - Le nombre de tour déjà écoulé
 *  - Le nombre de tour maximum
 *
 */
 class Clock {

    //region Attributs
    private int _nbTour;
    private int _nbTourMax;
    private Grille _grille;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe Clock.
     *
     * @param grille Grille sur laquelle va se dérouler les tours
     * @param nbTourMax Nombre maximum de tour de la partie
     *
     * @throws IllegalArgumentException Si le nombre de tour maximum de la partie est négatif.
     *
     */
     Clock(Grille grille, int nbTourMax){
        if(nbTourMax<0){
            throw new IllegalArgumentException("Le nombre de tour est négatif.");
        }

        _grille = grille;
        _nbTourMax=nbTourMax;

        //On fait un premier combat si jamais il y a plusieurs cellules sur la même case.
        combat();
    }
    //endregion

    //region Méthodes d'instances
    /**
     * Méthode qui fait passer un tour.
     * Un tour est composé deux actions dans l'ordre :
     *  1 - Etendre chaque automate
     *  2 - Faire combatres les cellules qui sont sur la même case.
     *
     */
     void tour(){
        _nbTour+=1;

        //On étend chaque automate
        ArrayList<Automate> listeDesAutomates = _grille.getAutomatesEnJeu();
        for (Automate automate:listeDesAutomates) {
            automate.evolution();
        }

        //On les fait se combatre
        combat();
    }

    /**
     * Méthode qui fait combatre les automates.
     * Si une case est disputé par plusieurs automates, un tirage au sort décidera quelle sera la cellule gagnante.
     *
     */
    private void combat(){
        //On parcourt toutes les cases de la grille
        for(int y=0; y<= _grille.getTaille()[1]; y++) {
            for(int x=0; x<= _grille.getTaille()[0]; x++){
                int[] position = {x,y};

                //On liste toutes les cellules sur la case
                ArrayList<Cellule> cellulesSurLaPosition = new ArrayList<>();
                for(Cellule cellule:_grille.getListeCellules()){
                    if(cellule.getPosition()[0] == position[0] && cellule.getPosition()[1] == position[1]){
                        cellulesSurLaPosition.add(cellule);
                    }
                }

                //Si le nombre de cellule est supérieur 1, combat.
                if(cellulesSurLaPosition.size() >1){
                    //On choisi l'index de la survivante au hasard
                    int indexVivante = (int)(Math.random() * cellulesSurLaPosition.size());
                    //On tue toutes les autres
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

    /**
     * Méthode qui annonce la fin de la partie.
     * La partie est finie si il ne reste qu'un automate vivant ou que le nombre de tour maximum à été atteint.
     * Tant que cette méthode renvoie null, la partie n'est pas finie.
     *
     * @return La liste des automates vainqueurs. Vide en cas de match nul.
     *
     */
     ArrayList<Automate> fin(){
        ArrayList<Automate> listeDesAutomates = _grille.getAutomatesEnJeu();

        ArrayList<Automate> gagnant;

        //Si il n'y a plus d'automates, match null
        if(listeDesAutomates.size() == 0){
            gagnant = new ArrayList<>();
        }
        //Si il n'y en a plus qu'un, il est vainqueur
        else if(listeDesAutomates.size() == 1){
            gagnant = new ArrayList<>();
            gagnant.add(listeDesAutomates.get(0));
        }
        //Si les tours sont finit, l'automate avec le plus de cellules gagne
        else if(_nbTour >=_nbTourMax){
            //On cherche le nombre maximum de cellule
            int nbMax = listeDesAutomates.get(0).getCellules().size();
            for (Automate auto : listeDesAutomates) {
                if(auto.getCellules().size()>nbMax){
                    nbMax = auto.getCellules().size();
                }
            }

            //On trouve tous les automates gagnants
            int finalNbMax = nbMax;
            listeDesAutomates.removeIf(a->(a.getCellules().size() < finalNbMax));
            gagnant = listeDesAutomates;
        }
        //Sinon, la partie n'est pas encore finie
        else{
            gagnant = null;
        }

        return gagnant;
    }
    //endregion
}
