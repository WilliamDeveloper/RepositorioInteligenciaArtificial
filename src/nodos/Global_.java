package nodos;

import java.io.File;
import java.util.ArrayList;


public class Global_ {
    public static ArrayList<Node> vListaDeNodos = LeitorCSV.doLerCSV_nodos(new File("src").getAbsolutePath() +"/nodos/nodos.csv");
}
