package model;



import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //On crée la grille
        int taille[] = {5,5};
        ArrayList<Cellule> listeDesCellules = new ArrayList<>();
        Grille g = new Grille(taille, listeDesCellules, new AlgorithmeExtensionRepetition());

        //On crée les automates
        Automate aut1 = new Automate();
        Automate autr = new Automate();

        //On crée les cellules
        int pos1[] = {2,2};
        Cellule c1 = new Cellule(aut1, g, pos1);

        //On crée les cellules
        int pos4[] = {1,2};
        Cellule c4 = new Cellule(autr, g, pos4);



        //On crée le temps
        Clock c = new Clock(g, 10);
        System.out.println(g);
        while (c.fin() == null){
            c.tour();
            System.out.println(g);
        }

        System.out.println(c.fin());
    }
}
