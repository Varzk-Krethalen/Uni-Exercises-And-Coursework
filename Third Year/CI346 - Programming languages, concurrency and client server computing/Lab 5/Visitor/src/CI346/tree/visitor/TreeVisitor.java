package CI346.tree.visitor;

import CI346.tree.Branch;
import CI346.tree.Leaf;

/**
 * The base class for BinaryTree visitors. This interface needs to contain an
 * overloaded `visit' method for every subclass of BinaryTree (i.e. everything
 * which might appear in a tree). Each `visit' method should return `void'.
 *
 * Created by jb259 on 27/10/16.
 */
public interface TreeVisitor<T> {

    void visit(Leaf<T> leaf);

    void visit(Branch<T> branch);
}
