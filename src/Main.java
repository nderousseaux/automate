import model.Automate;
import model.Cellule;
import model.Grille;
import model.Temp;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //On crée la grille
        int taille[] = {5,5};
        ArrayList<Cellule> listeDesCellules = new ArrayList<>();
        Grille g = new Grille(taille, listeDesCellules);

        //On crée les automates
        Automate aut1 = new Automate();
        Automate aut2 = new Automate();

        //On crée les cellules
        int pos1[] = {1,1};
        Cellule c1 = new Cellule(aut1, g, pos1); g._listeDesCellules.add(c1);
        int pos2[] = {0,1};
        Cellule c2 = new Cellule(aut1, g, pos2); g._listeDesCellules.add(c2);
        int pos3[] = {0,2};
        Cellule c3 = new Cellule(aut1, g, pos3); g._listeDesCellules.add(c3);

        int pos4[] = {2,1};
        Cellule c4 = new Cellule(aut2, g, pos4); g._listeDesCellules.add(c4);
        int pos5[] = {2,2};
        Cellule c5 = new Cellule(aut2, g, pos5); g._listeDesCellules.add(c5);
        int pos6[] = {3,2};
        Cellule c6 = new Cellule(aut2, g, pos6); g._listeDesCellules.add(c6);

        //On crée le temps
        Temp c = new Temp(g);

        System.out.println(g);
        c.tour();
        System.out.println(g);
        c.tour();
        System.out.println(g);


    }
}
