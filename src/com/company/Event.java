package com.company;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:29
 */
public class Event {

    private Process<?> process;

    public Event ( Process<?> process ) {
        this.process = process;
    }

    @Deprecated
    public void onCompleted () {

    }

    @Deprecated
    public void onFailed (Throwable ex) {

    }

    public Process<?> getProcess() {
        return process;
    }
}
