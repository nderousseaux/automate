package model;

public class Cellule {

    public Automate _parent;
    public Grille _terrain;
    public int _position[];


    public Cellule(Automate parent, Grille terrain, int position[]){
        _parent = parent;
        _terrain = terrain;
        _position = position;
    }

}
