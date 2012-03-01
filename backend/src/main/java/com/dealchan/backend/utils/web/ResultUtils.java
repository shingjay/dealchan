package com.dealchan.backend.utils.web;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 1/19/12
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResultUtils {

    public static String[] ESCAPE_CHARACTERS = { "\\\\\"", "\\\\\\", "\\\\/"};
    
    public static String cleanJson(String json) {
        json = json.substring(json.indexOf("\"")+1, json.lastIndexOf("\""));

        for( EscapeCharacter esc : EscapeCharacter.values() ) {
            //System.out.println(esc.getEscaped() + ":" + esc.getConverted());
            json = json.replaceAll( esc.getEscaped(), esc.getConverted() );
        }
        return json;
    }
    
    public static String urlEncoder(String toEncode) {
        return null;
    }
    
    public static <T> T convertToJackson(String json, Class klass) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getJsonFactory();
            JsonParser parser = factory.createJsonParser(json);
            return (T) parser.readValueAs(klass);
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static enum EscapeCharacter {
        
        // dont fucking change the order!
        BACKSLASH_QUOTE("\\\\\\\"","\\\""),
        DOUBLE_QUOTE("\\\\\"", "\""),
        SINGLE_QUOTE("\\\\'" ,"'"),
        BACKSLASH_FORWARDSLASH("\\\\\\\\\\\\\\/", "/"),
        BACKSLASH_FORWARDSLASH2("\\\\/", "/");

        
        private String escaped;
        private String converted;
        
        EscapeCharacter(String escaped, String converted) {
            this.escaped = escaped;
            this.converted = converted;
        }

        public String getConverted() {
            return converted;
        }

        public String getEscaped() {
            return escaped;
        }
    }

}
