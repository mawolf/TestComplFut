/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.process;

import com.company.JolieMain;
import com.company.net.Message;

/**
 *
 * @author martin
 */
public class WriterProcess extends CommProcess {

    public WriterProcess(Process parent, Message message) {
        super(parent, message);
    }
    
    public WriterProcess(JolieMain jolieMain, Message message) {
        super(jolieMain, message);
    }
    

    @Override
    public void run() {
        getJolieMain().getNetworkHandler().send(this, message);
    }
    
}
