package model;


import java.util.ArrayList;

/**
 * Classe qui permet de définir l'évolution des automates dans sur leur grille'
 * Est une implémentation du pattern Stratégie : "Fredkin"
 *
 */
public class AlgorithmeEvolutionFredkin implements AlgorithmeEvolution {

    //region Attributs
    private static AlgorithmeEvolutionFredkin _instance = null;
    private static String _name = "Fredkin";
    //endregion

    //region Constructeur/Singleton
    /**
     * Constructeur de la classe ALgorithmeEvolutionFredkins. Utilisé uniquement dans la méthode getInstance()
     *
     *
     */
    AlgorithmeEvolutionFredkin(){
        _instance = this;
    }

    /**
     * Méthode du design patern singleton, qui permet de prendre une seule instance de la classe AlgorithmeEvolutionFredkin
     *
     *
     * @return Nouvelle instance de l'algorithme d'évolution GOL associé à l'automate.
     *
     * @throws ExceptionInInitializerError Renvoyé si l'algorithme à déjà été associé à l'automate.
     *
     */
    public static AlgorithmeEvolutionFredkin getInstance(){
        if(_instance == null) _instance = new AlgorithmeEvolutionFredkin();
        else throw new ExceptionInInitializerError("Un automate est déjà associé à cet algorithme d'évolution");
        return  _instance;

    }
    //endregion

    //region Getters/Setters
    /**
     * Getter qui retourne le nom de l'algorithme
     *
     */
    public String getName() {
        return _name;
    }
    //endregion

    //region Méthodes d'instances
    /**
     * Méthode qui définit comment les automates s'étendent.
     * Cette méthode suit les rêgles d'évolution "Fredkin".
     *
     */
    public void evolution(Automate automate) {
        //On récupère les grille sur lesquelles l'automate à des cellules
        ArrayList<Grille> listeDesTerrains = new ArrayList<>();
        for (Cellule cellule:automate.getCellules()) {
            if(!listeDesTerrains.contains(cellule.getTerrain())){
                listeDesTerrains.add(cellule.getTerrain());
            }
        }
        
        //Pour chaque grille, on étend
        for (Grille terrain:listeDesTerrains) {
            //On crée des listes temporaires de cellules à créer et de cellules à supprimer.
            ArrayList<int[]> cellulesACreer = new ArrayList<>();
            ArrayList<Cellule> cellulesASupprimer = new ArrayList<>();

            //On regarde chaque case de la grille, et on détermine ce qu'il faut faire (faire naitre ou tuer)
            for(int y=0; y< terrain.getTaille()[1]; y++) {
                for(int x=0; x< terrain.getTaille()[0]; x++){
                    int[] position = {x,y};

                    //On regarde combien de cellules vivante autour d'elle
                    int nbCellulesVivantes = nbCellulesVivantes(automate, position, terrain);

                    //Si la cellule est morte et a exactement 1 ou 3 cellules vivantes autour, elle nait
                    ArrayList<Cellule> cellulesACetEndroit = terrain.getCellules(position);
                    cellulesACetEndroit.removeIf(cellule -> (cellule.getParent()!=automate));
                    if(cellulesACetEndroit.size() == 0 && (nbCellulesVivantes == 3 ||nbCellulesVivantes == 1)){
                        cellulesACreer.add(new int[]{x,y});
                    }

                    //Si la cellule est vivante et qu'elle ni une, ni 3 cellules vivantes autour, elle meurt
                    if(cellulesACetEndroit.size() == 1 && nbCellulesVivantes != 2 && nbCellulesVivantes != 3){
                        cellulesASupprimer.add(cellulesACetEndroit.get(0));
                    }
                }
            }
            //On crée les cellules
            for (int[] pos:cellulesACreer) {
                new Cellule(automate, terrain, pos);
            }
            //On supprime les cellules
            for(Cellule cellule: cellulesASupprimer){
                cellule.meurt();
            }
        }
    }

    /**
     * Méthode qui calcule le nombre de cellule vivante appartenant à l'automate autour de la position passée en paramètre.
     *
     * @param position Position autour de laquelle on regarde le nombre de cellules vivante.
     * @param terrain Grille sur laquelle faire la détection.
     *
     * @return Nombre de cellule vivante appartenant à l'automate autour de la position donnée.
     *
     * @throws IllegalArgumentException Si la longueur du tableau "position" n'est pas de 2.
     * @throws IndexOutOfBoundsException Si la taille est négative ou sort des bornes de la grille
     *
     */
    private int nbCellulesVivantes(Automate automate, int[] position, Grille terrain){
        if(position.length != 2){
            throw new IllegalArgumentException("La position demandé ne s'inscrit pas dans un espace de dimension 2.");
        }
        else if(position[0]<0 || position[0]>= terrain.getTaille()[0] || position[1]<0 || position[1]>= terrain.getTaille()[1]){
            throw new IndexOutOfBoundsException("La position indiquée sort des bornes de la grille");
        }

        int nbCellulesVivantes = 0;

        //En haut au milieu
        if(automate.hasCellule(new int[]{position[0], position[1]-1}, terrain)){ nbCellulesVivantes++; }

        //Milieu à gauche
        if(automate.hasCellule( new int[]{position[0]-1, position[1]}, terrain)){ nbCellulesVivantes++; }

        //Milieu à droite
        if(automate.hasCellule(new int[]{position[0]+1, position[1]}, terrain)){ nbCellulesVivantes++; }

        //En bas au milieu
        if(automate.hasCellule(new int[]{position[0], position[1]+1}, terrain)){ nbCellulesVivantes++; }

        return nbCellulesVivantes;
    }
    //endregion
}
