package model;

import java.util.ArrayList;

public class Automate {

    public ArrayList<Cellule> _cellules = new ArrayList<>();
    public AlgorithmeExpension _algoExpension = new AlgorithmeExpensionGOL();

    public Automate(){
    }

    public void expense(){
        _algoExpension.expense(this);
    }


    public boolean hasCellule(int[] position){
        boolean res = false;
        //Pour chaque cellule, on regarde si la position correspond
        for (Cellule cellule:_cellules) {
            if(cellule._position[0] == position[0] && cellule._position[1]==position[1]){
                res = true;
                break;
            }
        }
        return res;
    }

    public Cellule getCellule(int[] position){
        Cellule res = null;
        //Pour chaque cellule, on regarde si la position correspond
        for (Cellule cellule:_cellules) {
            if(cellule._position[0] == position[0] && cellule._position[1]==position[1]){
                res = cellule;
                break;
            }
        }
        if(res==null){
            throw new IllegalArgumentException();
        }

        return res;
    }




}
