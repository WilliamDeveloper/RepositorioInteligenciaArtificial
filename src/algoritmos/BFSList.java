package algoritmos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class BFSList {

    public static final int INICIAL = 0;
    public static final int PROCESSANDO = 1;
    public static final int FINISHED = 2;

    public static void bfs(ArrayList<ArrayList<Integer>> adj) {
        int[] state = new int[adj.size()];

        for (int v = 0; v < adj.size(); ++v) {
            bfs(adj, v, state);
        }
    }

    public static int[] bfs(ArrayList<ArrayList<Integer>> adj, int v, int[] vEstadoDoVertice) {

         //System.out.println("ja era");
        if (vEstadoDoVertice[v] != INICIAL) {
            return null;
        }

        int[] vDistancia = new int[vEstadoDoVertice.length];
        Arrays.fill(vDistancia, -1);

        vDistancia[0] = 0;

        ArrayDeque<Integer> vFilaDeVertice = new ArrayDeque<>();
        vFilaDeVertice.add(v);
        vEstadoDoVertice[v] = PROCESSANDO;

        while (!vFilaDeVertice.isEmpty()) {
            int verticeAtualVisitado = vFilaDeVertice.poll();
            System.out.println("Visitando o vertice -> [" + verticeAtualVisitado + "] !!!");

            for (int verticeVizinhoAdjacente : adj.get(verticeAtualVisitado)) {
                if (vEstadoDoVertice[verticeVizinhoAdjacente] == INICIAL) {
                    vFilaDeVertice.add(verticeVizinhoAdjacente);
                    vEstadoDoVertice[verticeVizinhoAdjacente] = PROCESSANDO;
                    vDistancia[verticeVizinhoAdjacente]++;
                }
            }
            vEstadoDoVertice[verticeAtualVisitado] = FINISHED;
            vEstadoDoVertice[v] = FINISHED;
           
        }
         System.out.println("Finalizando visita ao vertice -> [" + v + "] !!!");
        return vDistancia;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
             adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(1);
        

        bfs(adj);
    }
}
