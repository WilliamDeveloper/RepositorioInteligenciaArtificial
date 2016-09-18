package nodos;

import java.util.ArrayList;
import java.util.Collections;


public class AStar {

    public static void main(String args[]) {

        Node vNodoOrigem = Global_.vListaDeNodos.get(0);
        Node vNodoDestino = Global_.vListaDeNodos.get(6);

        ArrayList<Node> vListaAbertaFronteira = new ArrayList();
        ArrayList<Node> vListaFechadaVisitados = new ArrayList();        

        doEncontrarCaminho(vNodoOrigem, vNodoDestino, vListaAbertaFronteira, vListaFechadaVisitados);

    }

//#########################################################################

    private static void doEncontrarCaminho(Node pOrigem, Node pDestino, ArrayList<Node> pListaAbertaFronteira, ArrayList<Node> pListaFechadaVisitados) {
     
        pListaAbertaFronteira.add(pOrigem);

        Node vNodePaiAtual;
        Node vNodeFinalDestino = null;

        while (pListaAbertaFronteira.isEmpty() == false) {
            Collections.sort(pListaAbertaFronteira);            
            vNodePaiAtual = pListaAbertaFronteira.remove(0);
            vNodePaiAtual.vVisited = true;
            pListaFechadaVisitados.add(vNodePaiAtual);
            System.out.println(vNodePaiAtual.vNome);

            for (int i = 0; i < vNodePaiAtual.vListVerticeAdj.size(); i++) {
                
                Node vNodoAdjacenteAtual = vNodePaiAtual.doGetVerticeAdj(i);
                
                if (vNodoAdjacenteAtual.isProcuraSeEstaNaLista(pListaFechadaVisitados) == true) {
                    continue;
                } else if (vNodoAdjacenteAtual.isProcuraSeEstaNaLista(pListaAbertaFronteira) == false) {
                    pListaAbertaFronteira.add(vNodoAdjacenteAtual);

                    vNodoAdjacenteAtual.doCalcularCusto(vNodePaiAtual, pDestino);
                    Collections.sort(pListaAbertaFronteira);
                } else {
                    double tmp_vCustoG = Node.doGetDistancia(vNodePaiAtual, vNodoAdjacenteAtual);
                    
                    if (tmp_vCustoG < vNodoAdjacenteAtual.vCalculoNode.vCustoG_PaiToAdj && tmp_vCustoG > 0) {
                        vNodoAdjacenteAtual.doCalcularCusto(vNodePaiAtual, pDestino);
                        Collections.sort(pListaAbertaFronteira);
                    }
                }
            }//fim for

            boolean tmp_nodeDestino = false;

            for (Node vNode : pListaAbertaFronteira) {
                if (vNode.isDestino()) {
                    pListaAbertaFronteira.remove(vNode);
                    pListaFechadaVisitados.add(vNode);
                    vNodeFinalDestino = vNode;
                    tmp_nodeDestino = true;
                    break;
                }
            }

            if (tmp_nodeDestino == true) {
                Node.doMostrarInfoCaminho(pOrigem,vNodeFinalDestino);
                break;
            }else{
            
            }
            

        }
    }
//################################################################

}
