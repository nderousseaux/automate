package model;

import java.util.ArrayList;

public class AlgorithmeExpensionGOL implements AlgorithmeExpension {

    //Si mort et 3 viv autour = naissance
    //Si viv et pas 2 ni 3 d'entourage : mort
    
    @Override
    public void expense(Automate automate) {
        //On récupère la grille
        Grille grille = automate._cellules.get(0)._terrain;

        //Cellules à créer
        ArrayList<int[]> cellulesACreer = new ArrayList<>();
        //Cellules à supprimer
        ArrayList<Cellule> cellulesASupprimer = new ArrayList<>();

        //On regarde chaque case de la grille, et on détermine ce qu'il faut faire.
        for(int y=0; y< grille._taille[1]; y++) {
            for(int x=0; x< grille._taille[0]; x++){
                int position[]= {x,y};
                
                //On regarde combien de cellules vivante autour d'elle
                int nbCellulesVivantes = nbCellulesVivantes(automate, position);

                //Si la cellule est morte et a exactement 3 cellules vivantes autour, elle nait
                ArrayList<Cellule> cellulesACetEndroit = grille.getCellule(position);
                cellulesACetEndroit.removeIf(cellule -> (cellule._parent!=automate));
                if(cellulesACetEndroit.size() == 0 && nbCellulesVivantes == 3){
                    cellulesACreer.add(new int[]{x,y});
                }

                //Si la cellule est vivante et qu'elle a moins de 2 ou plus de 3 voisines elle meurt
                if(cellulesACetEndroit.size() == 1 && nbCellulesVivantes != 2 && nbCellulesVivantes != 3){
                    cellulesASupprimer.add(cellulesACetEndroit.get(0));
                }
            }
        }

        //On crée les cellules
        for (int[] pos:cellulesACreer) {
            new Cellule(automate, grille, pos);
        }
        //On supprime les cellules
        for(Cellule cellule: cellulesASupprimer){
            cellule.meurt();
        }
    }
    
    public int nbCellulesVivantes(Automate automate, int[] pos){
        //On récupère la grille
        Grille grille = automate._cellules.get(0)._terrain;
        
        int nbCellulesVivantes = 0;
        
        //En haut à gauche
        ArrayList<Cellule> hautGauche = grille.getCellule( new int[]{pos[0]-1, pos[1]-1});
        hautGauche.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += hautGauche.size();

        //En haut au milieu
        ArrayList<Cellule> hautMilieu = grille.getCellule( new int[]{pos[0], pos[1]-1});
        hautMilieu.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += hautMilieu.size();

        //En haut à droite
        ArrayList<Cellule> hautDroite = grille.getCellule( new int[]{pos[0]+1, pos[1]-1});
        hautDroite.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += hautDroite.size();

        //Milieu à gauche
        ArrayList<Cellule> milieuGauche = grille.getCellule( new int[]{pos[0]-1, pos[1]});
        milieuGauche.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += milieuGauche.size();
        
        //Milieu à droite
        ArrayList<Cellule> milieuDroite = grille.getCellule( new int[]{pos[0]+1, pos[1]});
        milieuDroite.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += milieuDroite.size();

        //En bas à gauche
        ArrayList<Cellule> basGauche = grille.getCellule( new int[]{pos[0]-1, pos[1]+1});
        basGauche.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += basGauche.size();

        //En bas au milieu
        ArrayList<Cellule> basMilieu = grille.getCellule( new int[]{pos[0], pos[1]+1});
        basMilieu.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += basMilieu.size();

        //En bas à droite
        ArrayList<Cellule> basDroite = grille.getCellule( new int[]{pos[0]+1, pos[1]+1});
        basDroite.removeIf(cellule -> (cellule._parent != automate));
        nbCellulesVivantes += basDroite.size();



        return nbCellulesVivantes;
    }
}
