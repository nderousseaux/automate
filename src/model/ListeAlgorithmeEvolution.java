package model;

import java.util.ArrayList;

public abstract class ListeAlgorithmeEvolution {

    public static ArrayList<AlgorithmeEvolution> _listeAlgorithmeEvolution = new ArrayList<>() {{
        add(new AlgorithmeEvolutionGOL());
        add(new AlgorithmeEvolutionFredkin());
    }};

}
