package com.ozguryazilim.tekir.core.query.columns;

import com.ozguryazilim.telve.query.columns.Column;

import javax.persistence.metamodel.Attribute;

/**
 * List<String> tipindeki alanların etiket şeklinde gösterilmesini sağlar.
 * Bir hibernate sorunu ve telve tasarımından örütü getAttribute ve setAttribute metodlarını desteklememektedir.
 *
 * @param <E> Entity sınıf adı
 * @author Hüseyin ATEŞ
 */
public class TagColumn<E> extends Column<E>{

    private String attributeName;

    public TagColumn(String attributeName, String labelKey) {
        super(null, labelKey);
        this.attributeName = attributeName;
    }


    @Deprecated
    @Override
    public Attribute<? super E, ?> getAttribute() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public void setAttribute(Attribute<? super E, ?> attribute) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTemplate() {
        return "tagcolumn";
    }

    @Override
    public String getName() {
        return this.attributeName;
    }
}
