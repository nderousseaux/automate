package model;

public class Cellule {

    public Automate _parent;
    public Grille _terrain;
    public int _position[];


    public Cellule(Automate parent, Grille terrain, int position[]){
        _parent = parent;
        _terrain = terrain;
        _position = position;
        parent._cellules.add(this);
        terrain.getListeCellules().add(this);
    }

    public void meurt(){
        _parent._cellules.remove(this);
        _terrain.getListeCellules().remove(this);
    }

    public int[] getPosition() {
        return this._position;
    }
}
