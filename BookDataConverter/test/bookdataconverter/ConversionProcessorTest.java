/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookdataconverter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author pervez
 */
public class ConversionProcessorTest {
    
    private String inputFileName = "";
    private String outputFormat = "";
    
    public ConversionProcessorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        inputFileName = "C:\\Users\\pervez\\Google Drive\\software industry\\escenic\\bookdataconverterrepo\\sample_input_files\\input.txt";        
//        inputFileName = "C:\\Users\\pervez\\Google Drive\\software industry\\escenic\\bookdataconverterrepo\\sample_input_files\\input.xml";
        outputFormat = "xml";
//        outputFormat = "text";
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class ConversionProcessor.
     */
    @Test
    public void testProcess() {
        System.out.println("testProcess()");
        ConversionProcessor instance = new ConversionProcessor(inputFileName, outputFormat);
        instance.process();
    }
    
    @Test
    public void testProcessWithEmptyInputFileName() {
        System.out.println("testProcessWithEmptyInputFileName()");
        ConversionProcessor instance = new ConversionProcessor("", outputFormat);
        instance.process();
    }
    
    @Test
    public void testProcessWithNullInputFileName() {
        System.out.println("testProcessWithNullInputFileName()");
        ConversionProcessor instance = new ConversionProcessor(null, outputFormat);
        instance.process();
    }
    
    @Test
    public void testProcessWithEmptyOutputFormat() {
        System.out.println("testProcessWithEmptyOutputFormat()");
        ConversionProcessor instance = new ConversionProcessor(inputFileName, "");
        instance.process();
    }
    
    @Test
    public void testProcessWithNullOutputFormat() {
        System.out.println("testProcessWithNullOutputFormat()");
        ConversionProcessor instance = new ConversionProcessor(inputFileName, null);
        instance.process();
    }
}
