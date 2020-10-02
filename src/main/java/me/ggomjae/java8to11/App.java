package me.ggomjae.java8to11;

import me.ggomjae.java8to11.five.FooInterface;

public class App {
    public static void main(String[] args){
        Foo2 foo2 = new Foo2("ggomjae");
        foo2.print();
        foo2.printNameUpperCase();

        // static을 이용하여 사용가능.
        FooInterface.printAnything();
    }
}
