/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net;

import java.io.Serializable;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author martin
 */
public class Message implements Serializable {
    
    private static final AtomicLong idGenerator = new AtomicLong(1);
    
    private final long id = idGenerator.getAndIncrement();
    private URI uri;
    private String protocol;
    private long responseFor;
    private boolean response;
    private String content;

    public Message(URI uri, String protocol, String content) {
        this.uri = uri;
        this.protocol = protocol;
        this.content = content;
        this.responseFor = 0;
        this.response = false;
    }
    
    public Message(long responseFor, URI uri, String protocol, String content) {
        this.uri = uri;
        this.protocol = protocol;
        this.content = content;
        this.responseFor = responseFor;
        this.response = responseFor != 0;
    }
    
    public static Message createResponseFor(Message request) {
        Message msg = new Message(request.id, request.uri, request.protocol, null);
        return msg;
    }

    public URI getUri() {
        return uri;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    public String getProtocol() {
        return protocol;
    }
    
}
