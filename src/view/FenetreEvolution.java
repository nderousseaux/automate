package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class FenetreEvolution extends JFrame {
    /**
     * Constructeur de la classe FenetreParametres
     */
    public FenetreEvolution() {
        super();

        // Configuration générale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires - Partie en cours...");
        this.setSize(680, 680);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Ajout d'un texte d'introduction à la fenêtre
        JLabel jl = new JLabel("<html><h2 style='margin:0;padding:0'>Partie en cours...</h2>" +
                "<p>Les automates évoluent, le premier à ne plus avoir de cellule en vie a perdu.</p></html>");
        jl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jl, BorderLayout.NORTH);

        // Affichage de la fenêtre
        this.setVisible(true);
    }
}
