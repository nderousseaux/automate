import controler.ControleurPartie;
import view.TextualView;

//TODO:Vue graphique
//TODO:Vérifier si la méthode algorithme d'évolution est conforme au sujet
//TODO:Créer l'uml
//TODO:Créer le rapport

public class Main {

    public static void main(String[] args) {
        ControleurPartie controleur = new ControleurPartie();
        TextualView textualView = new TextualView(controleur);
        textualView.start();
    }
}
