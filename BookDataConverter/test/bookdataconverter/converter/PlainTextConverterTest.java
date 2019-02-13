/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdataconverter.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pervez
 */
public class PlainTextConverterTest {

    private InputStream is = null;

    public PlainTextConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            is = new FileInputStream("C:\\Users\\pervez\\Google Drive\\software industry\\escenic\\bookdataconverterrepo\\sample_input_files\\input.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlainTextConverterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class PlainTextConverter.
     */
    @Test
    public void testLoad() {
        System.out.println("testLoad()");
        PlainTextConverter instance = new PlainTextConverter();
        boolean result = instance.load(is);
        assertTrue(result);
    }

    @Test
    public void testLoadWithNullInputStream() {
        System.out.println("testLoadWithNullInputStream()");
        PlainTextConverter instance = new PlainTextConverter();
        boolean result = instance.load(null);
        assertFalse(result);
    }

    /**
     * Test of convert method, of class PlainTextConverter.
     */
    @Test
    public void testOnlyConvert() {
        System.out.println("testOnlyConvert()");
        PrintStream ps = System.out;
        PlainTextConverter instance = new PlainTextConverter();
        instance.convert(ps);
    }

    @Test
    public void testOnlyConvertWithNullOutputStream() {
        System.out.println("testOnlyConvertWithNullOutputStream()");
        PlainTextConverter instance = new PlainTextConverter();
        instance.convert(null);
    }

    @Test
    public void testLoadThenConvert() {
        System.out.println("testLoadThenConvert()");
        PrintStream ps = System.out;
        PlainTextConverter instance = new PlainTextConverter();
        boolean result = instance.load(is);
        instance.convert(ps);
    }

    @Test
    public void testLoadThenConvertWithNullInputStream() {
        System.out.println("testLoadThenConvertWithNullInputStream()");
        PrintStream ps = System.out;
        PlainTextConverter instance = new PlainTextConverter();
        boolean result = instance.load(null);
        instance.convert(ps);
    }

    @Test
    public void testLoadThenConvertWithNullOutputStream() {
        System.out.println("testLoadThenConvertWithNullOutputStream()");
        PrintStream ps = null;
        PlainTextConverter instance = new PlainTextConverter();
        boolean result = instance.load(is);
        instance.convert(ps);
    }
}
