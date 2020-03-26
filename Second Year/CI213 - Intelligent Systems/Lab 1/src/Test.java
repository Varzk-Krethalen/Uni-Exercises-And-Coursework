import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {

	static class Node {
		int value;
		boolean visited = false;
		Node right;
		Node left;

		public Node() {
		}

		public int getValue() {
			return value;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

	}

	public static void getChildNodes(Node n, Queue q) {
		n.visited = true;           				//records that the current node has been visited
		if (n.getLeft() != null) {   				//checks if node has a left child			
			q.add(n.getLeft());    					//adds left to the queue to be looked at
		}
		if (n.getRight() != null) {					//does the same for any right child node
			q.add(n.getRight());    

		}

	}

	public static void BFS(Node n) {
		Queue<Node> q = new LinkedList<Node>();		//creates a linkedList queue of nodes to be searched
		q.add(n);									//adds initial node to the head of the queue

		while (!q.isEmpty()) {    					//when the queue has contents
			Node child = q.remove();   				//removes current node from the queue. setting it to child
			System.out.println(child.getValue()); 	//prints the contents of the removed node
			getChildNodes(child, q); 				//finds child nodes of the removed node
		}
	}

	private static void getDFSChildNodes(Node n, Stack stack) {
		if (n.getLeft() != null && n.getLeft().visited == false) {
			stack.push(n.getLeft());
			n.getLeft().visited = true;
		}
		if (n.getRight() != null && n.getRight().visited == false) {
			stack.push(n.getRight());				//the right nodes are always first to be navigated
			n.getRight().visited = true;
		}
	}

	public static void DFS(Node n) {
		Stack<Node> s = new Stack<Node>();
		s.push(n);

		while (!s.isEmpty()) {
			Node child = s.pop();
			System.out.println(child.getValue());
			getDFSChildNodes(child, s);
		}
	}
	
	public static void recursiveDFS(Node n) {
		System.out.println(n.getValue());
		if (n.getValue() != 7)
		{
			if (n.getLeft() != null) {recursiveDFS((n.getLeft()));}
			if (n.getRight() != null) {recursiveDFS((n.getRight()));}
		}
	}
	
	public static void main(String[] args) {
		Node one = new Node();
		one.setValue(1);
		Node two = new Node();
		two.setValue(2);
		Node three = new Node();
		three.setValue(3);
		one.setLeft(two);
		one.setRight(three);
		Node four = new Node();
		four.setValue(4);
		Node five = new Node();
		five.setValue(5);
		Node six = new Node();
		six.setValue(6);
		Node seven = new Node();
		seven.setValue(7);
		two.setLeft(four);
		two.setRight(five);
		three.setLeft(six);
		three.setRight(seven);

		// uncomment to run each one
		//BFS(one);
		//DFS(one);
	    recursiveDFS(one);
	}
}
