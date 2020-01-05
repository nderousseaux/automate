package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class FenetrePlacement extends JFrame {
    /**
     * Constructeur de la classe FenetreIntro
     */
    public FenetrePlacement() {
        super();

        // Configuration générale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires - Placement initial");
        this.setSize(680, 680);
        this.setLocationRelativeTo(null);

        // Ajout d'un texte d'introduction à la fenêtre
        JLabel jl = new JLabel("<html><h2 style='margin:0;padding:0'>Placement initial des cellules</h2>" +
                "<p>Dernière étape avant la partie, placez chacun vos cellules initiales.</p></html>");
        jl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jl, BorderLayout.NORTH);

        // Affichage de la fenêtre
        this.setResizable(false);
        this.setVisible(true);
    }
}
