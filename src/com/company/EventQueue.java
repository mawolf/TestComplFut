package com.company;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:02
 */
public class EventQueue<T> {

    final private ConcurrentLinkedQueue<T> baseQueue = new ConcurrentLinkedQueue<>();

    public void enqueue(T event) {
        if (baseQueue.peek() == null) {
            baseQueue.add(event);
            synchronized (baseQueue) {
                baseQueue.notifyAll();
            }
        }
        else {
            baseQueue.add(event);
        }
    }

    public T dequeue() throws InterruptedException {
        T element = null;
        while ((element = baseQueue.poll()) == null) {
            synchronized (baseQueue) {
                baseQueue.wait();
            }
        }
        return element;
    }

}
