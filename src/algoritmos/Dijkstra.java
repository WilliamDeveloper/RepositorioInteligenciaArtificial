package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra
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
	public static double[] dijkstra(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs, int v)
	{
		int N = adj.size();
		double[] ret = new double[N];
		Arrays.fill(ret, Double.POSITIVE_INFINITY);
		PriorityQueue<Item> pq = new PriorityQueue<>();
		pq.add(new Item(0, v));
		while (!pq.isEmpty())
		{
			Item it = pq.poll();
			if (!Double.isInfinite(ret[it.v])) continue;
			//When we remove a vertex for the first time
			//Set its value in ret
			ret[it.v] = it.cost;
			ArrayList<Integer> nbrs = adj.get(it.v);
			double cost = it.cost;
			for (int i = 0; i < nbrs.size(); ++i) {
				int w = nbrs.get(i);
				double edgeCost = costs.get(it.v).get(i);
				pq.add(new Item(cost + edgeCost, w));
			}
		}
		return ret;
	}
	
	//Returns -1 if all -1.  Otherwise returns index of smallest non-negative.
	static int findMin(double[] pq)
	{
		int ind = -1; 
		for (int i = 0; i < pq.length; ++i)
			if (pq[i] >= 0 && (ind == -1 || pq[i] < pq[ind]))
				ind = i;
		return ind;
	}
	//Uses an array as the priority queue instead of a binary heap.
	public static double[] dijkstra2(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs, int v)
	{
		int N = adj.size();
		double[] ret = new double[N];
		Arrays.fill(ret, Double.POSITIVE_INFINITY);
		double[] pq = new double[N];
		Arrays.fill(pq,-1);
		pq[v] = 0;
		int minInd = v;
		while (minInd != -1)
		{
			ret[minInd] = pq[minInd];
			pq[minInd] = -1;
			for (int i = 0; i < adj.get(minInd).size(); ++i)
			{
				int w = adj.get(minInd).get(i);
				if (Double.isInfinite(ret[w]))
				{
					double newVal = ret[minInd] + costs.get(minInd).get(i);
					if (pq[w] == -1 || newVal < pq[w])
						pq[w] = newVal;
				}
			}
			minInd = findMin(pq);
		}
		return ret;
	}
	public static void main(String[] args)
	{
		int N = 5;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		ArrayList<ArrayList<Double>> costs = new ArrayList<>();
		for (int i = 0; i < N; ++i) adj.add(new ArrayList<>());
		for (int i = 0; i < N; ++i) costs.add(new ArrayList<>());
		adj.get(0).add(1); adj.get(0).add(2); 
		adj.get(1).add(3); adj.get(2).add(3);
		costs.get(0).add(3.0); costs.get(0).add(2.0); 
		costs.get(1).add(6.0); costs.get(2).add(6.5);
		double[] ds1 = dijkstra(adj,costs,0);
		double[] ds2 = dijkstra2(adj,costs,0);
		System.out.println(Arrays.toString(ds1));
		System.out.println(Arrays.toString(ds2));
	}
}
