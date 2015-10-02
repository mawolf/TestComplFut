/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net;

import com.company.net.channel.CommChannel;
import com.company.process.CommProcess;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author martin
 */
public class NetworkHandler {
    
    private final Map<URI,Map<String, CommChannel>> channels = new HashMap<>();
    
    public CompletableFuture send(CommProcess process, Message message) {
        CommChannel channel = getChannel(message.getUri(), message.getProtocol());
        return channel.send(process, message);
    }
    
    public CommChannel getChannel(URI uri, String protocol) {
        Map<String, CommChannel> protocolChannels = channels.get(uri);
        if (protocolChannels == null)
            return null;
        return protocolChannels.get(protocol);
    }
    
    public void addChannel(CommChannel channel) {
        Map<String, CommChannel> protocolChannels = channels.get(channel.getUri());
        if (protocolChannels == null) {
            protocolChannels = new HashMap<>();
            channels.put(channel.getUri(), protocolChannels);
        }
        protocolChannels.put(channel.getProtocol(), channel);
    }
    
}
