
package algoritmos;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim
{
	static class Item implements Comparable<Item>
	{
		private double cost;
		private int v;
		public Item(double c, int v)
		{
			this.cost = c;
			this.v = v;
		}
		@Override
		public int compareTo(Item o) { return Double.compare(cost, o.cost); }
	}
	public static double prim(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs)
	{
		int N = adj.size(), cnt = 0;
		boolean[] done = new boolean[N];
		double ret = 0;
		PriorityQueue<Item> pq = new PriorityQueue<>();
		pq.add(new Item(0, 0));
		while (!pq.isEmpty())
		{
			Item it = pq.poll();
			if (done[it.v]) continue;
			done[it.v] = true;
			cnt++;
			ret += it.cost;
			for (int i = 0; i < adj.get(it.v).size(); ++i) {
				int x = adj.get(it.v).get(i);
				double newVal = costs.get(it.v).get(i);
				pq.add(new Item(newVal, x));
			}
		}
		return cnt == N ? ret : Double.POSITIVE_INFINITY;
	}
}

