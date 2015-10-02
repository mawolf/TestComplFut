/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net;

import java.net.URI;

/**
 *
 * @author martin
 */
public class Message {
    
    private final URI uri;
    private final String protocol;
    private final String content;

    public Message(URI uri, String protocol, String content) {
        this.uri = uri;
        this.protocol = protocol;
        this.content = content;
    }

    public URI getUri() {
        return uri;
    }

    public String getContent() {
        return content;
    }

    public String getProtocol() {
        return protocol;
    }
    
}
