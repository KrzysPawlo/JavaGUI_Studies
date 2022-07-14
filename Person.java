package Zad1_Gui3;

public class Person {

    protected String name;
    protected int age;

    public Person(String n, int a) {
        this.name = n;
        this.age = a;
    }

    String getName(){
        return name;
    }

    int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return name + "( " + age + ") ";
    }


}
