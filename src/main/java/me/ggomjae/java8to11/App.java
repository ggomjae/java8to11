package me.ggomjae.java8to11;

import me.ggomjae.java8to11.five.FooInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;


public class App {
    public static void main(String[] args){
        Foo2 foo2 = new Foo2("ggomjae");
        foo2.print();
        foo2.printNameUpperCase();

        // static을 이용하여 사용가능.
        FooInterface.printAnything();

        ////////////////////////////////////////////////////////
        //       Six                                          //
        ////////////////////////////////////////////////////////

        List<String> name = new ArrayList<>();
        name.add("ggomjae");
        name.add("kyugnjae");
        name.add("gomjae");


        /*
        forEach ->
        Consumer는 어떤 객체를 입력받아 void를 출력시키는 함수형 인터페이스
        이걸 보면 메소드 레퍼런스가 생각나야한다.
        */
        name.forEach( s->{
            System.out.println(s);
        });

        /* 메소드 레퍼런스 ClassName::MethodName */
        name.forEach(System.out::println);

        /* tryAdvance 또한 Consumer를 받는다. */
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("========================");
        while(spliterator1.tryAdvance((System.out::println)));

        /* Stream 맛보기 */
        long g = name.stream().map(String::toUpperCase)
                .filter( s -> s.startsWith("g"))
                .count();
        System.out.println(g);

        /* 삭제 */
        name.removeIf(s->s.startsWith("g"));

        /* 차순 , reversed를 이용한 역순*/
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase);
        name.sort(compareToIgnoreCase.reversed());


    }
}
