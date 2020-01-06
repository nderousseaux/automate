package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Fenêtre affichant un message d'introduction et le bouton pour jouer
 */
public class FenetreIntro extends JFrame {
    //region Constructeur
    /**
     * Constructeur de la classe FenetreIntro
     */
    public FenetreIntro() {
        super();

        // Configuration générale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires - Introduction");
        this.setSize(680, 280);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Ajout du texte d'introduction à la fenêtre
        JLabel jl = new JLabel("<html><h2 style='margin:0;padding:0'>Bienvenue sur le jeu de combat d'automates !</h2>" +
                "<p>Une partie se joue à deux joueurs. Vous devrez d'abord vous mettre d'accord sur les paramètres de la partie." +
                " Ensuite, chaque joueur va choisir un type d'automate et placer ses cellules de départ sur la grille.<br/>" +
                " La partie va alors se lancer et les automates vont évoluer. Si une case est disputée par deux cellules, il y a un combat. La cellule perdante meurt.<br/>" +
                " Le dernier automate avec des cellules en vie est déclaré vainqueur. Bon jeu !</p></html>");
        jl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jl, BorderLayout.NORTH);

        // Ajout du bouton de lancement à la fenêtre
        JButton btn = new JButton("JOUER");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btn.addActionListener(e -> {
            this.dispose();
            GestionnaireVue.getInstance().ouvrirParametres();
        });

        this.add(btn, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        this.setVisible(true);
    }
    //endregion
}
