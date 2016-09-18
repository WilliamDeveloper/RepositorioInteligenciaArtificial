package nodos;

import java.util.*;

class Node implements Comparable<Node> {  

    public String vNome;
    public double vY;
    public double vX;
    public double vCustoF;
    public double vCustoG;
    public double vCustoH;
    public Node vNodePai;
    public boolean vVisited;

    public ArrayList<Node> vListVerticeAdj = new ArrayList<Node>();
    public ArrayList<Integer> vListVerticeAdjCusto = new ArrayList<Integer>();
    public int vDistanciaVerticeAtualParaDestino;

    //############################################################################
    public Node(String label) {
        this.vNome = label;
    }

    //############################################################################
    public boolean isDestino() {
        return (vDistanciaVerticeAtualParaDestino == 0);
    }

    //############################################################################
    public boolean isVisited() {
        return this.vVisited;
    }

    //############################################################################
    Node doGetMelhorCaminhoAdj() {
        int vIndexMelhorVerticeAdj = doGetIndexVerticeAdjComMenorCusto();
        return vListVerticeAdj.get(vIndexMelhorVerticeAdj);
    }

    //############################################################################
    public boolean isProcuraSeEstaNaLista(ArrayList<Node> vLista) {
        boolean retorno = false;
        for (Node node : vLista) {
            if (this.vNome.equals(node.vNome)) {
                retorno = true;
            }
        }
        return retorno;
    }

    //############################################################################
    int doGetIndexVerticeAdjComMenorCusto() {
        int vIndex = -1;
        int vMenorCusto = 0;

        for (int i = 0; i < this.vListVerticeAdjCusto.size(); i++) {
            int vCustoA = this.vListVerticeAdj.get(i).vDistanciaVerticeAtualParaDestino + this.vListVerticeAdjCusto.get(i);

            if (vIndex == -1) {
                vMenorCusto = vCustoA;
            }

            if (vCustoA <= vMenorCusto && this.vListVerticeAdj.get(i).isVisited() == false) {
                vIndex = i;
            }
        }

        return vIndex;
    }

    //############################################################################
    public static double doGetDistancia(Node pNodoA, Node pNodoB) {
        double vDistancia = 0.0F;
        double tmp_dif_y = (pNodoB.vY - pNodoA.vY);
        double tmp_dif_x = (pNodoB.vX - pNodoA.vX);
        double tmp_somatorio = (Math.pow(tmp_dif_y, 2) + Math.pow(tmp_dif_x, 2));

        vDistancia = Math.sqrt(tmp_somatorio);

        return vDistancia;
    }
//############################################################################

    @Override
    public int compareTo(Node vOutroNode) {
        if (this.vCustoF < vOutroNode.vCustoF) {
            return -1;
        }
        if (this.vCustoF > vOutroNode.vCustoF) {
            return 1;
        }
        return 0;
    }

    //############################################################################    
    public void doCalcularCusto(Node vNodoAtual, Node vDestino) {
        this.vNodePai = vNodoAtual;
        this.vCustoG = Node.doGetDistancia(vNodoAtual, this);
        this.vCustoH = Node.doGetDistancia(this, vDestino);
        this.vCustoF = this.vCustoG + this.vCustoH;
    }
    
//############################################################################    
     public static void doMostrarInfoCaminho(Node vNodeOrigem, Node vNodeDestino) {

        Stack<Node> pilha = new Stack<>();
        pilha.add(vNodeDestino);
        Node vNodeAnterior = vNodeDestino.vNodePai;

        System.out.println("vNodeOrigem" + vNodeOrigem.vNome);
        System.out.println("vNodeDestino" + vNodeDestino.vNome);

        while (vNodeAnterior.vNome.equals(vNodeOrigem.vNome) == false) {
            pilha.add(vNodeAnterior);
            vNodeAnterior = vNodeAnterior.vNodePai;
        }
        pilha.add(vNodeAnterior);

        double vTotalCusto = 0;
        while (pilha.isEmpty() == false) {
            Node node = pilha.pop();
            System.out.print(" -> " + node.vNome);
            vTotalCusto += node.vCustoF;
            System.out.println(" vTotalCusto " + vTotalCusto);
        }
        System.out.println("vTotalCusto " + vTotalCusto);
        System.out.println();
    }     
//############################################################################  
}
