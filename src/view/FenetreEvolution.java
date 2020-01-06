package view;

import controler.ControleurPartie;
import model.Automate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class FenetreEvolution extends JFrame {
    GrilleGraphique _grille = null;

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

        // Ajout de la grille à la fenêtre
        _grille = new GrilleGraphique(ControleurPartie.getInstance().getDimensionX(), ControleurPartie.getInstance().getDimensionY());
        this.add(_grille);

        // Ajout de la légende de couleur
        JLabel legende = new JLabel("<html>Couleurs : <span style='color: red'>" + ControleurPartie.getInstance().getListeDesAutomates().get(0).getName()
        + "</span> | <span style='color: blue'>" + ControleurPartie.getInstance().getListeDesAutomates().get(1).getName() + "</span></html>");
        legende.setFont(new Font("Segoe UI", Font.BOLD, 22));
        this.add(legende, BorderLayout.SOUTH);
        // Affichage de la fenêtre
        this.setVisible(true);

        new Thread(() -> {
            try {
                evolution();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    /**
     * Procèdure qui gère l'évolution des automates une fois la partie lancée.
     *
     */
    public void evolution() throws InterruptedException {
        while(ControleurPartie.getInstance().IsFin() == null){
            ControleurPartie.getInstance().nextTour();
            System.out.println(ControleurPartie.getInstance().stringGrille());
            _grille.refresh(ControleurPartie.getInstance().getGrille().getListeCellules());
            Thread.sleep(2000);
        }
        String msg = "La partie est terminée !\n";
        ArrayList<Automate> vainqueurs = ControleurPartie.getInstance().IsFin();

        if(vainqueurs.size() == 0){
            msg += "Aucun automate n'est ressorti vainqueur...";
        } else if(vainqueurs.size() == 1){
            msg += "Le vainqueur est " + vainqueurs.get(0).getName();
        } else{
            msg += "Les vainqueurs à égalité sont : " + vainqueurs.get(0).getName();
            for (Automate automate:vainqueurs) {
                msg += vainqueurs.get(0).getName() + " ";
            }
        }
        JOptionPane.showMessageDialog(this, msg, "Fin de la partie !", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }


}
