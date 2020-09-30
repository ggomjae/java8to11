package me.ggomjae.java8to11;

@FunctionalInterface
public interface RunSomething {
    void doIt();

    static void printName(){
        System.out.println("ggomjae");
    }

    default void printAge(){
        System.out.println("28");
    }
}
