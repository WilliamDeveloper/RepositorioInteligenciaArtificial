package nodos;

import java.util.*;

class CalculoNode {

    public Node vNodePai;
    public double vCustoF;
    public double vCustoG_PaiToAdj;
    public double vCustoH_AdjToAlvo;

}

class Node implements Comparable<Node> {

    public String vNome;
    public double vY;
    public double vX;
    public CalculoNode vCalculoNode;
    
    public boolean vVisited;

    //public ArrayList<Node> vListVerticeAdj = new ArrayList<Node>();
    public ArrayList<String> vListVerticeAdj = new ArrayList<String>();
    public ArrayList<Integer> vListVerticeAdjCusto = new ArrayList<Integer>();
    public int vDistanciaVerticeAtualParaDestino2;

    //############################################################################
    public Node(String label) {
        this.vNome = label;
        this.vCalculoNode = new CalculoNode();
    }

    //############################################################################
    public boolean isDestino() {
        //return (vDistanciaVerticeAtualParaDestino == 0);
        return (this.vCalculoNode.vCustoH_AdjToAlvo == 0);
    }

    //############################################################################
    public boolean isVisited() {
        return this.vVisited;
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
    @Override
    public int compareTo(Node vOutroNode) {
        if (this.vCalculoNode.vCustoF < vOutroNode.vCalculoNode.vCustoF) {
            return -1;
        }
        if (this.vCalculoNode.vCustoF > vOutroNode.vCalculoNode.vCustoF) {
            return 1;
        }
        return 0;
    }

    //############################################################################    
    public void doCalcularCusto(Node vNodePaiAtual, Node vNodeDestino) {
        this.vCalculoNode.vNodePai = vNodePaiAtual;
        this.vCalculoNode.vCustoG_PaiToAdj = Node.doGetDistancia(vNodePaiAtual, this);
        this.vCalculoNode.vCustoH_AdjToAlvo = Node.doGetDistancia(this, vNodeDestino);
        this.vCalculoNode.vCustoF = this.vCalculoNode.vCustoG_PaiToAdj + this.vCalculoNode.vCustoH_AdjToAlvo;
    }

//############################################################################    
    public static void doMostrarInfoCaminho(Node vNodeOrigem, Node vNodeDestino) {

        Stack<Node> pilha = new Stack<>();
        pilha.add(vNodeDestino);
        Node vNodeAnterior = vNodeDestino.vCalculoNode.vNodePai;

        System.out.println("vNodeOrigem" + vNodeOrigem.vNome);
        System.out.println("vNodeDestino" + vNodeDestino.vNome);

        while (vNodeAnterior.vNome.equals(vNodeOrigem.vNome) == false) {
            pilha.add(vNodeAnterior);
            vNodeAnterior = vNodeAnterior.vCalculoNode.vNodePai;
        }
        pilha.add(vNodeAnterior);

        double vTotalCusto = 0;
        while (pilha.isEmpty() == false) {
            Node node = pilha.pop();
            System.out.print(" -> " + node.vNome);
            vTotalCusto += node.vCalculoNode.vCustoF;
            System.out.println(" vTotalCusto " + vTotalCusto);
        }
        System.out.println("vTotalCusto " + vTotalCusto);
        System.out.println();
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
//#########################################################################

    Node doGetVerticeAdj(int i) {
        int vNomeVertice  = Integer.valueOf(this.vListVerticeAdj.get(i));
        return Global_.vListaDeNodos.get(vNomeVertice);
    }
    
}
