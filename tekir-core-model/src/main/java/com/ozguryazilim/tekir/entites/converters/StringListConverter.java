/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entites.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Gelen String listesini virgüllerle ayırarak tek bir String olarak yazılmasını sağlar.
 * 
 * JPA 2.1 / Java 8 bağımlılıkları
 * 
 * @author Hakan Uygun
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
