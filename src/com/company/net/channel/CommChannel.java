/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net.channel;

import com.company.EventListener;
import com.company.net.Message;
import com.company.process.CommProcess;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author martin
 */
abstract public class CommChannel {
    
    private final URI uri;
    private final String protocol;
    private AtomicBoolean writing = new AtomicBoolean();
    
    protected final ConcurrentLinkedQueue<Message> recievedMessages = 
            new ConcurrentLinkedQueue<>();
    
    private ArrayList<EventListener> writeListeners = new ArrayList<>();
    private ArrayList<EventListener> readListeners = new ArrayList<>();

    public CommChannel(URI uri, String protocol) {
        this.uri = uri;
        this.protocol = protocol;
    }

    public URI getUri() {
        return uri;
    }
    
    public String getProtocol() {
        return protocol;
    }

    public ConcurrentLinkedQueue<Message> getRecievedMessages() {
        return recievedMessages;
    }
    
    abstract public void send(CommProcess process);
    
    abstract protected void recv();
    
    public void addWriteListener(EventListener<CommChannel> listener) {
        writeListeners.add(listener);
    }
    
    protected void notifyWriteListeners() {
        for (EventListener<CommChannel> listener : writeListeners)
            listener.handle(this);
    }
    
    public void addReadListener(EventListener<CommChannel> listener) {
        readListeners.add(listener);
    }
    
    protected void notifyReadListeners() {
        for (EventListener<CommChannel> listener : readListeners)
            listener.handle(this);
    }
    
    public boolean setWrite(boolean b) {
        return writing.compareAndSet(!b, b);
    }
}
