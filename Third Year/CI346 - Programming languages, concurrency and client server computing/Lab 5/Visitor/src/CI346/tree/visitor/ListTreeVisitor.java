package CI346.tree.visitor;

import CI346.tree.Branch;
import CI346.tree.Leaf;

import java.util.ArrayList;
import java.util.List;

/**
 * A visitor for binary trees that carries out an *in-order* traversal to
 * create a list of the labels in a tree.
 *
 * Created by jb259 on 27/10/16.
 */
public class ListTreeVisitor<T extends String> implements TreeVisitor<T> {
    private List<T> result;

    public ListTreeVisitor() {
        this.result = new ArrayList<T>();
    }

    public String getList() {
        return result.toString();
    }

    @Override
    public void visit(Leaf<T> leaf) {
        result.add(leaf.getLabel());
        return;
    }

    @Override
    public void visit(Branch<T> branch) {
        branch.getLeft().accept(this);
        result.add(branch.getLabel());
        branch.getRight().accept(this);
        return;
    }
}
