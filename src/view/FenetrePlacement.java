package view;

import controler.ControleurPartie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Fenêtre permettant aux joueurs de placer leurs cellules initiales au lancement de la partie
 * Elle est définie par :
 *  - Un entier contenant l'index de l'automate qui doit placer sa cellule
 *  - Un JLabel contenant le texte d'instruction pour indiquer qui doit placer sa cellule
 */
public class FenetrePlacement extends JFrame {
    //region Attributs
    private int _automateCourant = 0;
    private JLabel _labelTour;
    //endregion

    // region Constructeur
    /**
     * Constructeur de la classe FenetrePlacement
     */
    public FenetrePlacement() {
        super();

        // Configuration générale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires - Placement initial");
        this.setSize(680, 280);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Ajout d'un texte d'introduction à la fenêtre
        JLabel jl = new JLabel("<html><h2 style='margin:0;padding:0'>Placement initial des cellules</h2>" +
                "<p>Dernière étape avant la partie, placez chacun vos cellules initiales.</p></html>");
        jl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jl, BorderLayout.NORTH);

        // Container
        JPanel container = new JPanel(new GridLayout(3, 1));
        this.add(container, BorderLayout.CENTER);

        // DEBUT PLACEMENT
        // On mélange la liste
        ControleurPartie.getInstance().mix();

        // On ajoute l'instruction du tour courant
        _labelTour = new JLabel("C'est à " + ControleurPartie.getInstance().getListeDesAutomates().get(0).getName() + " de placer une cellule.");
        container.add(_labelTour);

        // Choix des coordonnées
        JPanel jpc = new JPanel(new GridLayout(1, 4));
        container.add(jpc);
        jpc.add(new JLabel("X", SwingConstants.CENTER));
        JSpinner jsX = new JSpinner(new SpinnerNumberModel(0, 0, ControleurPartie.getInstance().getDimensionX()-1, 1));
        jpc.add(jsX);
        jpc.add(new JLabel("Y", SwingConstants.CENTER));
        JSpinner jsY = new JSpinner(new SpinnerNumberModel(0, 0, ControleurPartie.getInstance().getDimensionY()-1, 1));
        jpc.add(jsY);

        // Bouton pour ajouter la cellule
        JButton btnAjouter = new JButton("Ajouter la cellule");
        btnAjouter.addActionListener(e -> {
            int x = (int)jsX.getValue();
            int y = (int)jsY.getValue();
            placer(x, y);
        });
        container.add(btnAjouter);
        // FIN PLACEMENT

        // Affichage de la fenêtre
        this.setVisible(true);
    }
    //endregion

    //region Methodes d'instance
    /**
     * Procédure qui gère le placement d'une cellule après que le joueur ait validé son choix
     */
    public void placer(int x, int y) {
        try {
            ControleurPartie.getInstance().addCellule(x, y, ControleurPartie.getInstance().getListeDesAutomates().get(_automateCourant));
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Cette case est déjà occupée !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // La cellule a bien été ajoutée, on s'assure qu'on doit continuer et si oui on passe au tour de placement suivant
        if(ControleurPartie.getInstance().getGrille().getListeCellules().size() < ControleurPartie.getInstance().getNbCelluleVivante()*2) {
            // On passe au tour de placement suivant
            _automateCourant++;
            if(_automateCourant >= ControleurPartie.getInstance().getListeDesAutomates().size())
                _automateCourant = 0;
            _labelTour.setText("C'est à " + ControleurPartie.getInstance().getListeDesAutomates().get(_automateCourant).getName() + " de placer une cellule.");
        } else {
            // Lancement de l'évolution des automates
            this.dispose();
            GestionnaireVue.getInstance().ouvrirEvolution();
        }
    }
    //endregion
}
