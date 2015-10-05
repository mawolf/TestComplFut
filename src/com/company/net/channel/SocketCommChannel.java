/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.net.channel;

import com.company.net.Message;
import com.company.process.CommProcess;
import com.company.process.Process;
import com.company.process.WriterProcess;
import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class SocketCommChannel extends CommChannel {

    private final AsynchronousSocketChannel channel;

    // TODO provide these for protocol implementations
    private final BufferedInputStream bis;
    private final BufferedOutputStream bos;

    public SocketCommChannel(URI uri, String protocol, AsynchronousSocketChannel channel) {
        super(uri, protocol);
        this.channel = channel;
        bis = new BufferedInputStream(Channels.newInputStream(channel));
        bos = new BufferedOutputStream(Channels.newOutputStream(channel));
        recv();
    }

    @Override
    public void send(CommProcess process) {
        Message message = process.getMessage();
        byte[] bytes = message.getContent().getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        // We can add timeout and timeout handling here.
        channel.write(buffer, message, new CompletionHandler<Integer, Message>() {
            @Override
            public void completed(Integer result, Message attachment) {
                Logger.getGlobal().log(Level.INFO, "Sent msg: {0}", attachment.getContent());
                process.callback();
                notifyWriteListeners();
            }

            @Override
            public void failed(Throwable exc, Message attachment) {
                process.callback(exc);
            }
        });
    }

    @Override
    protected void recv() {
        ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
        
        // We can add timeout and timeout handling here.
        channel.read(inputBuffer, null, new CompletionHandler<Integer, Void>() {

            @Override
            public void completed(Integer bytesRead, Void v) {
                if (bytesRead > 0) {
                    byte[] buffer = new byte[bytesRead];
                    // Rewind the input buffer to read from the beginning
                    inputBuffer.rewind();
                    inputBuffer.get(buffer);
                    // I guess we could have read the content of multiple messages
                    Message message = new Message(getUri(), getProtocol(), new String(buffer));
                    Logger.getGlobal().log(Level.INFO, "Recieved msg: {0}", message.getContent());
                    recievedMessages.add(message);
                    //Notify network handler
                    notifyReadListeners();
                }
                // Register for next read
                recv();
            }

            @Override
            public void failed(Throwable exc, Void v) {
                throw new UnsupportedOperationException("Not yet implemented");
            }

        });
    }
}
