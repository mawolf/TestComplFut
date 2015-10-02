package com.company.process;

import com.company.JolieMain;
import com.company.process.Process;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 13:19
 */
public class ParallelProcess extends ComposedProcess {

    private final AtomicInteger callbackCount = new AtomicInteger();

    public ParallelProcess(JolieMain jolieMain, Process ... processes) {
        super(jolieMain, processes);
    }

    @Override
    public void run() {
        getJolieMain().execute(children.toArray(new Process[0]));
    }
    
    @Override
    public void callback() {
        if (callbackCount.getAndIncrement() == children.size())
            super.callback();
    }
}
