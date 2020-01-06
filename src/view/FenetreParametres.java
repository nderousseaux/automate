package view;

import controler.ControleurPartie;
import controler.ControleurVue;
import model.AlgorithmeEvolution;
import model.AlgorithmeExtension;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FenetreParametres extends JFrame {
    private HashMap _parametres = new HashMap();

    /**
     * Constructeur de la classe FenetreParametres
     */
    public FenetreParametres() {
        super();

        // Configuration générale de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Automates cellulaires - Paramètres");
        this.setSize(680, 680);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Ajout d'un texte d'introduction à la fenêtre
        JLabel jl = new JLabel("<html><h2 style='margin:0;padding:0'>Paramètres de la partie</h2>" +
                "<p>Avant de jouer il faut paramétrer votre partie.</p></html>");
        jl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jl, BorderLayout.NORTH);

        // Container des paramètres
        JPanel container = new JPanel(new GridLayout(3, 1));
        this.add(container, BorderLayout.CENTER);

        // DEBUT PARAMETRES GENERAUX
        JPanel jp = new JPanel(new GridLayout(8, 1));
        Border border = BorderFactory.createTitledBorder("Paramètres généraux de la partie");
        jp.setBorder(border);

        // Taille de la grille
        JPanel jpt = new JPanel(new GridLayout(1, 4));
        jp.add(jpt);
        jpt.add(new JLabel("Longueur de la grille", SwingConstants.CENTER));
        JSpinner jsLong = new JSpinner(new SpinnerNumberModel(2, 2, null, 1));
        jpt.add(jsLong);
        jpt.add(new JLabel("Largeur de la grille", SwingConstants.CENTER));
        JSpinner jsLarg = new JSpinner(new SpinnerNumberModel(2, 2, null, 1));
        jpt.add(jsLarg);

        // Algorithme d'évolution
        jp.add(new JLabel("Méthode d'expension de la grille"));
        ArrayList<AlgorithmeExtension> listeAlgo = ControleurPartie.getInstance().getListeAlgorithmeExtension();
        String[] listeMeth = new String[listeAlgo.size()];
        for(int i = 0; i < listeAlgo.size(); i++) {
            listeMeth[i] = listeAlgo.get(i).getName();
        }
        JComboBox comboExpension = new JComboBox(listeMeth);
        jp.add(comboExpension);
        container.add(jp);

        // Nombre de cellules en vie initialement
        jp.add(new JLabel("Nombre initial de cellules par automate"));
        JSpinner jsCellules = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        jp.add(jsCellules);

        // Nombre de tours
        jp.add(new JLabel("Nombre de tours"));
        JSpinner jsTours = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        jp.add(jsTours);
        // FIN PARAMETRES GENERAUX

        // Construction de la liste des types d'automate
        ArrayList<AlgorithmeEvolution> listeTypes = ControleurPartie.getInstance().getListeAlgorithmesEvolutionDisponible();
        String[] listeNomsTypes = new String[listeTypes.size()];
        for(int i = 0; i < listeTypes.size(); i++) {
            listeNomsTypes[i] = listeTypes.get(i).getName();
        }

        // DEBUT JOUEUR 1
        JPanel jp1 = new JPanel(new GridLayout(4, 1));
        Border jp1border = BorderFactory.createTitledBorder("Automate du joueur 1");
        jp1.setBorder(jp1border);
        jp1.add(new JLabel("Type d'automate"));
        JComboBox comboTypeJ1 = new JComboBox(listeNomsTypes);
        jp1.add(comboTypeJ1);
        jp1.add(new JLabel("Nom de l'automate"));
        JTextField txtNomJ1 = new JTextField();
        jp1.add(txtNomJ1);
        container.add(jp1);
        // FIN JOUEUR 1

        // DEBUT JOUEUR 2
        JPanel jp2 = new JPanel(new GridLayout(4, 1));
        Border jp2border = BorderFactory.createTitledBorder("Automate du joueur 2");
        jp2.setBorder(jp2border);
        jp2.add(new JLabel("Type d'automate"));
        JComboBox comboTypeJ2 = new JComboBox(listeNomsTypes);
        jp2.add(comboTypeJ2);
        jp2.add(new JLabel("Nom de l'automate"));
        JTextField txtNomJ2 = new JTextField();
        jp2.add(txtNomJ2);
        container.add(jp2);
        // FIN JOUEUR 2

        // Bouton "Suite"
        JButton btnSuite = new JButton("SUITE");
        btnSuite.addActionListener(e -> {
            // On actualise les paramètres dans la HashMap
            _parametres.clear();
            _parametres.put("LongueurGrille", jsLong.getValue());
            _parametres.put("LargeurGrille", jsLarg.getValue());
            _parametres.put("AlgoExpension", comboExpension.getSelectedIndex());
            _parametres.put("CellulesDepart", jsCellules.getValue());
            _parametres.put("NombreTours", jsTours.getValue());
            _parametres.put("J1Type", comboTypeJ1.getSelectedIndex());
            _parametres.put("J1Nom", txtNomJ1.getText());
            _parametres.put("J2Type", comboTypeJ2.getSelectedIndex());
            _parametres.put("J2Nom", txtNomJ2.getText());

            // Debug : Parametrer automatiquement
            /*_parametres.clear();
            _parametres.put("LongueurGrille", 10);
            _parametres.put("LargeurGrille", 5);
            _parametres.put("AlgoExpension", 0);
            _parametres.put("CellulesDepart", 1);
            _parametres.put("NombreTours", 5);
            _parametres.put("J1Type", 0);
            _parametres.put("J1Nom", "JoueurA");
            _parametres.put("J2Type", 1);
            _parametres.put("J2Nom", "JoueurB");*/

            // On traite les paramètres
            traiterParametres();
        });
        this.add(btnSuite, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        this.setVisible(true);
    }

    public void traiterParametres() {
        if(_parametres.get("J1Nom").toString().length() == 0) {
            // Le nom du joueur 1 est vide
            JOptionPane.showMessageDialog(this, "Le nom du joueur 1 est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(_parametres.get("J2Nom").toString().length() == 0) {
            // Le nom du joueur 2 est vide
            JOptionPane.showMessageDialog(this, "Le nom du joueur 2 est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(_parametres.get("J1Nom").toString().equals(_parametres.get("J2Nom").toString())) {
            // Les noms des automates sont les mêmes
            JOptionPane.showMessageDialog(this, "Les deux automates ne peuvent pas avoir le même nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if((int)_parametres.get("J1Type") == (int)_parametres.get("J2Type")) {
            // Les deux joueurs ont le même type d'automate
            JOptionPane.showMessageDialog(this, "Chaque joueur doit avoir un type d'automate différent.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if((int)_parametres.get("CellulesDepart")*2 > (int)_parametres.get("LargeurGrille")*(int)_parametres.get("LargeurGrille")) {
            // Trop de cellules de départ pour pouvoir tout placer sur la grille
            JOptionPane.showMessageDialog(this, "La grille est trop petite pour accueillir autant de cellules initialement.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            // Tout est bon
            // Initialisation de la grille
            ControleurPartie.getInstance().initGrille((int)_parametres.get("LongueurGrille"), (int)_parametres.get("LargeurGrille"), (int)_parametres.get("AlgoExpension"));

            // Enregistrement des paramètres généraux
            ControleurPartie.getInstance().setNbCelluleVivante((int)_parametres.get("CellulesDepart"));
            ControleurPartie.getInstance().setNbTourMax((int)_parametres.get("NombreTours"));

            // Creation de l'automate du joueur 1
            ControleurPartie.getInstance().createAutomate2((int)_parametres.get("J1Type"), _parametres.get("J1Nom").toString());

            // Creation de l'automate du joueur 2
            ControleurPartie.getInstance().createAutomate2((int)_parametres.get("J2Type"), _parametres.get("J2Nom").toString());

            this.dispose();
            ControleurVue.getInstance().ouvrirPlacement();
        }
    }
}
