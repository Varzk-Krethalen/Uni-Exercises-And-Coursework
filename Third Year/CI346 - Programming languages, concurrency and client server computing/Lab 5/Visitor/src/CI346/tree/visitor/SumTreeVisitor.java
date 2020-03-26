package CI346.tree.visitor;

import CI346.tree.Branch;
import CI346.tree.Leaf;

import java.math.BigDecimal;

/**
 * A visitor for binary trees that carries out a *pre-order* traversal to add together
 * all the labels in a tree whose labels are numbers.
 *
 * Created by jb259 on 27/10/16.
 */
public class SumTreeVisitor<T extends Number> implements TreeVisitor<T> {

    private Number sum = 0;

    public T getSum() {
        return (T)sum;
    }

    @Override
    public void visit(Leaf<T> leaf) {
        sum = new BigDecimal(sum.floatValue()).add(new BigDecimal(leaf.getLabel().floatValue()));
        return;
    }

    @Override
    public void visit(Branch<T> branch) {
        sum = new BigDecimal(sum.floatValue()).add(new BigDecimal(branch.getLabel().floatValue()));
        branch.getLeft().accept(this);
        branch.getRight().accept(this);
    }
}
