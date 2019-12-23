package model;

import java.util.ArrayList;

public class ListeAlgorithmeEvolution {

    public static ArrayList<AlgorithmeEvolution> listeAlgorithmeEvolution = new ArrayList<>() {{
        add(AlgorithmeEvolutionGOL.getInstance());
        add(AlgorithmeEvolutionFredkin.getInstance());
    }};

}
