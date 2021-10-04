/**
 * Sortest Path in Directed Graph (DAG) 
 * (using DFS)
 */

import java.util.*; 


/**
 * Since it is a weigted graph we have to store the adj list values in pair
 * Pair of value and weight
 */
class Pair
{
    private int v;
    private int weight;
    Pair(int _v, int _w) { v = _v; weight = _w; }
    int getV() { return v; }
    int getWeight() { return weight; }
}


class QS12_shortestPathDAG
{
    /**
     * To find the topological sort using 
     */
    void topologicalSortUtil(int node, Boolean visited[], Stack<Integer> stack, ArrayList<ArrayList<Pair>> adj)
    {

        visited[node] = true;
        for(Pair it: adj.get(node)) {
            if(visited[it.getV()] == false) {
                topologicalSortUtil(it.getV(), visited, stack, adj);
            }
        }
        stack.add(node);
    }


    void shortestPath(int source, ArrayList<ArrayList<Pair>> adj, int N)
    {

        Stack<Integer> stack = new Stack<>();   // Take a stack of integer    

        Boolean visited[] = new Boolean[N];     // Take a visited array of N size and mark everything as false
        for (int i = 0; i < N; i++)             
            visited[i] = false;



        // STEP 1 : Perform Topological sort
        for (int i = 0; i < N; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack, adj);

        // STEP 2 : Take a distance array and mark everything as +infinity    
        int dist[] = new int[N];     
        for (int i = 0; i < N; i++)
            dist[i] = Integer.MAX_VALUE;

        // mark dist[source] as 0 bcz the distance from source to source is always 0    
        dist[source] = 0;

        while (stack.empty() == false) //itrate for every elements of stack
        {
            int node = (int)stack.pop();

            // if the node has been reached previously then you can itrate for all its adjacent nodes
            if (dist[node] != Integer.MAX_VALUE)
            {
                for(Pair it: adj.get(node)) {
                    if(dist[node] + it.getWeight() < dist[it.getV()]) {
                        dist[it.getV()] = dist[node] + it.getWeight(); 
                    }
                }
            }
        }

        for (int i = 0; i < N; i++)
        {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.print( "INF ");
            else
                System.out.print( dist[i] + " ");
        }
    }

    /**
     * Main method
     * 
     */
    public static void main(String args[])
    {
        int n = 6;
        ArrayList<ArrayList<Pair> > adj = new ArrayList<ArrayList<Pair> >();
		
		for (int i = 0; i < n; i++) 
			adj.add(new ArrayList<Pair>());
			
		adj.get(0).add(new Pair(1, 2));
		adj.get(0).add(new Pair(4, 1));
		adj.get(1).add(new Pair(2, 3));
		adj.get(2).add(new Pair(3, 6));
		adj.get(4).add(new Pair(2, 2));
		adj.get(4).add(new Pair(5, 4));
		adj.get(5).add(new Pair(3, 1));
		QS12_shortestPathDAG obj = new QS12_shortestPathDAG(); 
		obj.shortestPath(0, adj, n); 
		
    }
}