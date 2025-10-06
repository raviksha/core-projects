// https://ouryahoo.atlassian.net/browse/SHERPA-23149
// https://git.ouryahoo.com/gist/raviksha/8a6b5204948aa76f1847adf9f8360c1e
package org.self.yahoo.jdk21;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreads {
    private static final int TASK_COUNT = 10_000;

    /*
        1. Virtual thread is very light weight
        2. They solve the problem of throughput, not latency
        3. Never require to pool any virtual threads. Defeats the purpose from using platform threads as the context switching is very fast
           No conserving of resources is required
        4. Careful with pinning virtual threads with platform threads. Sync block execution lock contention using Virtual threads will hold up resources similar to platform threads
        5. Thread Local usage with Virtual threads needs to be reviews. Holding references for a 1M virtual threads in memory will not be efficient
        6.
     */
    public static void main(String[] args) throws Exception {


        System.out.println("main: IsVirtualThread: " + Thread.currentThread().isVirtual());
        //cachedThreadPool();
        fixedThreadPool();
        virtualThreads();

//        Thread.startVirtualThread(() ->  {
//            System.out.println("main: IsVirtualThread: " + Thread.currentThread().isVirtual());
//        }).join(); // Waits for the virtual thread to join
//
//        virtualThreadsBuilder();

    }



    private static void virtualThreads() {
        var begin = Instant.now();
        /*
         * Creates an Executor that starts a new virtual Thread for each task.
         * The number of threads created by the Executor is unbounded.
         */
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, TASK_COUNT).forEach((i) -> executor.submit(() -> {
               // Thread.sleep(Duration.ofSeconds(1));
                Thread.sleep(Duration.ofMillis(500));
                return i;
            }));
        }
        var end = Instant.now();
        System.out.println("virtualThreads: " + Duration.between(begin, end));
    }

    /*
        Thread pool ready with a pool of 1000 platform threads
     */
    private static void fixedThreadPool() {
        var begin = Instant.now();
        /*
            Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.
            At any point, at most nThreads threads will be active processing tasks.
         */
        try (var executor = Executors.newFixedThreadPool(1000)) {
            IntStream.range(0, TASK_COUNT).forEach((i) -> executor.submit(() -> {
               // Thread.sleep(Duration.ofSeconds(1));
                Thread.sleep(Duration.ofMillis(500));
                return i;
            }));
        }
        var end = Instant.now();
        System.out.println("fixedThreadPool: " + Duration.between(begin, end));
    }

    /*
        Creates each Platform thread on demand
     */
    private static void cachedThreadPool() {
        var begin = Instant.now();
        try (var executor = Executors.newCachedThreadPool()) {
            IntStream.range(0, TASK_COUNT).forEach((i) -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                return i;
            }));
        }
        var end = Instant.now();
        System.out.println("cachedThreadPool: " + Duration.between(begin, end));
    }

    private static void virtualThreadsBuilder() throws Exception{
        Thread.Builder builder = Thread.ofVirtual().name("Yahoo-virtual-thread");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("virtualThreadsBuilder: Hello virtual thread");
            }
        };

        Thread thread = builder.start(runnable);
        thread.join();
    }
}
