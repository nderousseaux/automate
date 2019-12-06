package model;

import java.util.ArrayList;

public class Automate {

    public ArrayList<Cellule> _cellules;
    public AlgorithmeExpension _algoExpension;

    public Automate(){
    }

    public void expense(){
        _algoExpension.expense(this);
    }




}
