package nodos;

import java.util.ArrayList;
import java.util.Collections;


public class AStar {

    public static void main(String args[]) {

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");

        ArrayList<Node> vListaAbertaFronteira = new ArrayList();
        ArrayList<Node> vListaFechadaVisitados = new ArrayList();

        n1.vDistanciaVerticeAtualParaDestino = 10;
        n1.vListVerticeAdj.add(n2);
        n1.vListVerticeAdjCusto.add(5);
        n1.vListVerticeAdj.add(n3);
        n1.vListVerticeAdjCusto.add(5);
        n1.vListVerticeAdj.add(n4);
        n1.vListVerticeAdjCusto.add(5);

        n2.vDistanciaVerticeAtualParaDestino = 15;
        n2.vListVerticeAdj.add(n5);
        n2.vListVerticeAdjCusto.add(5);

        n3.vDistanciaVerticeAtualParaDestino = 10;
        n3.vListVerticeAdj.add(n6);
        n3.vListVerticeAdjCusto.add(5);

        n4.vDistanciaVerticeAtualParaDestino = 5;
        n4.vListVerticeAdj.add(n7);
        n4.vListVerticeAdjCusto.add(5);

        n5.vDistanciaVerticeAtualParaDestino = 10;
        n5.vListVerticeAdj.add(n6);
        n5.vListVerticeAdjCusto.add(5);

        n6.vDistanciaVerticeAtualParaDestino = 5;
        n6.vListVerticeAdj.add(n7);
        n6.vListVerticeAdjCusto.add(5);

        n7.vDistanciaVerticeAtualParaDestino = 0;

        Node vOrigem = n1;
        Node vDestino = n7;

        //##################################################
        vListaAbertaFronteira.add(vOrigem);

        Node vNodePaiAtual;
        Node vNodeDestino = null;

        while (vListaAbertaFronteira.isEmpty() == false) {
            Collections.sort(vListaAbertaFronteira);            
            vNodePaiAtual = vListaAbertaFronteira.remove(0);
            vNodePaiAtual.vVisited = true;
            vListaFechadaVisitados.add(vNodePaiAtual);
            System.out.println(vNodePaiAtual.vNome);

            for (int i = 0; i < vNodePaiAtual.vListVerticeAdj.size(); i++) {
                Node vNodoAdjacenteAtual = vNodePaiAtual.vListVerticeAdj.get(i);
                if (vNodoAdjacenteAtual.isProcuraSeEstaNaLista(vListaFechadaVisitados) == true) {
                    continue;
                } else if (vNodoAdjacenteAtual.isProcuraSeEstaNaLista(vListaAbertaFronteira) == false) {
                    vListaAbertaFronteira.add(vNodoAdjacenteAtual);

                    vNodoAdjacenteAtual.doCalcularCusto(vNodePaiAtual, vDestino);
                    Collections.sort(vListaAbertaFronteira);
                } else {
                    double tmp_vCustoG = Node.doGetDistancia(vNodePaiAtual, vNodoAdjacenteAtual);
                    
                    if (tmp_vCustoG < vNodoAdjacenteAtual.vCustoG && tmp_vCustoG > 0) {
                        vNodoAdjacenteAtual.doCalcularCusto(vNodePaiAtual, vDestino);
                        Collections.sort(vListaAbertaFronteira);
                    }
                }
            }//fim for

            boolean tmp_nodeDestino = false;

            for (Node vNode : vListaAbertaFronteira) {
                if (vNode.isDestino()) {
                    vListaAbertaFronteira.remove(vNode);
                    vListaFechadaVisitados.add(vNode);
                    vNodeDestino = vNode;
                    tmp_nodeDestino = true;
                    break;
                }
            }

            if (tmp_nodeDestino == true) {
                Node.doMostrarInfoCaminho(vOrigem,vNodeDestino);
                break;
            }else{
            
            }
            

        }

    }

//#########################################################################


}
