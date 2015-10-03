package com.company;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:02
 */
public class EventQueue<T> {

    final private ConcurrentLinkedQueue<T> baseQueue = new ConcurrentLinkedQueue<>();

    public void enqueue(T event) {
        boolean doNotify = baseQueue.peek() == null;
        
        baseQueue.add(event);
        
        if (doNotify) {
            synchronized (baseQueue) {
                baseQueue.notifyAll();
            }
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
    
    public T poll() {
        return baseQueue.poll();
    }
    
    public T peek() {
        return baseQueue.peek();
    }

}
