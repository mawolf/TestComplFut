package com.company;

import java.util.concurrent.*;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:00
 */
public class JolieMain {

    final private static int WORKER_THREADS_NUM = 4;

    final private EventQueue<Event> eq = new EventQueue<>();
    final private Executor threadPoolExecutor = Executors.newFixedThreadPool(WORKER_THREADS_NUM);

    private class WorkerThread implements Runnable {
        private boolean exit = false;

        @Override
        public void run() {
            while (!exit) {
                try {
                    Event event = eq.dequeue();
                    event.getProcess().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void run() throws InterruptedException {

        System.out.println("run() called");

        for ( int i = 0; i < WORKER_THREADS_NUM; i++ ) {
            threadPoolExecutor.execute(new WorkerThread());
        }

//        Thread.sleep(1000);

        System.out.println("WorkerThreads created");
        Event event = new Event(new SequentialProcess(new Process<String>() {
            @Override
            public CompletableFuture<String> run() {
                System.out.println("Wohoo!");
                return new CompletableFuture<String>();

            }
        }));

        eq.enqueue(event);
        System.out.println("enqueue event");

        for ( int i = 0; i < WORKER_THREADS_NUM; i++ ) {
            threadPoolExecutor.execute(new WorkerThread());
        }


    }

}
