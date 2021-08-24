package model;

import java.util.ArrayList;

public abstract class ListeAlgorithmeExtension {

    public static ArrayList<AlgorithmeExtension> listeAlgorithmeExtension = new ArrayList<>() {{
        add(new AlgorithmeExtensionRepetition());
    }};

}
