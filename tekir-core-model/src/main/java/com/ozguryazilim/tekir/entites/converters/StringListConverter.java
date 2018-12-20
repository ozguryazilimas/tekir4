package com.ozguryazilim.tekir.entites.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Gelen String listesini virgüllerle ayırarak tek bir String olarak yazılmasını sağlar.
 *
 * Bunun kullanılması dönüştürülen alan üzerinde CONTAINS yapıldığında
 * https://hibernate.atlassian.net/browse/HHH-9991 sorununa sebep olmaktadır.
 *
 * JPA 2.1 / Java 8 bağımlılıkları
 * 
 * @author Hakan Uygun
 * @see https://hibernate.atlassian.net/browse/HHH-9991
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String>{

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return String.join(",", list);
    }
    
    @Override
    public List<String> convertToEntityAttribute(String field) {
        String f = field == null ? "" : field.trim();
        if( f.isEmpty() ){
            return new ArrayList<>();
        } 
        
        return new ArrayList<>(Arrays.asList(f.split(",")));
    }
}
