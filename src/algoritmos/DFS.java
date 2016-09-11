package algoritmos;

public class DFS {

    public static final int NAO_VISITADO = 0;
    public static final int VISITANDO = 1;
    public static final int VISITA_TERMINADA = 2;
    public static final boolean ADJACENTE_ESTA_CONECTADO = true;

    public static void dfs(boolean[][] pListaDeVertice) {
        int[] vEstadoDoVertice = new int[pListaDeVertice.length];

        for (int vVerticeAtual = 0; vVerticeAtual < pListaDeVertice.length; ++vVerticeAtual) {
            dfs(pListaDeVertice, vVerticeAtual, vEstadoDoVertice);
        }
    }

    public static void dfs(boolean[][] pListaDeVertice, int pVerticeAtual, int[] pEstadoDoVertice) {
        if (pEstadoDoVertice[pVerticeAtual] != NAO_VISITADO) {
            return;
        }
        
        System.out.println("Visitando o vertice -> [" + pVerticeAtual + "] !!!");
        pEstadoDoVertice[pVerticeAtual] = VISITANDO;

        for (int vVerticeJ = 0; vVerticeJ < pListaDeVertice.length; ++vVerticeJ) {
            if (pListaDeVertice[pVerticeAtual][vVerticeJ] == ADJACENTE_ESTA_CONECTADO) {
                dfs(pListaDeVertice, vVerticeJ, pEstadoDoVertice);
            }
        }
        System.out.println("Finalizando visita ao vertice -> [" + pVerticeAtual + "] !!!");
        pEstadoDoVertice[pVerticeAtual] = VISITA_TERMINADA;
    }

    public static void main(String[] args) {
        boolean[][] adj = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };
        dfs(adj);
    }
}
