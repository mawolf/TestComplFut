/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net;

import com.company.Event;
import com.company.net.channel.CommChannel;
import com.company.process.Process;

/**
 *
 * @author martin
 */
public class ChannelEvent {
    
    public static enum ChannelEventType  {
        READ_READY, WRITE_READY;
    }
    
    private final ChannelEventType type;
    private final CommChannel channel;
    
    public ChannelEvent(CommChannel channel, ChannelEventType type) {
        this.channel = channel;
        this.type = type;
    }

    public CommChannel getChannel() {
        return channel;
    }

    public ChannelEventType getType() {
        return type;
    }
    
}
