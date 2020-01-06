import controler.ControleurPartie;
import controler.ControleurVue;

//TODO:Vérifier si la méthode algorithme d'évolution est conforme au sujet
//TODO:Créer l'uml
//TODO:Créer le rapport

public class Main {

    public static void main(String[] args) {
        ControleurPartie controleur = new ControleurPartie();
        // Vue textuelle (obsolète) : new VueTextuelle(controleur).start();

        ControleurVue.getInstance().start();
    }
}
