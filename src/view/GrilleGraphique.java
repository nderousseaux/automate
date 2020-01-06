package view;

import controler.ControleurPartie;
import model.Cellule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Représentation graphique d'une grille
 * Elle est définie par :
 *  - Un tableau de 2 entiers représentant la taille de la grille
 *  - Un tableau de toutes les rectangles qui composent la grille
 *  - Un tableau des rectangles qui appartiennent au Joueur 1
 *  - Un tableau des rectangles qui appartient au Joueur 2
 *  - Un entier représentant la taille de chaque rectangle
 */
public class GrilleGraphique extends JPanel {
    //region Attributs
    int[] _taille = new int[2];
    private ArrayList<Shape> _grilleTab = new ArrayList();
    private ArrayList<Shape> _rempliJ1 = new ArrayList();
    private ArrayList<Shape> _rempliJ2 = new ArrayList();
    private int _tailleCellules = 25;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe GrilleGraphique
     *
     * @param tailleX Longueur de la grille
     * @param tailleY Largeur de la grille
     *
     */
    public GrilleGraphique(int tailleX, int tailleY) {
        _taille[0] = tailleX;
        _taille[1] = tailleY;

        for (int ligne = 0; ligne < _taille[1]; ligne++) {
            for (int col = 0; col < _taille[0]; col++) {
                _grilleTab.add(new Rectangle(_tailleCellules * col, _tailleCellules * ligne, _tailleCellules, _tailleCellules));
            }
        }
    }
    //endregion

    //region Méthodes d'instance
    /**
     * Fonction gérant les "dessins" à réaliser
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (Shape cell : _rempliJ1) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLUE);
        for (Shape cell : _rempliJ2) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLACK);
        for (Shape cell : _grilleTab) {
            g2d.draw(cell);
        }
    }

    /**
     * Fonction gérant le rafraichissement de la grille
     */
    public void refresh(ArrayList<Cellule> listeCellule) {
        // On vide les deux listes (toutes les cases doivent être vide avant qu'on commence à "colorier")
        _rempliJ1.clear();
        _rempliJ2.clear();

        // On parcourt les rectangles et déterminons ceux qui doivent être "coloriés"
        for(Cellule c : listeCellule) {
            for(int i = 0; i < _grilleTab.size(); i++) {
                Shape forme = _grilleTab.get(i);
                if (forme.contains(c.getPosition()[0]*_tailleCellules, c.getPosition()[1]*_tailleCellules)) {
                    if (!_rempliJ1.contains(forme) && !_rempliJ1.contains(forme)) {
                        if(ControleurPartie.getInstance().getListeDesAutomates().indexOf(c.getParent()) == 0) {
                            _rempliJ1.add(forme);
                        } else if(ControleurPartie.getInstance().getListeDesAutomates().indexOf(c.getParent()) == 1){
                            _rempliJ2.add(forme);
                        }
                    }
                }
            }
        }

        // On "redessine" la grille à la fin pour afficher le résultat du refresh à l'écran
        this.repaint();
    }
    //endregion
}
