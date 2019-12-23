import controler.Controler;
import model.AlgorithmeExtensionRepetition;
import model.Automate;
import model.Cellule;
import model.Grille;
import view.TextualView;

import java.util.ArrayList;

//TODO:Vue graphique
//TODO:Vérifier si la méthode algorithme d'évolution est conforme au sujet
//TODO:Créer l'uml
//TODO:Créer le rapport

public class Main {

    public static void main(String[] args) {
        Controler controleur = new Controler();
        TextualView textualView = new TextualView(controleur);
        textualView.start();
    }
}
