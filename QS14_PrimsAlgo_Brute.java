/**
 * Prims Algorithm (Brute Force)
 * To find the Minimum Spanning Tree of a graph
 * TC : O(n^2)
 * SC : O(n)
 */
import java.util.*; 

class Node 
{
    private int v; // value
    private int weight; // weight
    
    Node(int _v, int _w) { v = _v; weight = _w; }
    
    Node() {}
    
    int getV() { return v; }
    int getWeight() { return weight; }
}

class QS14_PrimsAlgo_Brute
{
    void primsAlgo(ArrayList<ArrayList<Node>> adj, int N)
    {
        int key[] = new int[N];
        int parent[] = new int[N]; 
        boolean mstSet[] = new boolean[N]; 

        // Initialize every element of key[] as infinity , every element of mstSet[] as false , and every element of parent[] as -1
        for(int i = 0;i<N;i++) {
        	key[i] = 100000000; 
        	mstSet[i] = false; 
            parent[i] = -1;
        }

        // Mark key of first element as 0
        key[0] = 0;
        
        
        // we are going to loop for n-1 times because a spanning tree always has n-1 edges
        for(int i = 0;i<N-1;i++) {

            // At first itrate through the key[] array and find the index of minimum value which is not a part of mstSet[]
        	int mini = 100000000, u = 0; 
        	for(int v = 0;v<N;v++) {
        		if(mstSet[v] == false && key[v] < mini) {
        			mini = key[v]; // store min value in mini
        			u = v;         // store index of min value in u
        		}
        	}


        	mstSet[u] = true;
     
            // traverse through the adjacent of u
        	for(Node it: adj.get(u)) {
                // if its adj dont exist on mst and its edge weight is smaller than key[adj]
        		if(mstSet[it.getV()] == false && it.getWeight() < key[it.getV()]) {
        			parent[it.getV()] = u; 
        			key[it.getV()] = it.getWeight(); 
        		}
        	}
        }

        for(int i = 1;i<N;i++) {
        	System.out.println(parent[i] + " - " + i); 
        }
    }
    public static void main(String args[])
    {
        int n = 5;
        ArrayList<ArrayList<Node> > adj = new ArrayList<ArrayList<Node> >();
		
		for (int i = 0; i < n; i++) 
			adj.add(new ArrayList<Node>());
			
		adj.get(0).add(new Node(1, 2));
		adj.get(1).add(new Node(0, 2));
		
		adj.get(1).add(new Node(2, 3));
		adj.get(2).add(new Node(1, 3));
		
		adj.get(0).add(new Node(3, 6));
		adj.get(3).add(new Node(0, 6));
		
		adj.get(1).add(new Node(3, 8));
		adj.get(3).add(new Node(1, 8));
		
		adj.get(1).add(new Node(4, 5));
		adj.get(4).add(new Node(1, 5));
		
		adj.get(2).add(new Node(4, 7));
		adj.get(4).add(new Node(2, 7));
		
		QS14_PrimsAlgo_Brute obj = new QS14_PrimsAlgo_Brute(); 
		obj.primsAlgo(adj, n); 
		
    }
}