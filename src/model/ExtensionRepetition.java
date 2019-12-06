package model;
import java.util.ArrayList;

public class ExtensionRepetition implements MethodeExtension {
    // METHODE D'EXTENSION "REPETITION"
    // Voisinage par défaut :
    // X X X
    // X o X
    // X X X

    // Voisinage en bord de grille :
    // | a b c . . . x y z | ---> a a a | a b c ... x y z | z z z

    public ArrayList<Cellule> voisinage(Grille grille, int[] pos) {
        ArrayList<Cellule> voisinage = new ArrayList<Cellule>();
        int x = pos[0];
        int y = pos[1];

        // Voisinage par défaut (pas en bord de grille) :
        if((x + 1 < grille.getTaille()[0] || x - 1 > 0) && (y + 1  < grille.getTaille()[1] || y - 1 > 0)) {
            for(Cellule cel : grille.getListeCellules()) {
                // Remplir l'arraylist resultat avec les cellules à récupérer
                
                // Cellules à récupérer :
                // x-1, y-1
                // x, y-1
                // x+1, y-1
                // x+1, y
                // x+1, y+1
                // x, y-1
                // x-1, y+1
                // x-1, y
            }

        } else {
            // Voisinage en bord de grille
            // Créer une copie de la grille et l'agrandir tant que le voisinage n'est pas compris dedans
            // remplir l'arraylist résultat une fois que le voisinage est compris dedans

        }


        return voisinage;
    }
}
