/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookdataconverter.converter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 *
 * @author pervez
 */
public abstract class Converter {
    
    private Properties bookDataProperties = new Properties();
    private String type = "";
    
    public abstract boolean load(InputStream is);
    public abstract void convert(PrintStream ps);

    /**
     * @return the bookDataProperties
     */
    public Properties getBookDataProperties() {
        return bookDataProperties;
    }

    /**
     * @param bookDataProperties the bookDataProperties to set
     */
    public void setBookDataProperties(Properties bookDataProperties) {
        this.bookDataProperties = bookDataProperties;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
