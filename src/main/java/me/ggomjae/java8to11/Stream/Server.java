package me.ggomjae.java8to11.Stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {
    public static void main(String[] args) {

        List<Person> abcClass = new ArrayList<>();
        abcClass.add(new Person(1, "abc gom", true));
        abcClass.add(new Person(2, "abc ggomjae", true));
        abcClass.add(new Person(3, "abc ggom", false));
        abcClass.add(new Person(4, "abc kyungjae", false));
        abcClass.add(new Person(5, "ggomjae", false));

        List<Person> defClass = new ArrayList<>();
        defClass.add(new Person(6, "def Test", true));
        defClass.add(new Person(7, "def Test2", true));
        defClass.add(new Person(8, "def Test3", false));

        List<List<Person>> ggomjaeEvents = new ArrayList<>();
        ggomjaeEvents.add(abcClass);
        ggomjaeEvents.add(defClass);

        System.out.println("abc 으로 시작하는 사람");
        abcClass.stream()
                .filter(oc -> oc.getName().startsWith("abc"))
                .forEach(oc -> System.out.println(oc.getNumber()));

        System.out.println("공부하지 않은 사람");
        abcClass.stream()
                .filter(Predicate.not(Person::isClosed))
                .forEach(oc -> System.out.println(oc.getNumber()));

        System.out.println("사람 이름만 모아서 스트림 만들기");
        abcClass.stream()
                .map(Person::getName)
                .forEach(System.out::println);


        System.out.println("두 반 목록에 모든 사람 번호 출력");
        ggomjaeEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getNumber()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("def 반 중에 이름이 Test가 있는지 확인");
        boolean test = defClass.stream().anyMatch(oc -> oc.getName().contains("Test"));
        System.out.println(test);

        System.out.println("abc 사람 중에 이름에 abc이 들어간 것만 모아서 List로 만들기");
        List<String> spring = abcClass.stream()
                .map(Person::getName)
                .filter(t -> t.contains("spring"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }
}
