/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdataconverter.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;

/**
 *
 * @author pervez
 */
public class PlainTextConverter extends Converter {

    @Override
    public boolean load(InputStream is) {
        this.getBookDataProperties().clear();
        try {
            this.getBookDataProperties().load(is);
            return true;
        } catch (IOException | NullPointerException ex) {
//            Logger.getLogger(PlainTextConverter.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public void convert(PrintStream ps) {
        if (!this.getBookDataProperties().isEmpty() && ps != null) {
            Enumeration<?> enumeration = this.getBookDataProperties().propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = this.getBookDataProperties().getProperty(key);
                ps.println(key + ": " + value);
            }
        }
    }
}
