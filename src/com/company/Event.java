package com.company;

import com.company.process.Process;
import java.util.concurrent.CompletableFuture;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:29
 */
public class Event {

    private final Process process;

    public Event ( Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

}
