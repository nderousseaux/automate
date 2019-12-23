package view;

import javax.swing.*;
import java.awt.*;

public class GrilleGraphique extends JPanel {
    int[] _taille;

    public GrilleGraphique(int[] taille) {
        this._taille = taille;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        int tailleCellule = 50;
        for (int x = tailleCellule; x <= tailleCellule*_taille[0]; x += tailleCellule) {
            for (int y = tailleCellule; y <= tailleCellule*_taille[1]; y += tailleCellule) {
                g.drawRect(x, y, tailleCellule, tailleCellule);
            }
        }
    }

    /* TODO : Methode refresh appelée avec un tableau 2D qui contient le numéro de chaque automate à l'emplacement
    * de chaque cellule (position dans le tableau = position sur la grille, ex : tab[0, 1] contient le numéro
    * de l'automate de la cellule en position [0, 1] sur la grille. */
    public void refresh(int[] tableauCellules) {

    }
}
