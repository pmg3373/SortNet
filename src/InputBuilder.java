
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Places initial input into the monitor
 * @author Pat
 */
public class InputBuilder implements Runnable {
    
    Integer[] input;
    Monitor monitor;
    private final boolean DEBUG = false;
    
    /**
     * Prepare InputBuilder for run
     * @param input Values to put into monitor
     * @param monitor Reference to the monitor object
     */
    public InputBuilder(Integer[] input, Monitor monitor){
        this.input = input;
        this.monitor = monitor;
    }
    
    public void run(){
        for(int i = 0; i < input.length; i++){
            try {
                monitor.putValue(i, input[i]);
                if(DEBUG){
                    System.out.println("\nInput Builder: Putting value " 
                            + input[i] + " at " + i);
                }
            } catch (Exception ex) {
                System.out.print(ex.getMessage());
                return;
            }
        }
        if(DEBUG){
                    System.out.println("\nInput Builder: Done");
        }
    }    
}
