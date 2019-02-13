/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdataconverter.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pervez
 */
public class ConverterManager {

    private static ConverterManager converterManager = null;
    private final ConcurrentMap<String, Converter> supportedFormatConverters = new ConcurrentHashMap<>();

    private ConverterManager() {

    }

    public static ConverterManager getInstance() {
        if (converterManager == null) {
            converterManager = new ConverterManager();
            converterManager.loadSupportedFormats();
        }
        return converterManager;
    }

    public boolean loadSupportedFormats() {
        try {
            Properties formats = new Properties();
            InputStream is = ConverterManager.class.getResourceAsStream("supported_format.properties");
            formats.load(is);
            is.close();
            Set<String> formatSet = formats.stringPropertyNames();

            for (String key : getSupportedFormatConverters().keySet()) {
                if (!formatSet.contains(key)) {
                    getSupportedFormatConverters().remove(key);
                }
            }

            for (String key : formatSet) {
                String value = formats.getProperty(key);
                Class<?> converterClass = Class.forName(value);
                Constructor<?> cons = converterClass.getConstructor();
                Converter format = (Converter) cons.newInstance();
                format.setType(key.toUpperCase());
                getSupportedFormatConverters().put(key.toUpperCase(), format);
            }

            return true;
        } catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException | IOException e) {
            Logger.getLogger(ConverterManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return false;
    }

    public Converter guessInputFormatConverter(byte[] inputData) throws IOException {
        Converter converter = null;
        if (inputData != null) {
            for (Map.Entry<String, Converter> entry : this.getSupportedFormatConverters().entrySet()) {
                InputStream inputByteStream = new ByteArrayInputStream(inputData);
                if (entry.getValue().load(inputByteStream)) {
                    converter = entry.getValue();
                    break;
                }
                inputByteStream.close();
            }
        }
        return converter;
    }

    public Converter getOutputFormatConverter(Properties bookDataProperties, String format) {
        Converter converter = null;
        if (format != null) {
            converter = this.getSupportedFormatConverters().get(format.toUpperCase());
            if (converter != null) {
                converter.setBookDataProperties(bookDataProperties);
            }
        }
        return converter;
    }

    /**
     * @return the supportedFormats
     */
    private ConcurrentMap<String, Converter> getSupportedFormatConverters() {
        return supportedFormatConverters;
    }
}
