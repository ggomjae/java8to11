package me.ggomjae.java8to11.Final;

import java.util.Arrays;

@Game("액션") // String value() 를 선언해서 "액션" 넣을 수 있고 TYPE_USE 라 어느 타입이든 가능.
@Game("로맨스")
public class FinalServer {
    public static void main(String[] args){

        Game[] games = FinalServer.class.getAnnotationsByType(Game.class);
        Arrays.stream(games).forEach( game ->{
            System.out.println(game.value());
        });

        // 중복으로 했다면 이렇게도 가능
        GameContainer gameContainer = FinalServer.class.getAnnotation(GameContainer.class);
        Arrays.stream(gameContainer.value()).forEach( game ->{
            System.out.println(game.value());
        });
    }

    static class enjoyGame<@Game("액션") T>{  // @Target 이게 TYPE_PARAMETAR
        // <C> 는 Type_PARAMETAR, C는 Type 둘이 다른것. 그래서 저기다가 가능
        public static <@Game("액션") C> void  enjoy(C c){
            System.out.println(c);
        }
    }
}
