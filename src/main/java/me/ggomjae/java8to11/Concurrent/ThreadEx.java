package me.ggomjae.java8to11.Concurrent;

public class ThreadEx {
    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.start();
        /* 1번째 방법
         순서상 Thread-0 출력 후 main 이 떠야하는데 main 다음에 Thread-0 이 뜬다.
         이렇게 순서를 보장하지 못한다.
         */
        System.out.println(Thread.currentThread().getName());

        /* 2번째 방법 */
        // Java 8 이전
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        // Java 8 이후
        Thread thread1 = new Thread(()-> {

            // 한번 재워보기 -> 자는 스레드에 리소스를 공급 x, 다른 스레드에 할당 - 먼저 일함
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // 이 익섹셥은 자고 있다가 누군가가 깨우면 이 블록이 실행된다.
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
        });

        thread.start();
        thread1.start();

        Thread thread2 = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 누군가 나를 깨우면 무한 루프를 꺼버린다.
                    return ;

                    /* thread.join() -> */
                    /*
                        throw new IllegalStateException(e);
                     */
                }
            }
        });

        // 꺼버리는게 아니라 깨우는 거다.
        thread2.interrupt();

        /*
            // main 스레드가 thread2가 끝날때까지 기다리는 것
            thread2.join();
         */
    }

    /* #1 첫번째 방법 */
    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
