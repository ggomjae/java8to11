package me.ggomjae.java8to11.one;

/*
 이것은 자바가 코드의 재활용 단위가 클래스 였던 것이 함수 단위로
 재사용이 가능해 지면서 조금더 개발을 유연하게 할 수 있게 된 점 이라고 할 수 있습니다.
 */

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
