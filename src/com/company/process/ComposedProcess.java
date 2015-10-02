/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.process;

import com.company.JolieMain;
import com.company.process.Process;
import java.util.ArrayList;
/**
 *
 * @author martin
 */
abstract public class ComposedProcess extends Process {

    protected final ArrayList<Process> children = new ArrayList<>();
    
    public ComposedProcess(JolieMain jolieMain, Process ... children) {
        super(jolieMain);
        addChild(children);
    }
    
    public ComposedProcess(Process parent, Process ... children) {
        super(parent);
        addChild(children);
    }
    
    public final void addChild(Process ... children) {
        for (Process child : children)
            addChild(child);
    }
    
    public final void addChild(Process child) {
        assert(child.getParent() == null || child.getParent() == this);
        child.setParent(this);
        children.add(child);
    }
}
