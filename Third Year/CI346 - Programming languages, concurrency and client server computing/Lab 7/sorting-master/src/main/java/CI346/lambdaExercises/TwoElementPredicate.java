package CI346.lambdaExercises;

/**
 * A functional interface defining a method, isBetter, that takes two Strings, s1 and s2,
 * and returns true if s1 is "better" than s2 in some sense.
 *
 * Created by jb259 on 20/11/16.
 */
public interface TwoElementPredicate<T extends Comparable> {
    boolean isBetter(T e1, T e2);
}
