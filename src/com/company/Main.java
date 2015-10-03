package com.company;

import com.company.net.Message;
import com.company.net.channel.SocketCommChannel;
import com.company.process.ParallelProcess;
import com.company.process.Process;
import com.company.process.SequentialProcess;
import com.company.process.CommProcess;
import com.company.process.WriterProcess;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Main {

    private static final int PORT_NUM = 8001;
    
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException, URISyntaxException {
        JolieMain jolieMain = new JolieMain();
        jolieMain.run();
        
        AsynchronousServerSocketChannel assc = 
                AsynchronousServerSocketChannel.open();
        assc.bind(new InetSocketAddress(PORT_NUM));
        System.out.println("Server listening for connections...");
        while(true) {
            Future<AsynchronousSocketChannel> future = assc.accept();
            AsynchronousSocketChannel asc = future.get();
            System.out.println("Got new connection!");
            URI uri = new URI("localhost");
            SocketCommChannel channel = 
                    new SocketCommChannel(uri, "dummy", asc);
            jolieMain.getNetworkHandler().addChannel(channel);
            
            int i = 0;
            jolieMain.execute(new SequentialProcess(jolieMain, new Process[] {
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Sequential response: %d\n", i++))),
            }));
            i = 0;
            jolieMain.execute(new ParallelProcess(jolieMain, new Process[] {
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
                new WriterProcess(jolieMain, new Message(uri, "dummy", String.format("Parallel response: %d\n", i++))),
            }));
        }
    }

}
