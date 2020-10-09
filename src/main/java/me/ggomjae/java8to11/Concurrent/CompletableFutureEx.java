package me.ggomjae.java8to11.Concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // ctrl + alt + v 하면 자동완성된다. new completableFuture<>(); 하면
        CompletableFuture<String> future = new CompletableFuture<>();
        // CompletableFuture<String> future = CompletableFuture.completedFuture("ggomjae") 도 가능
        future.complete("ggomjae");
        future.get();
        // Return 하는 게 없을 때
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("ggomjae" + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        // Return 값이 있을 때
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "ggomjae";
        });
        stringCompletableFuture.get();

        //////////////////////////////////////////////////////////////
        //          여기 까진 Future 와 비슷                          //
        /////////////////////////////////////////////////////////////

        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "ggomjae";
        }).thenApply(String::toUpperCase);
        // 이렇게 콜백함수를 쓸 수 있음. 단, Get은 꼭 써줘야함
        stringCompletableFuture2.get();

        // 리턴이 필요없을 때.  + 그냥 어떠한 작업만하고 싶을 때는 thenRun !
        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> {
            return "ggomjae";
        }).thenAccept((s) -> {
            System.out.println(s + "이렇게 리턴 없이 그냥");
        });

        thenAccept.get();

        /////////////////////// 조합 /////////////////////////

        // 이렇게 stringCompletableFutrue2 를 하고나서 ~ thenAccept를 해야하는데 조합을 하면 !?
        stringCompletableFuture2.get();
        stringCompletableFuture.get();

        //stringCompletableFuture.thenCompose(s-> getName(s));
        stringCompletableFuture.thenCompose(CompletableFutureEx::getName);

        // 서로 A , B가 연관관계가 없을 떄.
        CompletableFuture<String> A = CompletableFuture.supplyAsync(() -> {
            return "A";
        });

        CompletableFuture<String> B = CompletableFuture.supplyAsync(() -> {
            return "B";
        });

        CompletableFuture<String> ABcombine = A.thenCombine(B, (aResult, bResult) -> {
            return aResult + bResult;
        });
        System.out.println(ABcombine.get());

        // 만약 B가 Integer을 반환한다면 서로 타입이 달라서 Null을 반환한다. 이런 안좋은점이있다.
        CompletableFuture<Void> ABcombine2 = CompletableFuture.allOf(A,B)
                .thenAccept(System.out::println);

        // 예외 처리 - exceptionally, handle
        boolean error = true;
        CompletableFuture<String> C = CompletableFuture.supplyAsync(() -> {
            if(error){
                throw new IllegalArgumentException();
            }
            return "B";
        }).exceptionally( ex -> {
           return "error";
        });

        CompletableFuture<String> D = CompletableFuture.supplyAsync(() -> {
            if(error){
                throw new IllegalArgumentException();
            }
            return "D";
        }).handle( (result,e) -> {
           if(e != null) {
               return "error";
           }
            return result;
        });
    }

    private static CompletableFuture<String> getName(String s){
        return CompletableFuture.supplyAsync(()->{
           return s + "ggomjae";
        });
    }
}
