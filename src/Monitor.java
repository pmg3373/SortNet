
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Monitors the values of the network as they flow through
 * Controls getting and putting of values as well
 * @author Pat
 */
public class Monitor {
    HashMap<Integer, Integer> temp;
    
    /**
     * Sets up some monitor locals
     */
    public Monitor(){
        temp = new HashMap<Integer, Integer>();
    }
    
    /**
     * Gets the value at monitor location i
     * @param i Monitor location index
     * @return The value found at the index
     */
    public synchronized Integer getValue(int i){
        while(temp.get(i) == null){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return temp.get(i);
    }
    
    /**
     * Attempts to put a value into the monitor location i. Location must be empty.
     * Throws an error if a value is already stored there.
     * @param i Monitor Location Index
     * @param value The value to store
     * @throws Exception 
     * Throws if location is not empty
     */
    public synchronized void putValue(int i, Integer value) throws Exception{
        if(temp.get(i) != null){
            System.exit(-1);
        }
        else{
            temp.put(i, value);
            notifyAll();
        }
    }
}