
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Comparitor class for the SortNetProgram. Compares two integers found at in1 
 * and in2 of the monitor and puts the larger in out2 and the smaller in out1.
 * @author Pat
 */
public class Comparitor implements Runnable {
    Monitor monitor;
    int in1, in2, out1, out2;
    private final boolean DEBUG = false;
    
    /**
     * Prepares comparitor for running
     * @param monitor Reference to the monitor object
     * @param in1 Location of the first input in monitor
     * @param in2 Location of the second input in monitor
     * @param out1 Location of the first output in monitor
     * @param out2 Location of the second output in monitor
     */
    public Comparitor(Monitor monitor, int in1, int in2, int out1, int out2){
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
        this.out2 = out2;
        this.monitor = monitor;
    }
    
    
    /**
     * Compares two values, stores the larger in out2, the smaller in out1
     * @param val1 Value 1
     * @param val2 Value 2
     */
    private void compare(int val1, int val2){
        if(DEBUG){
            System.out.println("\n Comparitor for " + in1 + ", " + in2 + 
                ". Starting Compare of in1: " + val1 + " in2: " + val2);
        }
        
        if( val1 < val2 ){
            try{
                if(DEBUG){
                    System.out.println("\n Comparitor for " + in1 + ", " + in2 + 
                        ". Value 2 is larger.");
                     System.out.println("\n Comparitor for " + in1 + ", " + in2 +
                            "Putting " + val1 + " at " + out1 + " Putting " + 
                            val2 + " at " + out2);
                }
                monitor.putValue(out1, val1);
                monitor.putValue(out2, val2);
            }
            catch(Exception e){
                System.err.print(e.getMessage());
            }
        }
        else{
            try{
                if(DEBUG){
                    System.out.println("\n Comparitor for " + in1 + ", " + in2 + 
                        ". Value 1 is larger or equal.");
                    System.out.println("\n Comparitor for " + in1 + ", " + in2 +
                            "Putting " + val1 + " at " + out2 + " Putting " + 
                            val2 + " at " + out1);
                }
                monitor.putValue(out1, val2);
                monitor.putValue(out2, val1);
            }
            catch(Exception e){
                System.err.print(e.getMessage());
            }
        }
        
    }
    
    public void run(){
        Integer v1, v2;
        v1 = monitor.getValue(in1);
        v2 = monitor.getValue(in2);
        
        if(DEBUG){
                System.out.println("\n Comparitor for " + in1 + ", " + in2 + 
                        ". Values Recieved in1: " + v1 + " in2: " + v2);
            }
        this.compare(v1, v2);
    }
    
}
