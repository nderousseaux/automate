package view;

import controler.ControleurPartie;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe TextualView est une classe du package view, qui permet d'intéragir avec le modèle.
 * Pour lancer une partie, on lance la procèdue "Start".
 * La partie se déroule en trois phases :
 *  1- Le choix des paramètres de jeux
 *  2- Le choix de la position des cellules sur le terrain
 *  3- Le combat entre les automates et la déclaration du ou des vainqueurs.
 *
 * La classe est définie uniquement par son contrôleur.
 *
 */
public class TextualView {

    //region Attributs
    ControleurPartie _controleurPartie;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe TextualView
     *
     * @param controleurPartie Controleur que va manipuler la vue
     *
     */
    public TextualView(ControleurPartie controleurPartie){
        _controleurPartie = controleurPartie;
    }
    //endregion

    //region Méthodes d'instances

    //region Phases de jeu
    /**
     * Procédure qui fait se dérouler une partie.
     * Elle lance succèsivement les trois phases de jeu (paramétrage, placement et évolution)
     *
     */
    public void start(){
        //Message de bienvenue
        System.out.println("Bienvenue sur ce jeu de combat d'automates !");
        System.out.println("La partie se joue à deux, chaque joueur va choisir un type d'automate cellulaire et placer les cellules de départ sur la grille.");
        System.out.println("Les automates vont évoluer. Si une cellule est disputée par les automates, il y a un combat. La cellule perdante meurt");
        System.out.println("Le dernier automate en jeu est déclaré vainqueur.");
        System.out.println("\n\n Appuyez sur entrée pour continuer.");
        Scanner scanner = new Scanner(System.in);scanner.nextLine();

        //On lance le parametrage
        setting();

        //On lance placement
        placement();

        //On lance l'évolution
        evolution();

        //On affiche le ou les vainqueurs
        winners();
    }

    /**
     * Procédure qui lance la phase 1 du jeu, le choix des paramètres de jeu.
     *
     */
    private void setting(){
        //On déclare le scanner qui permettra de prendre les entrés de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        //region Grille

        //region Dimensions de la grille

        //On règle la dimension de la grille
        System.out.println("Entendez vous sur les dimensions de la grille :");
        int longueur = askInt(scanner, "Longueur : ");
        int largeur = askInt(scanner, "Largeur : ");

        System.out.println("Vous avez choisi une grille de longueur " + longueur + " et de largeur " + largeur +".");
        //endregion

        //region Extension de la grille

        System.out.println("\nMaintenant, choisissez la méthode d'expension de la grille parmit cette liste : ");
        for (AlgorithmeExtension algo: _controleurPartie.getListeAlgorithmeExtension()) {
            System.out.println(ListeAlgorithmeExtension.listeAlgorithmeExtension.indexOf(algo) + " : " + algo.getName());
        }
        int indexAlgoExtension = askInt(scanner, "Votre choix : ", 0, ListeAlgorithmeEvolution.listeAlgorithmeEvolution.size()-1);
        System.out.println("Vous avez choisi " + ListeAlgorithmeExtension.listeAlgorithmeExtension.get(indexAlgoExtension).getName() +" !");
        //endregion

        //On initialise la grille
        _controleurPartie.initGrille(longueur, largeur, indexAlgoExtension);

        //endregion

        //region Réglages partie
        int nbCelluleVivante = askInt(scanner, "\nChoisissez combien de cellules vivantes vous aurrez par personne au début de la partie :", 1, _controleurPartie.getNbCase()/2);
        _controleurPartie.setNbCelluleVivante(nbCelluleVivante);

        int nbTourMax = askInt(scanner, "\nChoisissez combien de tour durera la partie :");
        _controleurPartie.setNbTourMax(nbTourMax);
        //endregion

        //region Réglage premier automate
        System.out.println("\nJoueur 1 : Choisi ton automate");
        System.out.println("Choisissez la méthode d'expension de la grille parmit cette liste : ");
        for (AlgorithmeEvolution algo: _controleurPartie.getListeAlgorithmesEvolutionDisponible()){
            System.out.println(ListeAlgorithmeEvolution.listeAlgorithmeEvolution.indexOf(algo) + " : " + algo.getName());
        }
        int indexAlgoEvolutionJ1 = askInt(scanner, "Votre choix :", 0, _controleurPartie.getListeAlgorithmesEvolutionDisponible().size()-1);
        System.out.println("Vous avez choisi " + ListeAlgorithmeEvolution.listeAlgorithmeEvolution.get(indexAlgoEvolutionJ1).getName() +" !");
        System.out.print("Nom de l'automate (sans espaces): ");
        String nomJ1 = scanner.next();
        _controleurPartie.createAutomate(indexAlgoEvolutionJ1, nomJ1);
        //endregion

        //region Réglage second automate
        System.out.println("\nJoueur 2 : Choisi ton automate");
        System.out.println("Choisissez la méthode d'expension de la grille parmit cette liste : ");
        for (AlgorithmeEvolution algo: _controleurPartie.getListeAlgorithmesEvolutionDisponible()){
            System.out.println(_controleurPartie.getListeAlgorithmesEvolutionDisponible().indexOf(algo) + " : " + algo.getName());
        }
        int indexAlgoEvolutionJ2 = askInt(scanner, "Votre choix :", 0, _controleurPartie.getListeAlgorithmesEvolutionDisponible().size()-1);
        System.out.println("Vous avez choisi " + _controleurPartie.getListeAlgorithmesEvolutionDisponible().get(indexAlgoEvolutionJ2).getName() +" !");
        System.out.print("Nom de l'automate (sans espaces): ");
        String nomJ2 = "";
        nomJ2 += scanner.next();
        _controleurPartie.createAutomate(indexAlgoEvolutionJ2, nomJ2);
        //endregion
    }

    /**
     * Procédure qui lance la phase 2 du jeu, le placement des cellules.
     *
     */
    private void placement(){
        Scanner scanner = new Scanner(System.in);

        //On mélange la liste
        _controleurPartie.mix();

        System.out.println("C'est " + _controleurPartie.getListeDesAutomates().get(0).getName() + " qui commence !");

        //On boucle pour chaque cellule à placer
        for (int i = 0; i< _controleurPartie.getNbCelluleVivante(); i++) {
            //On boucle dans tout les automates
            for (Automate automate: _controleurPartie.getListeDesAutomates()) {
                while(true){
                    try{
                        System.out.println(automate.getName() + " , placez une cellule : ");
                        int x = askInt(scanner, "Position en x : ", 0, _controleurPartie.getDimensionX());
                        int y = askInt(scanner, "Position en y : ", 0, _controleurPartie.getDimensionY());
                        _controleurPartie.addCellule(x, y, automate);
                        break;
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("La place est déjà prise !");
                    }
                    finally {
                        System.out.println(_controleurPartie.stringGrille());
                    }
                }



            }
        }
    }

    /**
     * Procédure qui lance la phase 3 du jeu, l'évolution.
     *
     */
    private void evolution(){
        //Tant que ce n'est pas la fin, on passe un tour, et on affiche la grille
        while(_controleurPartie.IsFin() == null){
            _controleurPartie.nextTour();
            System.out.println("Tour n°"+ _controleurPartie.getCurrentTour() + "/" + _controleurPartie.getNbTourMax());
            System.out.println(_controleurPartie.stringGrille());
        }
    }

    /**
     * Procèdure qui affiche le ou les vainqueurs.
     *
     */
    private void winners(){
        //On affiche une dernière fois la grille
        System.out.println(_controleurPartie.stringGrille());

        //On déclare le vainqueur
        ArrayList<Automate> vainqueurs = _controleurPartie.IsFin();
        //Si la liste et vide, match nul.
        if(vainqueurs.size() == 0){
            System.out.println("Match nul");
        }
        else if(vainqueurs.size() == 1){
            System.out.println("Le vainqueur est "+ vainqueurs.get(0).getName());
        }
        else{
            System.out.println("Les vainqueurs à égalité sont :");
            for (Automate automate:vainqueurs) {
                System.out.println(automate.getName());
            }
        }
    }
    //endregion

    //region Autres méthodes
    /**
     * Méthode qui demande un int à l'utilisateur, et le redemande tant qu'il ne l'à pas.
     *
     * @param scanner Scanner qui servera à récupérer l'int de l'utilisateur.
     * @param sentence Phrase qu'affichera la fonction à l'utilisateur avant de demander l'int.
     * @param min Borne minimale de l'int (excluse).
     * @param max Borne maximale de l'int (excluse).
     *
     * @return int, entrée par l'utilisateur.
     *
     */
    private int askInt(Scanner scanner, String sentence, int min, int max){
        int entree;
        //Tant que l'entré ne correspond pas aux critères, on repose la question
        while(true){
            try{
                //On écrit la phrase
                System.out.print(sentence);

                //On demande l'entrée à l'utilisateur
                entree = Integer.parseInt(scanner.next());

                //Si elle n'est pas correcte, on soulève une exception
                if(entree < min || entree > max){
                    throw new IllegalArgumentException("L'entrée sort des bornes demandées");
                }

                //Si le code est arrivé jusque là, on peut sortir de la boucle.
                break;
            }
            catch (Exception e){
                System.out.println("Erreur :" + e.getMessage());
            }
        }

        return entree;
    }

    /**
     * Méthode qui demande un int à l'utilisateur, et le redemande tant qu'il ne l'à pas.
     *
     * @param scanner Scanner qui servera à récupérer l'int de l'utilisateur.
     * @param sentence Phrase qu'affichera la fonction à l'utilisateur avant de demander l'int.
     *
     * @return int, entrée par l'utilisateur.
     *
     */
    private int askInt(Scanner scanner, String sentence){
        int entree;

        //Tant que l'entré ne correspond pas aux critères, on repose la question
        while(true){
            try{
                //On écrit la phrase
                System.out.print(sentence);

                //On demande l'entrée à l'utilisateur
                entree = Integer.parseInt(scanner.next());

                //Si elle n'est pas correcte, on soulève une exception
                if(entree < 1){
                    throw new IllegalArgumentException("L'entrée sort des bornes demandées");
                }

                //Si le code est arrivé jusque là, on peut sortir de la boucle.
                break;
            }
            catch (Exception e){
                System.out.println("Erreur :" + e.getMessage());
            }
        }

        return entree;
    }
    //endregion

    //endregion

}
