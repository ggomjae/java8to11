package me.ggomjae.java8to11.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Server2 {
    public static void main(String[] args) {

        List<Person2> abcClass = new ArrayList<>();

        abcClass.add(new Person2(1, "abc gom", true));
        abcClass.add(new Person2(2, "abc ggomjae", true));
        abcClass.add(new Person2(3, "abc ggom", false));
        abcClass.add(new Person2(4, "abc kyungjae", false));
        abcClass.add(new Person2(5, "ggomjae", false));

        Person2 person2 = new Person2(1, "abc gom", true);

        /*
        이렇게하면 getProgress()에서 Null 이나오기에
        Duration progress = person2.getProgress().getStudyDuration();

        이렇게 해줬다. 이것은 에러를 만들기 좋은 코드다. Null체크를 깜빡할 수 있기에.
        Progress progress = person2.getProgress();
        if ( progress != null ) { ... };
         */

        Optional<Person2> optional = abcClass.stream()
                .filter( p -> p.getName().startsWith("abc"))
                .findFirst();
        // 존재하냐 안하냐
        boolean present = optional.isPresent();

        // Optional이기에 값을 꺼내올 수 있다.
        Person2 newPerson = optional.get();

        /*
         이제 여기서 이런식으로 하기보다는 Optional이 제공하는 API를 쓰자
         if(optional.isPresent()){
            Person2 newPerson = optional.get();
         }

         이런식으로
         optional.ifPresent(System.out::println);
         */

        /* 이건 무조건 생성 그래서 orElseGet을 더 추천함.
        Person2 person = optional.orElse(createNewPerson());

        또는 orElseThrow( () -> { return new -}); 서플라이어니까 이런식으로 가능
         */

        Person2 person = optional.orElseGet(Server2::createNewPerson);

        /* filter을 거쳐도 Optional */
        Optional<Person2> optional1 = optional
                .filter(Person2::isClosed);

        /* Map 또한 Optional */
        Optional<Integer> integer = optional.map(Person2::getNumber);
    }

    private static Person2 createNewPerson() {
        return new Person2(222, "abc gom", true);
    }
}
