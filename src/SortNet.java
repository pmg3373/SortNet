
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pat
 * 
 * Class SortNet is the main class of the SortNet program. It takes as input 8
 * values from the command line and uses a multi-threaded sorting network to
 * sort them in order of least to greatest.
 */
public class SortNet {
    
    private static final int NUM_INPUTS = 8;
    public static final boolean DEBUG = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Comparitor> comps = new ArrayList<Comparitor>();
        Integer[] input = new Integer[NUM_INPUTS];
        Monitor monitor = new Monitor();
        
        if(args.length != NUM_INPUTS){
            System.out.println("Error: Incorrect number of inputs"
                    + " Expected: " + NUM_INPUTS + " Recieved: " + args.length);
            return;
        }
        
        if(!validateInput(args, input)){
            System.out.println("Error: Malformed Input."
                    + " One or more inputs was a non integer value");
            return;
        }
        
        OutputPrinter output = 
                new OutputPrinter(new Integer[]{62, 63, 61, 59, 55, 51, 45, 39}
                , monitor);
        
        buildNetworkManual(comps, monitor);
        
        InputBuilder builder = new InputBuilder(input, monitor);
        
        if(DEBUG)
            System.out.println("Main: Done Creating Objects");
        
        
        //Run in this order, output, thread comparitors, input builder
        new Thread(output).start();
        
        for(int i = 0; i < comps.size(); i++){
            new Thread(comps.get(i)).start();
        }
        
       new Thread(builder).start();
        
        if(DEBUG)
            System.out.println("Main: All Threads have been started");
        
    }
    
    /**
    * Validates input for the SortNet program. Verifies that all values in input
    * are integers.
    * 
    * @param input The input array to validate
    * @param inputConv The input array to store the converted Integers.
    * 
    * @return True if all input is valid, False if otherwise
    */
    public static boolean validateInput(String[] input, Integer[] inputConv){
        for(int i = 0; i < input.length; i++){
            if( (inputConv[i] = tryParse(input[i])) == null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Attempts to parse an integer gracefully.
     * @param in The string to parse
     * @return The Integer value of in if it is an integer, otherwise null
     */
    public static Integer tryParse(String in){
        Integer out;
        try{
            out = Integer.parseInt(in);
        }
        catch(Exception e){
            return null;
        }
        return out;
    }
    
    /**
     * Manually build the 8 variable sorting network
     * @param comps An ArrayList to store the Comparitors in.
     * @param mon A reference to the monitor object
     */
    public static void buildNetworkManual(ArrayList<Comparitor> comps, 
            Monitor mon){
        comps.add(new Comparitor(mon, 0, 1, 8, 9));
        
        comps.add(new Comparitor(mon, 9, 2, 10, 11));
        
        comps.add(new Comparitor(mon, 8, 10, 12, 13));
        comps.add(new Comparitor(mon, 11, 3, 14, 15));
        
        comps.add(new Comparitor(mon, 13, 14, 16, 17));
        comps.add(new Comparitor(mon, 15, 4, 18, 19));
        
        comps.add(new Comparitor(mon, 12, 16, 20, 21));
        comps.add(new Comparitor(mon, 17, 18, 22, 23));
        comps.add(new Comparitor(mon, 19, 5, 24, 25));
        
        comps.add(new Comparitor(mon, 21, 22, 26, 27));
        comps.add(new Comparitor(mon, 23, 24, 28, 29));
        comps.add(new Comparitor(mon, 25, 6, 30, 31));
        
        comps.add(new Comparitor(mon, 20, 26, 32, 33));
        comps.add(new Comparitor(mon, 27, 28, 34, 35));
        comps.add(new Comparitor(mon, 29, 30, 36, 37));
        comps.add(new Comparitor(mon, 31, 7, 38, 39));
        
        comps.add(new Comparitor(mon, 33, 34, 40, 41));
        comps.add(new Comparitor(mon, 35, 36, 42, 43));
        comps.add(new Comparitor(mon, 37, 38, 44, 45));
        
        comps.add(new Comparitor(mon, 32, 40, 46, 47));
        comps.add(new Comparitor(mon, 41, 42, 48, 49));
        comps.add(new Comparitor(mon, 43, 44, 50, 51));
        
        comps.add(new Comparitor(mon, 47, 48, 52, 53));
        comps.add(new Comparitor(mon, 49, 50, 54, 55));
        
        comps.add(new Comparitor(mon, 46, 52, 56, 57));
        comps.add(new Comparitor(mon, 53, 54, 58, 59));
        
        comps.add(new Comparitor(mon, 57, 58, 60, 61));
        
        comps.add(new Comparitor(mon, 56, 60, 62, 63));
        
    }
}
