package controler;

import view.FenetreEvolution;
import view.FenetreIntro;
import view.FenetreParametres;
import view.FenetrePlacement;


/**
 * La classe ControleurVue gère la vue du jeu
 * Elle est définie par :
 *  - Son instance (patron Singleton)
 *
 */
public class ControleurVue {
    //region Attributs
    private static ControleurVue _instance = null;
    //endregion

    //region Getters/Setters
    //region Relatif au controleur
    /**
     * Getter de l'instance du controleur
     *
     */
    public static ControleurVue getInstance() {
        if(_instance == null)
            _instance = new ControleurVue();

        return _instance;
    }
    //endregion

    //region Méthodes d'instance
    /**
     * Ces procédures permettent de lancer les différentes fenêtres composant le jeu
     * A savoir l'introduction, les paramètres, le placement des cellules intitiales et enfin l'évolution des automates)
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
    public void ouvrirEvolution() { new FenetreEvolution(); }
    //endregion
}
