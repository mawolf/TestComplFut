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

    private class ProcessWorker extends EventQueueWorker<Event> { 

        public ProcessWorker(EventQueue<Event> queue) {
            super(queue);
        }
        
        @Override
        public void work(Event event) {
            Process process = event.getProcess();
            process.run();
            if (!(process instanceof CommProcess))
                process.callback();
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
            threadPoolExecutor.execute(new ProcessWorker(eq));
        }

    }
}
