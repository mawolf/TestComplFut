package com.company.process;

import com.company.JolieMain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Martin Wolf
 * At: 02/10/2015, 12:34
 */
public abstract class Process {

    private JolieMain jolieMain;
    private Process parent;

    public Process(JolieMain jolieMain) {
        this.parent = null;
        this.jolieMain = jolieMain;
    }
    
    public Process(Process parent) {
        this.parent = parent;
        this.jolieMain = parent.jolieMain;
    }
    
    public JolieMain getJolieMain() {
        return jolieMain;
    }

    public Process getParent() {
        return parent;
    }

    public void setParent(Process parent) {
        this.parent = parent;
    }
    
    abstract public void run();
    
    public void callback() {
        if (parent != null)
            parent.callback();
    }
    
    public void callback(Throwable exception) {
        if (parent != null)
            parent.callback(exception);
        else
            Logger.getGlobal().log(Level.SEVERE, exception.getMessage(), exception);
    }
}
