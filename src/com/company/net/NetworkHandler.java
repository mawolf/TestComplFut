/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net;

import com.company.EventListener;
import com.company.EventQueue;
import com.company.EventQueueWorker;
import com.company.net.channel.CommChannel;
import com.company.process.CommProcess;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author martin
 */
public class NetworkHandler {
    
    private final int WORKERS_NUM = 4;
    
    private final Map<URI,Map<String, CommChannel>> channels = new HashMap<>();
    
    private final Map<CommChannel, EventQueue<CommProcess>> writerQueues
            = new HashMap<>();
    private final Map<CommChannel, EventQueue<CommProcess>> readerQueues
            = new HashMap<>();
    private final EventQueue<ChannelEvent> eventQueue = new EventQueue<>();
    
    private final ExecutorService executor = Executors.newFixedThreadPool(WORKERS_NUM);

    private class NetworkWorker extends EventQueueWorker<ChannelEvent> {

        public NetworkWorker(EventQueue<ChannelEvent> queue) {
            super(queue);
        }

        @Override
        public void work(ChannelEvent event) {
            CommChannel channel = event.getChannel();
            if (event.getType() == ChannelEvent.ChannelEventType.WRITE_READY) {
                CommProcess process = getWriterQueue(channel).poll();
                assert(process != null);
                channel.send(process);
            } else if (event.getType() == ChannelEvent.ChannelEventType.READ_READY) {
                CommProcess process = getReaderQueue(channel).poll();
                assert(process != null);
                channel.recv(process);
            }
        }
        
    }
    
    public NetworkHandler() {
        for (int i = 0; i < WORKERS_NUM; i ++)
            executor.execute(new NetworkWorker(eventQueue));
    }
    
    private EventQueue<CommProcess> getWriterQueue(CommChannel channel) {
        return getQueue(writerQueues, channel);
    }    
    
    private EventQueue<CommProcess> getReaderQueue(CommChannel channel) {
        return getQueue(readerQueues, channel);
    }
    
    private EventQueue<CommProcess> getQueue(
            Map<CommChannel, EventQueue<CommProcess>> queueMap, 
            CommChannel channel) {
        EventQueue<CommProcess> queue = queueMap.get(channel);
        if (queue == null) {
            synchronized(queueMap) { 
                queueMap.putIfAbsent(channel, new EventQueue<>());
            }
        }
        return queueMap.get(channel);
    }
    
    public void send(CommProcess process) {
        Message message = process.getMessage();
        CommChannel channel = getChannel(message.getUri(), message.getProtocol());
        getWriterQueue(channel).enqueue(process);
        // Only add event if channel is not already writing
        if (channel.setWrite(true))
            eventQueue.enqueue(new ChannelEvent(channel, ChannelEvent.ChannelEventType.WRITE_READY));
    }
    
    public CommChannel getChannel(URI uri, String protocol) {
        Map<String, CommChannel> protocolChannels = channels.get(uri);
        if (protocolChannels == null)
            return null;
        return protocolChannels.get(protocol);
    }
    
    public void addChannel(CommChannel channel) {
        channel.addReadListener(new ReadChannelListener());
        channel.addWriteListener(new WriteChannelListener());
        Map<String, CommChannel> protocolChannels = channels.get(channel.getUri());
        if (protocolChannels == null) {
            protocolChannels = new HashMap<>();
            channels.put(channel.getUri(), protocolChannels);
        }
        protocolChannels.put(channel.getProtocol(), channel);
    }
    
    private class WriteChannelListener extends EventListener<CommChannel> {
        
        @Override
        public void handle(CommChannel channel) {
            if (!channel.setWrite(false))
                throw new IllegalStateException();
            if (writerQueues.get(channel).peek() != null && channel.setWrite(true))
                eventQueue.enqueue(new ChannelEvent(channel, ChannelEvent.ChannelEventType.WRITE_READY));
        }
        
    }
    
    private class ReadChannelListener extends EventListener<CommChannel> {
        
        @Override
        public void handle(CommChannel channel) {
            if (!channel.setRead(false))
                throw new IllegalStateException();
            if (writerQueues.get(channel).peek() != null && channel.setRead(true))
                eventQueue.enqueue(new ChannelEvent(channel, ChannelEvent.ChannelEventType.READ_READY));
        }
        
    }
    
}
