package ci284.lab5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ci284.lab5.trees.*;

public class BSTTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCountNodes() {
		BST<Integer> tree = new BranchNode<Integer>(7, null, null);
		assertEquals(1, tree.countNodes());
		tree.insert(5);
		tree.insert(12);
		assertEquals(3, tree.countNodes());
	}
	
	@Test
	public void testHeight() {
		BST<Integer> tree = new BranchNode<Integer>(7, null, null);
		assertEquals(1, tree.height());
		tree.insert(8);
		tree.insert(9);
		assertEquals(3, tree.height());
	}
	
	@Test
	public void testInsert() {
		BST<Integer> tree = new BranchNode<Integer>(null, null, null);
		tree.insert(7);
		assertEquals(7, tree.getLabel().intValue());
		tree.insert(7);
		assertEquals(1, tree.height());
		tree.insert(53);
		BranchNode<Integer> temp = (BranchNode<Integer>) tree;
		assertEquals(53, temp.getRight().getLabel().intValue());
		assertEquals("7 53", tree.toString());

	}
	
	@Test
	public void testToString() {
		BST<Integer> tree = new BranchNode<Integer>(7, null, null);
		tree.insert(23);
		tree.insert(17);
		tree.insert(4);
		assertEquals("4 7 17 23", tree.toString());
	}	
	
	@Test
	public void testRemove() {
		BST<Integer> tree = new BranchNode<Integer>(7, null, null);
		tree.insert(23);
		tree.insert(17);
		tree.insert(4);
		tree.remove(23);
		assertEquals("4 7 17", tree.toString());
	}
	
	@Test
	public void testMerge() {
		//BST<Integer> tree = new BranchNode<Integer>(7);
		
	}
	
}
