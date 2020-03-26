package CI346.ast.visitor;

import CI346.ast.*;
import java.util.Map;

/**
 * An AST Visitor which evaluates an expression. In order to
 * look up the value of identifiers, the constructor requires a
 * lookup table, or environment, allowing us to look up identifier
 * names.
 *
 * Created by jb259 on 27/10/16.
 */
public class EvalVisitor implements ASTVisitor<Integer> {
    Map<String, Integer> env;

    public EvalVisitor(Map<String, Integer> env) {
        this.env = env;
    }

    @Override
    public Integer visit(Val<Integer> integerVal) {
        return integerVal.getValue();
    }

    @Override
    public Integer visit(Id<Integer> integerId) {
        try {
            return env.get(integerId.getId());
        }
        catch (Exception e) {
            throw new RuntimeException("Identifier " + integerId.getId() + " undefined");
        }
    }

    @Override
    public Integer visit(Plus<Integer> integerPlus) {
        return getLeft(integerPlus) + getRight(integerPlus);
    }

    @Override
    public Integer visit(Minus<Integer> integerMinus) {
        return getLeft(integerMinus) - getRight(integerMinus);
    }

    @Override
    public Integer visit(Mul<Integer> integerMul) {
        return getLeft(integerMul) * getRight(integerMul);
    }

    @Override
    public Integer visit(Pow<Integer> integerPow) {
        return (int) Math.pow(getLeft(integerPow), getRight(integerPow));
    }

    private Integer getLeft(BinaryOp<Integer> operation) {
        return (Integer) operation.getLhs().accept(this);
    }

    private Integer getRight(BinaryOp<Integer> operation) {
        return (Integer) operation.getRhs().accept(this);
    }
}
