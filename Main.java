package Zad1_Gui3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Person[] arr = {
                new Person("Eve", 43), new Person("Joe", 34),
                new Person("Kim", 30), new Person("Joe", 25),
                new Person("Kim", 20), new Person("Eve", 27),
        };
        Comparator<Person> cmp = compCmp(
                (p1, p2) -> p1.getName().compareTo(p2.getName()),
                Person::getAge
        );

        Arrays.sort(arr, cmp);
        System.out.println(Arrays.toString(arr));

    }


    static <T, R extends Comparable<R>> Comparator<T> compCmp(Comparator<T> comp, Function<T, R> fun) {

        return (p1, p2) -> {
            int d = comp.compare(p1, p2);
            if (d != 0 ){
                return d;
            } else {
                return fun.apply(p1).compareTo(fun.apply(p2));
            }
        };


    }




}
