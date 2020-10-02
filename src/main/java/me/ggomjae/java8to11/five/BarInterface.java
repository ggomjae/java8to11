package me.ggomjae.java8to11.five;

public interface BarInterface extends FooInterface {

    // BarInterface에서는 FooInterface가 제공하는 default 메소드를 제공하고 싶지 않다 !
    // 이렇게 하면 이제 이 BarInterface를 상속받는 클래스가 재정의 해야함
    void printNameUpperCase();
}
