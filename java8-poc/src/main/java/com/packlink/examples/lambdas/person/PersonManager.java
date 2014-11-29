package com.packlink.examples.lambdas.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class PersonManager {

    // Approach 1: Create Methods That Search for Members That Match One Characteristic
    public static void printPersonsOlderThan(List<Person> roster, int age) {

        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods
    public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {

        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    public static void printPersons(List<Person> roster, CheckPerson tester) {

        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {

        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application

    /**
     * You need a functional interface that contains an abstract method that can take one argument of type Person and returns void.
     * The Consumer<T> interface contains the method void accept(T t), which has these characteristics.
     */
    public static void processPersons(List<Person> roster,
                                      Predicate<Person> tester,
                                      Consumer<Person> block) {

        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    // What if you want to do more with your members' profiles than printing them out.
    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {

        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    // Approach 8: Use Generics More Extensively
    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {

        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }


    /**
     * Accessing Local Variables of the Enclosing Scope
     * Lambda expressions do not inherit any names from a supertype or introduce a new level of scoping.
     * Declarations in a lambda expression are interpreted just as they are in the enclosing environment.
     * <p>
     * <p>
     * Target typing
     * To determine the type of a lambda expression, the Java compiler uses the target type of the context or situation
     * in which the lambda expression was found.
     * It follows that you can only use lambda expressions in situations in which the Java compiler can determine a target type:
     */


    public static void main(String[] args) {

        printPersonsOlderThan(new ArrayList<com.packlink.examples.lambdas.person.Person>(), 10);
        printPersons(new ArrayList<Person>(), new CheckPersonEligibleForSelectiveService());

        // Approach 4: Specify Search Criteria Code in an Anonymous Class
        printPersons(
                new ArrayList<Person>(),
                new CheckPerson() {
                    public boolean test(Person p) {

                        return p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25;
                    }
                }
        );

        // Approach 5: Specify Search Criteria Code with a Lambda Expression
        // The CheckPerson interface is a functional interface.
        //  A functional interface is any interface that contains only one abstract method.
        // (A functional interface may contain one or more default methods or static methods.)
        printPersons(
                new ArrayList<Person>(),
                (Person p) -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );


        printPersonsWithPredicate(
                new ArrayList<Person>(),
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );


        // Approach 7: Use Lambda Expressions Throughout Your Application
        processPersons(
                new ArrayList<Person>(),
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.printPerson()
        );


        /**
         *
         * Approach 8: Use Generics More Extensively
         *
         1.- Obtains a source of objects from the collection source.
         In this example, it obtains a source of Person objects from the collection roster.
         Notice that the collection roster, which is a collection of type List, is also an object of type Iterable.
         2.- Filters objects that match the Predicate object tester.
         In this example, the Predicate object is a lambda expression that specifies which members
         would be eligible for Selective Service.
         3.- Maps each filtered object to a value as specified by the Function object mapper.
         In this example, the Function object is a lambda expression that returns the e-mail address of a member.
         4.- Performs an action on each mapped object as specified by the Consumer object block.
         In this example, the Consumer object is a lambda expression that prints a string, which is the e-mail address returned by the Function
         object.
         */
        processPersonsWithFunction(
                new ArrayList<Person>(),
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );


        // Approach 9: Use Aggregate Operations That Accept Lambda Expressions as Parameters
        /**
         * The operations filter, map, and forEach are aggregate operations.
         *
         * Aggregate operations process elements from a stream, not directly from a collection
         * (which is the reason why the first method invoked in this example is stream).
         *
         * A stream is a sequence of elements. Unlike a collection, it is not a data structure that stores elements.
         * Instead, a stream carries values from a source, such as collection, through a pipeline.
         *
         * A pipeline is a sequence of stream operations, which in this example is filter- map-forEach.
         * In addition, aggregate operations typically accept lambda expressions as parameters, enabling you to customize how they behave.
         *
         */
        List<Person> roster = generatePeople();
        roster
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
    }

    private static List<Person> generatePeople() {

        List<Person> people = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            people.add(person);
        }

        return people;
    }
}
