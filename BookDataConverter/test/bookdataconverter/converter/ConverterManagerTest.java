/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookdataconverter.converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author pervez
 */
public class ConverterManagerTest {
    
    public ConverterManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ConverterManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("testGetInstance()");
        ConverterManager result = ConverterManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of loadSupportedFormats method, of class ConverterManager.
     */
    @Test
    public void testLoadSupportedFormats() {
        System.out.println("testLoadSupportedFormats()");
        ConverterManager instance = ConverterManager.getInstance();
        boolean result = instance.loadSupportedFormats();
        assertTrue(result);
    }

    private byte[] readDataFromInputFile(String inputFileName) throws IOException, NullPointerException {
        Path path = Paths.get(inputFileName);
        byte[] data = Files.readAllBytes(path);
        return data;
    }
    
    /**
     * Test of guessInputFormatConverter method, of class ConverterManager.
     */
    @Test
    public void testGuessInputFormatConverter() throws Exception {
        System.out.println("testGuessInputFormatConverter()");
        byte[] inputData = readDataFromInputFile("C:\\Users\\pervez\\Google Drive\\software industry\\escenic"
                + "\\bookdataconverterrepo\\sample_input_files\\input.xml");
        Converter result = ConverterManager.getInstance().guessInputFormatConverter(inputData);
        assertNotNull(result);
    }
    
    @Test
    public void testGuessInputFormatConverterWithNullInput() throws Exception {
        System.out.println("testGuessInputFormatConverterWithNullInput()");
        Converter result = ConverterManager.getInstance().guessInputFormatConverter(null);
        assertNull(result);
    }

    /**
     * Test of getOutputFormatConverter method, of class ConverterManager.
     */
    @Test
    public void testGetOutputFormatConverter() {
        System.out.println("testGetOutputFormatConverter()");
        
        Properties bookDataProperties = new Properties();
        bookDataProperties.put("name", "test book");
        bookDataProperties.put("type", "technical");
        bookDataProperties.put("author", "test author");
        
        Converter result = ConverterManager.getInstance().getOutputFormatConverter(bookDataProperties, "xml");
        assertNotNull(result);
        result = ConverterManager.getInstance().getOutputFormatConverter(bookDataProperties, "text");
        assertNotNull(result);
    }
    
    @Test
    public void testGetOutputFormatConverterWithNullorUnavailableFormat() {
        System.out.println("testGetOutputFormatConverterWithNullorUnavailableFormat()");
        
        Properties bookDataProperties = new Properties();
        bookDataProperties.put("name", "test book");
        bookDataProperties.put("type", "technical");
        bookDataProperties.put("author", "test author");
        
        Converter result = ConverterManager.getInstance().getOutputFormatConverter(bookDataProperties, null);
        assertNull(result);
        result = ConverterManager.getInstance().getOutputFormatConverter(bookDataProperties, "txt");
        assertNull(result);
    }
    
    @Test
    public void testGetOutputFormatConverterWithNullorEmptyData() {
        System.out.println("testGetOutputFormatConverterWithNullorEmptyData()");
        Properties bookDataProperties = new Properties();
        Converter result = ConverterManager.getInstance().getOutputFormatConverter(null, "text");
        assertNotNull(result);
        result = ConverterManager.getInstance().getOutputFormatConverter(bookDataProperties, "text");
        assertNotNull(result);
    }
}
