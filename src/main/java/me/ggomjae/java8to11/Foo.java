package me.ggomjae.java8to11;

import me.ggomjae.java8to11.one.RunSomething;
import me.ggomjae.java8to11.one.RunSomething2;
import me.ggomjae.java8to11.two.Plus10;

import java.util.function.*;

/*
First Class Citizon는 아래와 같은 속성들을 모두 만족해야 한다.

변수에 값을 할당 할 수 있어야 한다.
함수의 파라미터로 넘겨줄 수 있어야 한다.
함수의 반환값이 될 수 있어야 한다.
Java에 method는 위 조건 모두를 만족하지 않음으로 Java에서 method는 일급객체가 아니고 때문에 java는 함수형 프로그래밍 언어가 아니다

Java8에서는 함수를 일급객체처럼 다룰 수 있게 함수형 인터페이스를 제공한다. 함수형 인터페이스란 단 하나의 추상 메소드만 가지는 인터페이스를 의미한다.
 */
public class Foo {
    public static void main(String[] args) {

        ////////////////////////////////////////////////////////
        //       One                                          //
        ////////////////////////////////////////////////////////

        // 익명 내부 클래스
        /* 방법 1
        RunSomething runSomething = new RunSomething() {

            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };
        */

        /* 방법 2 : 람다 */
        RunSomething runSomething = () -> System.out.println("Hello");

        RunSomething2 runSomething2 = (number) -> {
            return number+10;
        };

        /* 같은 20 값이 계속 나와야한다. 보장해줘야한다. 그래야 함수형 프로그래밍 */
        System.out.println(runSomething2.doIt(10));
        System.out.println(runSomething2.doIt(10));

        /*
         순수한 함수 인터페이스 같은 경우에는 지역 변수라는지, 외부 변수라든지를 넣지 않는 것이 좋다
         예를 들면 new runSomething() { int localNumber = 10 ; doit(localNumber); } ;
         */

        ////////////////////////////////////////////////////////
        //       Two                                          //
        ////////////////////////////////////////////////////////

        /*
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));
        */
        Function<Integer,Integer> plus10 = (number) -> number + 10;
        System.out.println(plus10.apply(10));

        /* 조합을 할 수 있다 */
        Function<Integer, Integer> mutiply = (number) -> number * 2;
        Function<Integer, Integer> mutiplyAndPlus10 = plus10.compose(mutiply);
        System.out.println(mutiplyAndPlus10.apply(10));

        /* 옆에 붙이기. 조합반대*/
        Function<Integer, Integer> plus10AndThenmutiply = plus10.andThen(mutiply);
        System.out.println(plus10AndThenmutiply.apply(10));

        /* 또 다른 자바 패키지에 있는 */
        // 아무것도 반환하지 않겠다.
        Consumer<Integer> printT = (number) -> System.out.println(number);
        printT.accept(10);

        // 받지 않고 공급만 하겠다. 그래서 매개변수에 없음
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // Boolean 값만 나옴 그래서 or, and 등 조합가능
        Predicate<String> startsWithggomjae = (s) -> s.startsWith("ggomjae");
        Predicate<Integer> isEven = (number) -> number%2 == 0;

        // Function<Integer, Integer> 처럼 입력값과 결과값이 같은 때 사용 가능 깔끔하게.
        UnaryOperator<Integer> unaryOperator = (number) -> number + 2;
    }
}
