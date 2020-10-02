package me.ggomjae.java8to11.five;

public interface FooInterface {

    // 이렇게 인터페이스는 그냥 써도 기본적으로 public이다.
    void print();

    /*
        @implSpec을 이용해서 이 구현체가 어떤 역할을하는지 명확히 써주는게 좋음
        이유는 만약 getName() 했는데 Null 이 오면 Exception이 오니까
        그래서 또는 Foo2 클래스에 printNaumeUpperCase를 this.name.toUpperCase로 재정의 가능.
    */
    // 만약 이렇게 default를 안한다면 이 인터페이스를 상속받는 모든 클래스가
    // 전부 다 printNameUpperCase를 오버라이드 해줘야하기에 오류가 생긴다.
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    // @FunctionalInterface 를 쓰면 다수의 추상메소드기 때문에 오류.
    String getName();

    /*
    default String toString(){}; 처럼 기존에 있던 ( Obejct ) 메소드는 오버라이딩을 못한다.
     */

    // 인터페이스에도 static이 가능하다.
    static void printAnything(){
        System.out.println("Anythig");
    }
}
