package me.ggomjae.java8to11.two;

import java.util.function.Function;

/* 굳이 One Package 처럼 정의 하지 않고 이렇게 상속받아서 해도 된다. */
public class Plus10 implements Function<Integer,Integer> {

    @Override
    public Integer apply(Integer integer) {

        return integer + 10;
    }
}
