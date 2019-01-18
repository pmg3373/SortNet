
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Prints the output of the sorting network
 * @author Pat
 */
public class OutputPrinter implements Runnable{
    
    Integer[] input;
    Monitor monitor;
    private final boolean DEBUG = false;
    
    /**
     * Prepares OutputPrinter for run
     * @param input Monitor locations of the outputs to print
     * @param monitor Reference to the monitor object
     */
    public OutputPrinter(Integer[] input, Monitor monitor){
        this.input = input;
        this.monitor = monitor;
    }
    
    public void run(){
        Integer[] output = new Integer[input.length];
        if(DEBUG){
            System.out.println("\nOutput Thread Started");
        }
        for(int i = 0; i < output.length; i++){
            output[i] = monitor.getValue(input[i]);
            if(DEBUG){
                System.out.println("\nOutput Thread: Got Input " + input[i] + 
                        " Value is " + output[i]);
            }
        }
        
        //Print Formatting
        System.out.print(output[0] + "");
        for(int j = 1; j < input.length-1; j++){
            System.out.print(" " + output[j]);
        }
        System.out.print(" " + output[input.length - 1] + "\n");
    }
    
}
