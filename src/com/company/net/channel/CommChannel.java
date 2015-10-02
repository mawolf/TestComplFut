/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net.channel;

import com.company.net.Message;
import com.company.process.CommProcess;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author martin
 */
abstract public class CommChannel {
    
    private final URI uri;
    private final String protocol;

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
    
    abstract public CompletableFuture send(CommProcess process, Message message);
    
    abstract public CompletableFuture<Message> recv(CommProcess process);
    
    abstract public CompletableFuture<Message> recvResponseFor(CommProcess process, Message message);
    
}
