package nodos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LeitorCSV {

    public static ArrayList doLerCSV_nodos(String pCaminhoDoArquivo) throws FileNotFoundException, IOException {
        BufferedReader vReader = null;
        String vCaminhoDoArquivo = new File("src").getAbsolutePath() + "\\nodos\\nodos.csv";
        vReader = new BufferedReader(new FileReader(vCaminhoDoArquivo));
        ArrayList vListaDeNodos = new ArrayList();

        String vLinha = null;
        String[] vLinhaSeparada = null;
        int vIgnorarPrimeiraLinha = 0;

        while ((vLinha = vReader.readLine()) != null) {
            vLinhaSeparada = vLinha.split(";");

            if (vIgnorarPrimeiraLinha != 0) {
                Node nodo = new Node(vLinhaSeparada[0]);

                if (1 < vLinhaSeparada.length) {
                    nodo.vNome = vLinhaSeparada[0];
                }
                if (1 < vLinhaSeparada.length) {

                    nodo.vX = Integer.valueOf(vLinhaSeparada[1]);
                }
                if (2 < vLinhaSeparada.length) {
                    nodo.vY = Integer.valueOf(vLinhaSeparada[2]);
                }

                if (3 < vLinhaSeparada.length) {
                    String[] vNodosAdj = vLinhaSeparada[3].split("#");
                    nodo.vListVerticeAdj = new ArrayList(Arrays.asList(vNodosAdj));

                }
                vListaDeNodos.add(nodo);

            } else {
                vIgnorarPrimeiraLinha = 1;
            }
        }
        vReader.close();
        return vListaDeNodos;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Node> vListaDeNodos = LeitorCSV.doLerCSV_nodos(".\\src\\nodos\\nodos.csv");

        for (Node vNode : vListaDeNodos) {
            System.out.println("teste - " + vNode.vNome + " count nodos adj " + vNode.vListVerticeAdj.size() + " ");
        }

    }

}
