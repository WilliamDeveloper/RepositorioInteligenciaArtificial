
package calculos;

import java.util.List;


public class Nodo {
    public int x;
    public int y;
    public List<String> listaNodosAdjacentes;

    public Nodo(int x, int y, List<String> listaNodosAdjacentes) {
        this.x = x;
        this.y = y;
        this.listaNodosAdjacentes = listaNodosAdjacentes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<String> getListaNodosAdjacentes() {
        return listaNodosAdjacentes;
    }

    public void setListaNodosAdjacentes(List<String> listaNodosAdjacentes) {
        this.listaNodosAdjacentes = listaNodosAdjacentes;
    }
}
