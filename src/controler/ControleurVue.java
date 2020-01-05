package controler;

import view.FenetreIntro;
import view.FenetreParametres;
import view.FenetrePlacement;

/**
 * La classe Controler gère la vue du jeu
 * Elle est définie par :
 *  - Son instance (patron Singleton)
 *  -
 *  -
 *
 */
public class ControleurVue {
    private static ControleurVue _instance = null;

    public static ControleurVue getInstance() {
        if(_instance == null)
            _instance = new ControleurVue();

        return _instance;
    }

    /**
     * Procédure lancant les différentes étapes de la vue d'une partie
     * Elle lance successivement les quatre fenêtres (introduction, paramètres, placement des cellules et évolution)
     *
     */
    public void start() {
        ouvrirIntro();
    }

    public void ouvrirIntro() {
       new FenetreIntro();
    }

    public void ouvrirParametres() {
        new FenetreParametres();
    }

    public void ouvrirPlacement() {
        new FenetrePlacement();
    }
}
