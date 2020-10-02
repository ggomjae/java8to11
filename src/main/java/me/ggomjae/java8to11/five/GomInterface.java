package me.ggomjae.java8to11.five;

public interface GomInterface {

    /*
     만약 이렇게 하고 Foo2 에 implements GomInterface, FooInterface 를 하고
     printNameUpperCase가 겹친다면 오류가 나고 이럴때는 Foo2에서 직접 오버라이딩 해줘야한다.
     */
    default void printNameUpperCase(){
        System.out.println("GOM");
    }
}
