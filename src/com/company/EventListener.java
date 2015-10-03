/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author martin
 */
abstract public class EventListener<T> {
    
    public abstract void handle(T event);
    
}
