package ci284.lab5.trees;


public class LeafNode<T extends Comparable<T>> extends BST<T> {

	public LeafNode(T label) {
		this.label = label;
	}

	// Exercises

	@Override
	public int countNodes() {
		return 1;
	}

	@Override
	public int height() {
		return 1;
	}

	@Override
	public BST<T> insert(T e) {
		LeafNode<T> leaf = new LeafNode<T>(e);
		if (e.compareTo(label) > 0) {
			BranchNode<T> branch = new BranchNode<T>(label, null, leaf);
			return branch;
		}
		else if (e.compareTo(label) < 0) {
			BranchNode<T> branch = new BranchNode<T>(label, leaf, null);
			return branch;
		}
		return null;
	}

	@Override
	public String toString() {
		return String.valueOf(label);
	}

	@Override
	public BST<T> remove(T e) {
		if (e == label) {
			return null;
		}
		return this;
	}

	@Override
	protected BST<T> merge(BST<T> that) {
		throw new UnsupportedOperationException("Method not implemented");
	}

}
