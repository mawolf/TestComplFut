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

    public final Mutex writeMutex = new Mutex();
    public final Mutex readMutex = new Mutex();
    
    public SocketCommChannel(URI uri, String protocol, AsynchronousSocketChannel channel) {
        super(uri, protocol);
        this.channel = channel;
        bis = new BufferedInputStream(Channels.newInputStream(channel));
        bos = new BufferedOutputStream(Channels.newOutputStream(channel));
    }

    @Override
    public CompletableFuture send(CommProcess process, Message message) {
        
        try {
            if (!writeMutex.attempt(0)) {
                // Resubmit process;
                process.getJolieMain().execute(
                        new WriterProcess(process.getJolieMain(), message) {
                            @Override
                            public void callback() {
                                process.callback();
                            }
                        });
                return null;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SocketCommChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CompletableFuture future = new CompletableFuture();        
        future.thenApply((Void) -> {
            writeMutex.release();
            Logger.getGlobal().log(Level.INFO, "Sent msg: {0}", message.getContent());
            process.callback();
            return null;
        });
            
        byte[] bytes = message.getContent().getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        // We can add timeout and timeout handling here.
        channel.write(buffer, message, new CompletionHandler<Integer, Message>() {

            @Override
            public void completed(Integer result, Message attachment) {
                future.complete(result);
            }

            @Override
            public void failed(Throwable exc, Message attachment) {
                future.completeExceptionally(exc);
            }

        });
        return future;
    }

    @Override
    public CompletableFuture<Message> recv(CommProcess process) {
        CompletableFuture<Message> future = new CompletableFuture();
        future.thenApply((Message msg) -> {
            Logger.getGlobal().log(Level.INFO, "Recieved msg: {0}", msg.getContent());
            //process.callback();
            return msg;
        });
        ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
        // We can add timeout and timeout handling here.
        channel.read(inputBuffer, null, new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer bytesRead, Object attachment) {
                byte[] buffer = new byte[bytesRead];
                // Rewind the input buffer to read from the beginning
                inputBuffer.rewind();
                inputBuffer.get(buffer);
                // I guess we could have read the content of multiple messages
                Message message = new Message(getUri(), getProtocol(), new String(buffer));
                future.complete(message);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                future.completeExceptionally(exc);
            }

        });
        return future;
    }

    @Override
    public CompletableFuture<Message> recvResponseFor(CommProcess process, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
