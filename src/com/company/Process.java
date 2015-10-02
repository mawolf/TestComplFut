package com.company;

import java.util.concurrent.CompletableFuture;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:34
 */
public abstract class Process<T> {

    abstract public CompletableFuture<T> run();

}
