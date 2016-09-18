package nodos;

import java.util.ArrayList;
import java.util.Collections;

public class AStar {

    public static void main(String args[]) {

        Node vNodoOrigem = Global_.vListaDeNodos.get(0);
        Node vNodoDestino = Global_.vListaDeNodos.get(6);

        ArrayList<Node> vListaAbertaFronteira = new ArrayList();
        ArrayList<Node> vListaFechadaVisitados = new ArrayList();

        AStar.doEncontrarCaminho(vNodoOrigem, vNodoDestino, vListaAbertaFronteira, vListaFechadaVisitados);

    }

//#########################################################################
    public static void doEncontrarCaminho(Node pOrigem, Node pDestino, ArrayList<Node> pListaAbertaFronteira, ArrayList<Node> pListaFechadaVisitados) {
        pOrigem.doCalcularCusto(pOrigem, pDestino);
        pListaAbertaFronteira.add(pOrigem);

        Node vNodePaiAtual;
        Node vNodeFinalDestino = null;

        while (pListaAbertaFronteira.isEmpty() == false) {

            vNodePaiAtual = doGetNodeComMenorCustoF(pListaAbertaFronteira);

            vNodePaiAtual.doLogMostrarMyValues();//teste

            if (vNodePaiAtual.isDestino()) {
                System.out.println("###########-> destino <-##################");
                vNodePaiAtual.doLogMostrarMyValues();//teste
                System.out.println("##########################################");
                return;
            } else {
                Node vNodeFinalDesttino = doForEachSucessorAdjAoNodePai(vNodePaiAtual, pListaAbertaFronteira, pListaFechadaVisitados, pDestino);
                if (vNodeFinalDesttino != null) {
                    //exibir caminho
                    System.out.println("//exibir caminho percorrido!!");
                    Node.doMostrarInfoCaminho(pOrigem, vNodeFinalDesttino);
                    return;
                }
                pListaFechadaVisitados.add(vNodePaiAtual);
                vNodePaiAtual.vVisited = true;
            }
        }
    }
    
//################################################################
    private static Node doGetNodeComMenorCustoF(ArrayList<Node> pListaAbertaFronteira) {
        Collections.sort(pListaAbertaFronteira);
        Node vNode = pListaAbertaFronteira.remove(0);
        return vNode;
    }
//################################################################

    private static Node doForEachSucessorAdjAoNodePai(Node vNodePaiAtual, ArrayList<Node> pListaAbertaFronteira, ArrayList<Node> pListaFechadaVisitados, Node pDestino) {
        for (int i = 0; i < vNodePaiAtual.vListNodeSucessorAdj.size(); i++) {

            Node vNodeSucessorAdjAtual = vNodePaiAtual.doGetVerticeAdj(i);
            vNodeSucessorAdjAtual.doCalcularCusto(vNodePaiAtual, pDestino);
            Collections.sort(pListaAbertaFronteira);

            if (vNodeSucessorAdjAtual.isDestino()) {
                System.out.println("###########-> destino <-##################");
                vNodeSucessorAdjAtual.doLogMostrarMyValues();//teste
                System.out.println("##########################################");
                
                return vNodeSucessorAdjAtual;
            }

            if (vNodeSucessorAdjAtual.isProcuraSeEstaNaLista(pListaFechadaVisitados) == true) {
                continue;
            } else if (vNodeSucessorAdjAtual.isProcuraSeEstaNaLista(pListaAbertaFronteira) == false) {
                pListaAbertaFronteira.add(vNodeSucessorAdjAtual);
            }
        }//fim for
        return null;
    }

}
