package CI346.lambdaExercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CI346 Lab Exercise on sorting with lambda expressions and creating
 * functional interfaces.
 *
 * Created by jb259 on 20/11/16.
 */
public class Exercises {

    private List<Name> people;

    public Exercises() {
        List<Name> pMut = new ArrayList<>();
        pMut.add(new Name("Hubert", "Huncke"));
        pMut.add(new Name("Gary", "Snyder"));
        pMut.add(new Name("Gregory", "Corso"));
        pMut.add(new Name("Neal", "Cassady"));
        pMut.add(new Name("Jack", "Kerouac"));
        pMut.add(new Name("Allen", "Ginsberg"));

        people = Collections.unmodifiableList(pMut);
    }

    /*
    Sorting the old fashioned way, with a Comparator which is an Anonymous Inner Class.
     */
    public String exerciseZero() {
        Comparator<Name> firstNameSorter = new Comparator<Name>() {
            @Override
            public int compare(Name p1, Name p2){
                return p1.getFirstName().compareTo(p2.getFirstName());
             }
        };
        List<?> sortedList = people.stream()
                            .sorted(firstNameSorter)
                            .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    1. Sort the list using the same comparison as Exercise Zero but using a lambda expression.
     */
    public String exerciseOne() {
        List<?> sortedList = people.stream()
                .sorted(Comparator.comparing(p -> p.getFirstName()))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    2. Sort the list by the *length* of the *entire name* of each Name object. Shorter names
    should come first, so your lambda expression should take two Name objects, n1 and n2, and
    return a number less than 0 if the length of the name of n1 is less than the length of the
    name of n2, 0 if the lengths are equal, and so on.
     */
    public String exerciseTwo() {
        List<?> sortedList = people.stream()
                .sorted(Comparator.comparingInt(p -> p.getName().length()))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    3. Sort the list so that names containing the string "o" (anywhere in the *full name*) come
    first, and everything else comes after that.

    This should be a *stable* sort, so that if the names of both elements contain "o" they are
    considered equal and don't change place.

    So your lambda expression should take two Name objects, n1 and n2, and return -1 if the full
    name of n1 contains "o" but the name of n2 doesn't, 0 if both or neither contain the string,
    and 1 if only n2 contains it.

    Remember that lambda expressions can contain more than one line.
     */
    public String exerciseThree() {
        List<?> sortedList = people.stream()
                .sorted(Comparator.comparing(p -> !p.getName().contains("o")))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    4. Complete the static method sortByNameContainsString below to create
    a more reusable version of the sort from the previous exercise. As before,
    this method should return -1 if the full name of n1 contains the string but
    the full name of n2 doesn't, 0 if both or neither contain the string, and 1
    if only the name of n2 contains it.

    Then complete the exerciseFour method by calling sortByNameContainsString
    passing "l" as the first parameter, so that names containing l come first in
    the list.
     */
    public static int sortByNameContainsString(String s, Name n1, Name n2) {
        return Boolean.compare(!n1.getName().contains(s), !n2.getName().contains(s));
    }
    public String exerciseFour() {
        List<?> sortedList = people.stream()
                .sorted((n1, n2) -> sortByNameContainsString("l", n1, n2))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    5. Complete the method pickString which takes two strings, s1 and s2, and an instance of the
    TwoStringPredicate functional interface. pickString should return -1 if the predicate is
    true for (s1, s2) and 0 otherwise.

    Then complete the exerciseFive method by creating a lambda expression that calls
    Exercises.pickString passing in the *last name* of two Name objects and an instance
    of TwoStringPredicate. The instance of TwoStringPredicate should itself be a lambda expression
     and should return true if the length of the first string is *less than* the length of the second one.

     */
    public static int pickString(String s1, String s2, TwoStringPredicate predicate) {
        return (predicate.isBetter(s1, s2)) ? -1: 0;
    }

    public String exerciseFive() {
        List<?> sortedList = people.stream()
                .sorted((n1, n2) -> pickString(n1.getLastName(), n2.getLastName(),
                        (s1, s2) -> s1.length() < s2.length()))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

    /*
    Generalise the approach from Exercise 5 by creating a new functional interface in this package called
    TwoElementPredicate. This interface should be parameterised by a type T that extends the
    Comparable interface in the same way as in the signature of the pickElement method below.

    The TwoElementPredicate interface should define a single static method, isBetter, that takes two
    elements of type T and returns a boolean.

    Complete the pickElement method below so that it takes a third parameter with the type
    TwoElementPredicate<T> and returns -1 if the predicate is false for (e1, e2), and returns 0 otherwise.

    Then complete the exerciseSix method by creating a lambda expression that calls
    Exercises.pickElement by passing in the *first* name of two Name objects and an instance
    of TwoElementPredicate. The instance of TwoElementPredicate should itself be a lambda expression
    and should return true if the length of the first string is *greater than* the length of the second one.

    Why is TwoElementPredicate a better design than TwoStringPredicate?

     */

    public static <T extends Comparable> int pickElement(T e1, T e2, TwoElementPredicate predicate) {
        return (predicate.isBetter(e1, e2)) ? -1: 0;
    }

    public String exerciseSix() {
        List<?> sortedList = people.stream()
                .sorted((n1, n2) -> pickElement(n1.getFirstName(), n2.getFirstName(),
                        (TwoElementPredicate<String>) (e1, e2) -> e1.length() > e2.length()))
                .collect(Collectors.toList());
        return sortedList.toString();
    }

}