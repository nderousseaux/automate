import view.GestionnaireVue;

/**
 * La classe Main est la classe principale permet l'exécution du jeu
 *
 */
public class Main {
    /**
     * Méthode statique permettant d'exécuter le jeu
     */
    public static void main(String[] args) {
        // Vue textuelle (obsolète)
        /* ControleurPartie controleur = new ControleurPartie();
        Vue textuelle (obsolète) : new VueTextuelle(controleur).start();*/

        // Vue graphique
        // Appel au controleur de vue qui se charge de lancer les fenêtres successivement
        GestionnaireVue.getInstance().start();
    }
}
