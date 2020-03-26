import java.util.*;


class Node {
  
	String label;
	ArrayList<Node> adjList = new ArrayList<Node>();
	ArrayList<Integer> cost = new ArrayList<Integer>();
	int goalDist;
	
	
	Node bestAdj() {
		int cheap = cheapest(this.adjList, this.cost);
		
		
		
		return adjList.get(cheap);
		
		
		
		
	}
	
	int cheapest(ArrayList<Node> node, ArrayList<Integer> costs) {
		
		int ans = 0;
		
		for(int i = 1; i < costs.size(); i++) {
			
			if((node.get(i).goalDist + costs.get(i)) > (node.get(i-1).goalDist + costs.get(i-1))) {
				
				ans  = i-1;
			} else {
				ans = i;
			}
		}
		
		return ans;
		
	}



}
