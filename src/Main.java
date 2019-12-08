import javafx.scene.control.Cell;
import model.Automate;
import model.Cellule;
import model.Grille;
import model.Temp;


import javax.sound.sampled.spi.AudioFileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //On crée la grille
        int taille[] = {5,5};
        ArrayList<Cellule> listeDesCellules = new ArrayList<>();
        Grille g = new Grille(taille, listeDesCellules);

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
        Temp c = new Temp(g, 10);
        System.out.println(g);
        while (c.fin() == null){
            c.tour();
            System.out.println(g);
        }

        System.out.println(c.fin());
    }
}
