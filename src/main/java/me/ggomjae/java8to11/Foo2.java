package me.ggomjae.java8to11;

import me.ggomjae.java8to11.five.FooInterface;

public class Foo2 implements FooInterface {

    String Name;

    public Foo2(String name) {
        this.Name = name;
    }

    @Override
    public void print() {
        System.out.println("ggomjae");
    }

    @Override
    public String getName() {
        return this.Name;
    }

}
