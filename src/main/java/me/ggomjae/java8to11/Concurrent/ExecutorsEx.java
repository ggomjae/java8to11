package me.ggomjae.java8to11.Concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsEx {
    public static void main(String[] args){
        /* 가장 고전적인 방법*/
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        // 명시적으로 셧다운을 해야한다. 다음 작업이 오는것을 기다리고 프로세스를 안죽인다.
        executorService.shutdown();

        /* 2번째 방법 */
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        executorService1.shutdown();

        /* 결과적으로 2개만 줬어도 실행은 다된다. 필기 그림 참고*/
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("ggomjae"));
        executorService2.submit(getRunnable("kyungjae"));
        executorService2.submit(getRunnable("gomjae"));
        executorService2.submit(getRunnable("blog"));
        executorService2.submit(getRunnable("1993"));

        executorService2.shutdown();

        /* ScheduledExecutorService */
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("ggomjae"),5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();


    }

    private static Runnable getRunnable(String message) {
        return ()->{
            System.out.println( message + Thread.currentThread().getName());
        };
    }
}
