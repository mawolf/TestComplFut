package com.company.process;

import com.company.JolieMain;
import java.util.concurrent.CompletableFuture;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:35
 */
public class SequentialProcess extends ComposedProcess {

    private int currentProcess = 0;

    public SequentialProcess(JolieMain jolieMain, Process... processes) {
        super(jolieMain, processes);
    }

    @Override
    public void run() {
        getJolieMain().execute(children.get(currentProcess));
    }
    
    @Override
    public void callback() {
        if (++currentProcess < children.size())
            run();
        else
            super.callback();
    }
}
