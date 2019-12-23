package controler;

import java.util.ArrayList;
import java.util.Collections;

import model.*;

/**
 * La classe Controler gère la partie
 * Elle est définie par :
 *  - La grille qu'elle gère
 *  - Le nombre de tour déjà écoulé
 *  - Le nombre de tour maximum
 *
 */
public class Controler {

    //region Attributs
    private int _currentTour;
    private int _nbTourMax;
    private Grille _grille;
    private int _nbCellulesVivantes;
    private ArrayList<Automate> _listeDesAutomates = new ArrayList<>();
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe Controler.
     *
     */
    public Controler(){
    }
    //endregion

    //region Getters/Setters
    //region Relatif au controleur
    /**
     * Getter du numéro du tour courrant
     *
     */
    public int getCurrentTour(){
        return _currentTour;
    }

    /**
     * Getter du nombre de tour maximum
     *
     */
    public int getNbTourMax(){
        return _nbTourMax;
    }

    /**
     * Setter du nombre de tours maximum
     *
     * @param nbTourMax Nouveau nombre de tours maximum
     */
    public void setNbTourMax(int nbTourMax){
        _nbTourMax = nbTourMax;
    }

    /**
     * Getter de la liste des automates en jeu
     *
     */
    public ArrayList<Automate> getListeDesAutomates() {
        return _listeDesAutomates;
    }

    /**
     * Getter de tout les algorithmes d'évolution non encore séléctionnés.
     *
     * @return ArrayList d'algorithme, la liste des algorithmes d'évolution pas encore séléctionnés
     *
     */
    public ArrayList<AlgorithmeEvolution> getListeAlgorithmesEvolutionDisponible(){
        ArrayList<AlgorithmeEvolution> retour = (ArrayList<AlgorithmeEvolution>) ListeAlgorithmeEvolution.listeAlgorithmeEvolution.clone();
        for (Automate automate:_listeDesAutomates) {
            retour.remove(automate.getAlgoEvolution());
        }
        return retour;
    }

    /**
     * Getter qui renvoie le nombre de cellules vivantes au départ par joueur.
     *
     */
    public int getNbCelluleVivante(){
        return _nbCellulesVivantes;
    }

    /**
     * Setter réglant le nombre de Cellule vivante au départ par joueur
     *
     */
    public void setNbCelluleVivante(int nbCelluleVivante){
        _nbCellulesVivantes = nbCelluleVivante;
    }
    //endregion

    //region Relatif au model
    /**
     * Setter qui renvoie la dimension X de la grille
     *
     */
    public int getDimensionX(){
        return _grille.getTaille()[0];
    }

    /**
     * Setter qui renvoie la dimension Y de la grille
     *
     */
    public int getDimensionY(){
        return _grille.getTaille()[1];
    }


    /**
     * Getter qui retourne la liste des algorithme d'extention disponible
     *
     * @return Arraylist des algorithme d'extension contenu dans le modèle
     *
     */
    public ArrayList<AlgorithmeExtension> getListeAlgorithmeExtension(){
        return ListeAlgorithmeExtension.listeAlgorithmeExtension;
    }

    /**
     * Geter qui renvoie le nombre de cellules disponible sur la grille
     *
     */
    public int getNbCase(){
        int x = _grille.getTaille()[0];
        int y = _grille.getTaille()[1];
        return x*y;
    }
    //endregion
    //endregion

    //region Méthodes d'instance
    //region Méthodes relatives au controlleur
    /**
     * Procèdure qui mélange la liste des automates, pour décider aléatoirement de qui commence
     *
     */
    public void mix(){
        Collections.shuffle(_listeDesAutomates);
    }

    /**
     * Méthode qui initialise la grille avec sa taille, et son algorithme d'expension
     *
     * @param tailleX Longueur de la grille.
     * @param tailleY Largeur de la grille.
     * @param indexAlgorithmeExtension Algorithme d'extension associé à la grille
     *
     */
    public void initGrille(int tailleX, int tailleY, int indexAlgorithmeExtension){
        _grille = new Grille(new int[]{tailleX, tailleY}, new ArrayList<>(), ListeAlgorithmeExtension.listeAlgorithmeExtension.get(indexAlgorithmeExtension));
    }

    /**
     * Méthode qui fait passer un tour.
     * Un tour est composé deux actions dans l'ordre :
     *  1 - Etendre chaque automate
     *  2 - Faire combatres les cellules qui sont sur la même case.
     *
     */
    public void nextTour(){
        _currentTour +=1;

        //On étend chaque automate
        ArrayList<Automate> listeDesAutomates = _grille.getAutomatesEnJeu();
        for (Automate automate:listeDesAutomates) {
            automate.evolution();
        }

        //On les fait se combatre
        combat();
    }

    /**
     * Méthode qui fait combatre les automates.
     * Si une case est disputé par plusieurs automates, un tirage au sort décidera quelle sera la cellule gagnante.
     *
     */
    private void combat(){
        //On parcourt toutes les cases de la grille
        for(int y=0; y<= _grille.getTaille()[1]; y++) {
            for(int x=0; x<= _grille.getTaille()[0]; x++){
                int[] position = {x,y};

                //On liste toutes les cellules sur la case
                ArrayList<Cellule> cellulesSurLaPosition = new ArrayList<>();
                for(Cellule cellule:_grille.getListeCellules()){
                    if(cellule.getPosition()[0] == position[0] && cellule.getPosition()[1] == position[1]){
                        cellulesSurLaPosition.add(cellule);
                    }
                }

                //Si le nombre de cellule est supérieur 1, combat.
                if(cellulesSurLaPosition.size() >1){
                    //On choisi l'index de la survivante au hasard
                    int indexVivante = (int)(Math.random() * cellulesSurLaPosition.size());
                    //On tue toutes les autres
                    Cellule celluleSurvivante = cellulesSurLaPosition.get(indexVivante);
                    for (Cellule cellule:cellulesSurLaPosition) {
                        if(cellule != celluleSurvivante){
                            cellule.meurt();
                        }
                    }
                }
            }
        }
    }

    /**
     * Méthode qui annonce la fin de la partie.
     * La partie est finie si il ne reste qu'un automate vivant ou que le nombre de tour maximum à été atteint.
     * Tant que cette méthode renvoie null, la partie n'est pas finie.
     *
     * @return La liste des automates vainqueurs. Vide en cas de match nul.
     *
     */
    public ArrayList<Automate> IsFin(){
        ArrayList<Automate> listeDesAutomates = _grille.getAutomatesEnJeu();

        ArrayList<Automate> gagnant;

        //Si il n'y a plus d'automates, match null
        if(listeDesAutomates.size() == 0){
            gagnant = new ArrayList<>();
        }
        //Si il n'y en a plus qu'un, il est vainqueur
        else if(listeDesAutomates.size() == 1){
            gagnant = new ArrayList<>();
            gagnant.add(listeDesAutomates.get(0));
        }
        //Si les tours sont finit, l'automate avec le plus de cellules gagne
        else if(_currentTour >=_nbTourMax){
            //On cherche le nombre maximum de cellule
            int nbMax = listeDesAutomates.get(0).getCellules().size();
            for (Automate auto : listeDesAutomates) {
                if(auto.getCellules().size()>nbMax){
                    nbMax = auto.getCellules().size();
                }
            }

            //On trouve tous les automates gagnants
            int finalNbMax = nbMax;
            listeDesAutomates.removeIf(a->(a.getCellules().size() < finalNbMax));
            gagnant = listeDesAutomates;
        }
        //Sinon, la partie n'est pas encore finie
        else{
            gagnant = null;
        }

        return gagnant;
    }
    //endregion

    //region Méthodes relatives au modèle.
    /**
     * Procèdure qui crée une cellule dans le modèle
     *
     * @param x Position en x de la cellule
     * @param y Position en y de la cellule
     * @param automate Automate parent de la cellule
     */
    public void addCellule(int x, int y, Automate automate){
        int[] pos = {x, y};
        if(_grille.getCellules(pos).size() != 0){
            throw new IllegalArgumentException("La place est déjà prise !");
        }
        new Cellule(automate, _grille, pos);
    }

    /**
     * Procèdure qui crée un automate avec son algorithme d'évolution et son nom.
     *
     * @param indexAlgorithmeEvolution Algorithme d'évolution de l'automate
     * @param name Nom de l'automate
     */
    public void createAutomate(int indexAlgorithmeEvolution, String name){
        _listeDesAutomates.add(new Automate(getListeAlgorithmesEvolutionDisponible().get(indexAlgorithmeEvolution), name));
    }

    /**
     * Fonction qui renvoie une représentation textuelle de la grille. Utilisé dans Textual View
     *
     * @return String, reprèsentation textuelle de la grille.
     *
     */
    public String stringGrille(){
        return _grille.toString();
    }
    //endregion
    //endregion


}
