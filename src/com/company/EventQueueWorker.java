/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import com.company.process.CommProcess;

/**
 *
 * @author martin
 */
public abstract class EventQueueWorker<T> implements Runnable {
    
    private boolean exit = false;
    private final EventQueue<T> eq;

    public EventQueueWorker(EventQueue<T> queue) {
        this.eq = queue;
    }
    
    @Override
    public void run() {
        while (!exit) {
            try {
                T queueItem = eq.dequeue();
                work(queueItem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public abstract void work(T queueItem);
    
    public void exit() {
        exit = true;
    }
}
