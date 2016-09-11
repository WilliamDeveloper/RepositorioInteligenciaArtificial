
package algoritmos;

import java.util.ArrayList;

public class DFSList {
    public static final int UNVISITED = 0;
    public static final int VISITING = 1;
    public static final int FINISHED = 2;
    
    public static void dfs(ArrayList<ArrayList<Integer>> adj){
        int [] state = new int[adj.size()];
        
        for (int v = 0; v < adj.size(); ++v) {
            dfs(adj,v,state);
        }
    }
    
    public static void dfs(ArrayList<ArrayList<Integer>> adj, int v, int [] state){
        if(state[v]!= UNVISITED){
            return;
        }else{
            System.out.println("Visitando o vertice -> ["+v+"] !!!");
            state[v] = VISITING;
        }
        
        for (int w : adj.get(v)) {            
            dfs(adj,w,state);            
        }
        System.out.println("Finalizando visita ao vertice -> ["+v+"] !!!");
        state[v] = FINISHED;
    }
    
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < 3; ++i) {
            adj.add(new ArrayList<>());
        }
        
        adj.get(0).add(1);
        adj.get(0).add(2);
        
        adj.get(1).add(2);
        
        adj.get(2).add(0);
        adj.get(2).add(1);
        
        dfs(adj);
    }
}
