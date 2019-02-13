/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookdataconverter;

/**
 *
 * @author pervez
 */
public class BookDataConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 2) {
            String inputFileName = args[0];
            String outputFormat = args[1];
            ConversionProcessor conversionProcessor = new ConversionProcessor(inputFileName, outputFormat);
            conversionProcessor.process();
        } else {
            System.out.println("Number of parameters is incorrect");
        }
    }
    
}
