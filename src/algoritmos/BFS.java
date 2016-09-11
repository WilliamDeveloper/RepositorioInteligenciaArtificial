package algoritmos;

import java.util.ArrayDeque;

public class BFS {

    public static final int INICIAL_AINDA_NAO_VISITADO = 0;
    public static final int PROCESSANDO = 1;
    public static final int FINISHED = 2;
    public static boolean ADJACENTE_ESTA_CONECTADO = true;

    public static void bfs(boolean[][] pListaDeVertice) {
        int[] vEstadoDoVertice = new int[pListaDeVertice.length];

        for (int vVerticeAtual = 0; vVerticeAtual < pListaDeVertice.length; ++vVerticeAtual) {
            bfs(pListaDeVertice, vVerticeAtual, vEstadoDoVertice);
        }
    }

    public static void bfs(boolean[][] pListaDeVertice, int pVerticeAtual, int[] pEstadoDoVertice) {

        if (pEstadoDoVertice[pVerticeAtual] != INICIAL_AINDA_NAO_VISITADO) {
            return;
        }

        ArrayDeque<Integer> vFilaDeVertice = new ArrayDeque<>();
        vFilaDeVertice.add(pVerticeAtual);
        pEstadoDoVertice[pVerticeAtual] = PROCESSANDO; 

        while (!vFilaDeVertice.isEmpty()) {
            int vVerticeAtual = vFilaDeVertice.poll();
            System.out.println("Visitando o vertice -> [" + vVerticeAtual + "] !!!");

            for (int vVerticeJ = 0; vVerticeJ < pListaDeVertice.length; vVerticeJ++) {
                if (pListaDeVertice[vVerticeAtual][vVerticeJ] == ADJACENTE_ESTA_CONECTADO) {
                    if (pEstadoDoVertice[vVerticeJ] == INICIAL_AINDA_NAO_VISITADO) {
                        vFilaDeVertice.add(vVerticeJ);
                        pEstadoDoVertice[vVerticeJ] = PROCESSANDO;
                    }
                }
            }

        }

        pEstadoDoVertice[pVerticeAtual] = FINISHED;
        System.out.println("Finalizando visita ao vertice -> [" + pVerticeAtual + "] !!!");

    }

    public static void main(String[] args) {
        boolean[][] adj = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };
        bfs(adj);
    }
}
