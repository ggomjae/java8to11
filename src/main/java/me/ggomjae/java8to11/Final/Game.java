package me.ggomjae.java8to11.Final;

import java.lang.annotation.*;

// @Target(ElementType.TYPE_USE) 같은 경우에는 모든 Type에 다 가능 진짜 다 가능.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // TYPE_PARAMETER 으로 하면 오류난다. 범위 때문에.
@Repeatable(GameContainer.class) // 반복할 수 있는 어노테이션
public @interface Game {
    String value();
}
