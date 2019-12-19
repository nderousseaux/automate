package view;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    GrilleGraphique _grille = null;
    /**
     * Constructeur de la classe Fenêtre
     */
    public Fenetre(int[] tailleGrille) {
        super();

        // Configuration de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires");
        this.setSize(720, 720);
        this.setLocationRelativeTo(null);

        // Ajout de la grille graphique à la fenêtre
        _grille = new GrilleGraphique(tailleGrille);
        this.add(_grille, BorderLayout.CENTER);

        // Affichage de la fenêtre
        this.setVisible(true);
    }
}
