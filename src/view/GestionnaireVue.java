package view;


/**
 * La classe GestionnaireVue gère la vue du jeu
 * Elle est définie par :
 *  - Son instance (patron Singleton)
 *
 */
public class GestionnaireVue {
    //region Attributs
    private static GestionnaireVue _instance = null;
    //endregion

    //region Getters/Setters
    //region Relatif au gestionnaire
    /**
     * Getter de l'instance du gestionnaire
     *
     */
    public static GestionnaireVue getInstance() {
        if(_instance == null)
            _instance = new GestionnaireVue();

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
