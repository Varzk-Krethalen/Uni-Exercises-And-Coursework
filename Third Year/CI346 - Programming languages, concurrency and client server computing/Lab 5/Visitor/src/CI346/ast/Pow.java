package CI346.ast;

import CI346.ast.visitor.ASTVisitor;

/**
 * An AST node representing the addition of the left and right
 * hand expressions.
 *
 * Created by jb259 on 27/10/16.
 */
public class Pow<T> extends BinaryOp<T> {

    public Pow(Exp lhs, Exp rhs) {
        super(lhs, rhs);
    }

    @Override
    public T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
