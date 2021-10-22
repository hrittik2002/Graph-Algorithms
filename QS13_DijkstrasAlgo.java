
import java.util.*; 

class Node implements Comparator<Node>
{
    private int v;  // value
    private int weight; // weight
    
    Node(int _v, int _w) { v = _v; weight = _w; }
    
    Node() {}
    
    int getV() { return v; }
    int getWeight() { return weight; }
    

    // Sorts according to weights
    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.weight < node2.weight) 
            return -1; 
        if (node1.weight > node2.weight) 
            return 1; 
        return 0; 
    } 
}

class QS13_DijkstrasAlgo
{
    void shortestPath(int s, ArrayList<ArrayList<Node>> adj, int N)
    {
        int dist[] = new int[N]; // Distance Array
        
        for(int i = 0;i<N;i++) dist[i] = 100000000; // Mark everything as infinity

        // Mark dist[source] as zero bcz the distance from source to source
        dist[s] = 0; 
        
        // Min-Heap Priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>(N, new Node());

        // put (sorce , 0) in pq at first
        pq.add(new Node(s, 0));
        
        while(pq.size() > 0) {
            Node node = pq.poll();
            
            for(Node it: adj.get(node.getV())) {
                if(dist[node.getV()] + it.getWeight() < dist[it.getV()]) {
                    dist[it.getV()] = dist[node.getV()] + it.getWeight(); 
                    pq.add(new Node(it.getV(), dist[it.getV()]));
                }
            }
        }
        
        for (int i = 0; i < N; i++)
        {
            System.out.print( dist[i] + " ");
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
		
		adj.get(1).add(new Node(2, 4));
		adj.get(2).add(new Node(1, 4));
		
		adj.get(0).add(new Node(3, 1));
		adj.get(3).add(new Node(0, 1));
		
		adj.get(3).add(new Node(2, 3));
		adj.get(2).add(new Node(3, 3));
		
		adj.get(1).add(new Node(4, 5));
		adj.get(4).add(new Node(1, 5));
		
		adj.get(2).add(new Node(4, 1));
		adj.get(4).add(new Node(2, 1));
		
		QS13_DijkstrasAlgo obj = new QS13_DijkstrasAlgo(); 
		obj.shortestPath(0, adj, n); 
		
    }
}