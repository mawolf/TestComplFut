package com.company;

import java.util.concurrent.CompletableFuture;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 13:19
 */
public class ParallelProcess extends Process<Void> {

    private final Process[] processes;

    public ParallelProcess(Process ...processes) {
        this.processes = processes;
    }

    @Override
    public CompletableFuture<Void> run() {
        CompletableFuture<?>[] futures = new CompletableFuture<?>[this.processes.length];
        for ( int i = 0; i < this.processes.length; i++ ) {
            futures[i] = this.processes[i].run();
        }
        return CompletableFuture.allOf(futures);
    }
}
