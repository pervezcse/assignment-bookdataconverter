/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdataconverter;

import bookdataconverter.converter.Converter;
import bookdataconverter.converter.ConverterManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author pervez
 */
public class ConversionProcessor {

    private String inputFileName;
    private String outputFormat;
    private Converter inputFormatConverter = null;
    private Converter outputFormatConverter = null;

    public ConversionProcessor(String inputFileName, String outputFormat) {
        this.inputFileName = inputFileName;
        this.outputFormat = outputFormat;
    }

    public void process() {
        try {
            System.out.println("Reading input ...");
            byte[] inputData = readDataFromInputFile(this.inputFileName);
            System.out.println("++++");
            System.out.println(new String(inputData, "UTF-8"));
            System.out.println("­­­­");
            System.out.println("Guessing text format ... ");
            inputFormatConverter = ConverterManager.getInstance().guessInputFormatConverter(inputData);
            System.out.printf("Book data is in %s format.", inputFormatConverter.getType());
            System.out.println();
            outputFormatConverter = ConverterManager.getInstance()
                    .getOutputFormatConverter(inputFormatConverter.getBookDataProperties(), this.outputFormat);
            if (outputFormatConverter != null) {
                System.out.printf("Converting to %s format.", outputFormatConverter.getType());
                System.out.println();
                System.out.println("Here is the output... ");
                System.out.println("++++");
                outputFormatConverter.convert(System.out);
                System.out.println("­­­­");
            } else {
                System.out.println("Invalid output format.");
            }
        } catch (IOException | NullPointerException ex) {
//            Logger.getLogger(ConversionProcessor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Invalid input file");
        }
    }

    private byte[] readDataFromInputFile(String inputFileName) throws IOException, NullPointerException {
        Path path = Paths.get(inputFileName);
        byte[] data = Files.readAllBytes(path);
        return data;
    }
}
