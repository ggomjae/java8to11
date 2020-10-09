package me.ggomjae.java8to11.Concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /*
            1번째 방법
            Callable<String> hello = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return null;
                }
            };
        */

        /* 2번째 방법 */
        Callable<String> gomjae = () -> {
          Thread.sleep(2000L);
          return "Gomjae";
        };
        //executorService.submit(hello);
        Future<String> gomjaeSubmit = executorService.submit(gomjae);

        System.out.println(gomjaeSubmit.isDone());
        System.out.println("Start");

        gomjaeSubmit.get(); // 기다린다 결과를 갖고올때까지. 그래서 블록킹콜이라고한다.

        System.out.println("End");
        System.out.println(gomjaeSubmit.isDone());
        executorService.shutdown();

        /////////////////////////////////////////////////////////////////////
        //           여러 개 callable                                       //
        /////////////////////////////////////////////////////////////////////

        Callable<String> one = () -> {
            Thread.sleep(2000L);
            return "one";
        };

        Callable<String> two = () -> {
            Thread.sleep(3000L);
            return "two";
        };

        // 필기 그림 보기 //
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(one,two));
        for(Future<String> f : futures){
            System.out.println(f.get());
        }

        executorService.shutdown();
    }
}
