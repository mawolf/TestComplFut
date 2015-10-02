package com.company;

import java.util.concurrent.CompletableFuture;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:35
 */
public class SequentialProcess extends Process<Void> {

    private final Process[] processes;

    public SequentialProcess(Process... processes) {
        this.processes = processes;
    }

    @Override
    public CompletableFuture<Void> run() {
        CompletableFuture<?>[] futures = new CompletableFuture<?>[this.processes.length];

        for (int i = 0; i < this.processes.length; i++) {
            futures[i] = this.processes[i].run();
        }
        return CompletableFuture.allOf(futures);
    }
}
