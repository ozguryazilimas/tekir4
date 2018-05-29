package com.ozguryazilim.tekir.entites.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


/**
 * Gelen String listesini virgüllerle ayırıp "|" karakteri ile sararak tek bir String olarak yazılmasını sağlar.
 * StringListConverter'dan farkı değerleri "|" ile sarmasıdır. Böyle yapmasının sebebi alnı karakter dizilerini
 * içeren etiketler için sql like ile arama yapma gerekliliğidir.
 *
 * @author Hüseyin ATEŞ
 */
@Converter
public class TagListConverter implements AttributeConverter<List<String>, String>{
    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.stream().map(s -> "|" + s + "|")
                .collect(Collectors.joining(","));
    }

    @Override
    public List<String> convertToEntityAttribute(String field) {
        String f = field == null ? "" : field.trim();
        if (f.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(f.split(","))
                .map(s -> s.length() > 2 ? s.substring(1, s.length() - 1) : "")
                .filter(s -> !"".equals(s))
                .collect(Collectors.toList());
    }
}
