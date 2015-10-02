package com.company;

import com.company.process.Process;
import com.company.net.NetworkHandler;
import com.company.process.CommProcess;
import java.util.concurrent.*;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:00
 */
public class JolieMain {

    final private static int WORKER_THREADS_NUM = 4;

    final private EventQueue<Event> eq = new EventQueue<>();
    final private Executor threadPoolExecutor = Executors.newFixedThreadPool(WORKER_THREADS_NUM);
    final private NetworkHandler networkHandler = new NetworkHandler();

    private class ProcessWorker implements Runnable {
        private boolean exit = false;

        @Override
        public void run() {
            while (!exit) {
                try {
                    Event event = eq.dequeue();
                    Process process = event.getProcess();
                    process.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }
    
    /**
     * Registers the process for execution
     * @param process 
     */
    public void execute(Process process) {
        Event event = new Event(process);
        eq.enqueue(event);
    }
    
    /**
     * Registers the processes for parallel execution.
     * @param processes 
     */
    public void execute(Process ... processes) {
        for (Process process : processes)
            execute(process);
    }
    
    public void run() throws InterruptedException {

        for ( int i = 0; i < WORKER_THREADS_NUM; i++ ) {
            threadPoolExecutor.execute(new ProcessWorker());
        }

//        Event event = new Event(new SequentialProcess(this, new Process<String>(this) {
//            @Override
//            public CompletableFuture<String> run() {
//                System.out.println("Wohoo!");
//                return new CompletableFuture<String>();
//
//            }
//        }));

//        eq.enqueue(event);
    }
}
