package ci284.lab5.trees;

public class BranchNode<T extends Comparable<T>> extends BST<T> {

	BST<T> left;
	BST<T> right;

	public BranchNode(T label, BST<T> left, BST<T> right) {
		this.label = label;
		this.left = left;
		this.right = right;
	}

	public BST<T> getLeft() {
		return left;
	}

	public BST<T> getRight() {
		return right;
	}

	// Exercises
	@Override
	public int countNodes() {
		int leftCount = (left != null) ? left.countNodes() : 0;
		int rightCount = (right != null) ? right.countNodes() : 0;
		return 1 + leftCount + rightCount;
	}

	@Override
	public int height() {
		int leftHeight = (left != null) ? left.height() : 0;
		int rightHeight = (right != null) ? right.height() : 0;
		return 1 + ((leftHeight > rightHeight) ? leftHeight : rightHeight);
	}

	@Override
	public BST<T> insert(T e) {
		if (this.getLabel() == null) {
			this.label = e;
			return null;
		}
		else if (e.compareTo(label) < 0) {
			if (left == null) {
				left = new LeafNode<T>(e);
				return null;
			} else {
				BranchNode<T> temp = (BranchNode<T>) left.insert(e);
				if (temp != null) {
					left = temp;
				}
				return null;
			}
		}
		else if (e.compareTo(label) > 0) {
			if (right == null) {
				right = new LeafNode<T>(e);
				return null;
			} else {
				BranchNode<T> temp = (BranchNode<T>) right.insert(e);
				if (temp != null) {
					right = temp;
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String branchString = "";
		String leftString = (left != null) ? left.toString() + " " : "";
		String rightString = (right != null) ? " " + right.toString() : "";
		branchString += leftString + label + rightString;
		return branchString;
	}

	@Override
	public BST<T> remove(T e) {
		if (e == label) {
			boolean leastOne = ((left == null) || (right == null)) ? true: false;
			boolean onlyOne = !((left == null) && (right == null));
			if (leastOne && onlyOne) {
				return (left != null) ? left: right;
			}
			else {
				return merge(this);
			}
		}
		else {
			if (left != null) {
				left = left.remove(e);
			}
			if (right != null) {
				right = right.remove(e);
			}
			return this;
		}
	}

	@Override
	protected BST<T> merge(BST<T> that) {
		if (left instanceof BranchNode<?>) {
			if (right instanceof BranchNode<?>) {
				
			}
			else {
				
			}
		}
		else {
			if (right instanceof BranchNode<?>) {
				
			}
			else {
				
			}
		}
		return null;
	}

}
