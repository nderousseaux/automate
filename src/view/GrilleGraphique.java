package view;

import controler.ControleurPartie;
import model.Automate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GrilleGraphique extends JPanel {
    int[] _taille = new int[2];
    private ArrayList<Shape> grid = new ArrayList<Shape>();
    private ArrayList<Shape> fill = new ArrayList<Shape>();

    public GrilleGraphique(int tailleX, int tailleY) {
        _taille[0] = tailleX;
        _taille[1] = tailleY;

        /*
        addMouseListener(new MouseAdapter() {
            int x, y;
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < grid.size(); i++) {
                    Shape shape = grid.get(i);
                    if (shape.contains(e.getPoint())) {
                        x = i%_taille[0];
                        y = i/_taille[0];
                        if (!fill.contains(shape)) {
                            fill.add(shape);
                        }
                    }
                }
                System.out.println("Concerne : " + x + " " + y);
                System.out.println(ControleurPartie.getInstance().getGrille().getListeCellules());
                repaint();
            }
        });
        */

        int colWidth = 25;
        int rowHeight = 25;

        for (int row = 0; row < _taille[1]; row++) {
            for (int col = 0; col < _taille[0]; col++) {
                grid.add(new Rectangle(colWidth * col, rowHeight * row, colWidth, rowHeight));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (Shape cell : fill) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLACK);
        for (Shape cell : grid) {
            g2d.draw(cell);
        }
    }

    /* TODO : Methode refresh appelée avec un tableau 2D qui contient le numéro de chaque automate à l'emplacement
    * de chaque cellule (position dans le tableau = position sur la grille, ex : tab[0, 1] contient le numéro
    * de l'automate de la cellule en position [0, 1] sur la grille. */
    public void refresh(int[] tableauCellules) {

    }
}
