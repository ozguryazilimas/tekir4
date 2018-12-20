package com.ozguryazilim.tekir.entites.converters;

import com.ozguryazilim.tekir.entities.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Gender enum değerini JPA 2.1 için convert eder
 * 
 * Gender gördüğü her yerde otomatik devreye girmeli.
 * 
 * @author Hakan Uygun
 */
@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String>{

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.name().substring(0, 1);
    }

    @Override
    public Gender convertToEntityAttribute(String field) {
        switch( field ){
            case "U" : return Gender.UNKNOWN;
            case "F" : return Gender.FEMALE;
            case "M" : return Gender.MALE;
            default: return Gender.UNKNOWN;
        }
    }
    
}
