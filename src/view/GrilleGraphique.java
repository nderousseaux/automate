package view;

import controler.ControleurPartie;
import model.Cellule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GrilleGraphique extends JPanel {
    int[] _taille = new int[2];
    private ArrayList<Shape> _grilleTab = new ArrayList();
    private ArrayList<Shape> _rempliJ1 = new ArrayList();
    private ArrayList<Shape> _rempliJ2 = new ArrayList();
    private int _tailleCellules = 25;

    public GrilleGraphique(int tailleX, int tailleY) {
        _taille[0] = tailleX;
        _taille[1] = tailleY;

        for (int ligne = 0; ligne < _taille[1]; ligne++) {
            for (int col = 0; col < _taille[0]; col++) {
                _grilleTab.add(new Rectangle(_tailleCellules * col, _tailleCellules * ligne, _tailleCellules, _tailleCellules));
            }
        }
    }

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

    public void refresh(ArrayList<Cellule> listeCellule) {
        _rempliJ1.clear();
        _rempliJ2.clear();
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
        this.repaint();
    }
}
