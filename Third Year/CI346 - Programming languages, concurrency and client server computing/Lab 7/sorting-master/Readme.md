# Lambda expression and functional interfaces

This is a lab exercise for CI346, focusing on sorting lists using
a variety of lambda expressions, static methods and functional interfaces.

Start by cloning the repository:
 
    $ git clone https://github.com/jimburton/sorting.git

and load it in your IDE. Run the tests in the class `CI346.lambdaExercises.TestExercises`
to test your progress. You will probably need to add the JUnit library to the project in your IDE
before you can do that. 


* [Adding JUnit support to an IntelliJ project](https://www.jetbrains.com/help/idea/2016.2/configuring-testing-libraries.html)
* Eclipse: Right click on Project Name, go to Build Path, Add Libraries and select JUnit4.

The project will require Java 8 since it uses lambdas and the `streams` API.
Also, note that the list to be sorted is immutable which is good practice, as explained in the lectures.

## Exercise 0

This is provided as an example of sorting using an Anonymous Inner Class and the `streams` API.

## Exercise 1

Complete the `exerciseOne` method to sort the list using the same comparison as Exercise Zero but 
using a lambda expression.

## Exercise 2

Sort the list by the *length* of the *entire name* of each `Name` object. Shorter names
should come first, so your lambda expression should take two Name objects, `n1` and `n2`, and
return a number less than 0 if the length of the name of `n1` is less than the length of the
name of `n2`, 0 if the lengths are equal, and so on.

## Exercise 3

Sort the list so that names containing the string "o" (anywhere in the *full name*) comes
first, and everything else comes after that.

This should be a *stable* sort, so that if the names of both elements contain "o" they are
considered equal and don't change place.

So your lambda expression should take two `Name` objects, `n1` and `n2`, and return -1 if the full
name of `n1` contains "o" but the name of `n2` doesn't, 0 if both or neither contain the string,
and 1 if only `n2` contains it.

Remember that lambda expressions can contain more than one line.

## Exercise 4
    
Complete the static method `sortByNameContainsString` to create
a more reusable version of the sort from the previous exercise. As before,
this method should return -1 if the *full name* of `n1` contains the string but
the full name of `n2` doesn't, 0 if both or neither contain the string, and 1
if only the name of `n2` contains it.

Then complete the `exerciseFour` method by calling `sortByNameContainsString` in a
lambda expression, passing "l" as the first parameter to `sortByNamesContainingString`, 
so that names containing "l" come first in the list.

## Exercise 5
    
Complete the method `pickString` which takes two strings, `s1` and `s2`, and an instance of the
`TwoStringPredicate` functional interface. `pickString` should return -1 if the predicate is
true for `(s1, s2)` and 0 otherwise.

Then complete the `exerciseFive` method by creating a lambda expression that calls
`Exercises.pickString` passing in the *last name* of two `Name` objects and an instance
of `TwoStringPredicate`. The instance of `TwoStringPredicate` should itself be a lambda expression
and should return true if the length of the first string is *less than* the length of the second one.

## Exercise 6

Generalise the approach from Exercise 5 by creating a new functional interface in the package
`CI346.lambdaExercises` called `TwoElementPredicate`. This interface should be parameterised by a type 
`T` that extends the `Comparable` interface in the same way as in the signature of the `pickElement` method below.

The `TwoElementPredicate` interface should define a single static method, `isBetter`, that takes two
elements of type `T` and returns a `boolean`.

Complete the `pickElement` method below so that it takes a third parameter with the type
`TwoElementPredicate<T>` and returns -1 if the predicate is false for `(e1, e2)`, and returns 0 otherwise.

Then complete the `exerciseSix` method by creating a lambda expression that calls
`Exercises.pickElement` by passing in the *first* name of two `Name` objects and an instance
of `TwoElementPredicate`. The predicate should itself be a lambda expression
and should return true if the length of the first string is *greater than* the length of the second one.

Why is `TwoElementPredicate` a better design than `TwoStringPredicate`?
