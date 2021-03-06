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
abstract public class CommProcess extends Process {

    protected Message message;
    
    public CommProcess(Process parent, Message message) {
        super(parent);
        this.message = message;
    }
    
    public CommProcess(JolieMain jolieMain, Message message) {
        super(jolieMain);
        this.message = message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
    
    public void callback(Message message) {
        setMessage(message);
        callback();
    }
    
}
