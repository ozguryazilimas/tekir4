/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.unit.QuantitativeAmount;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

/**
 *
 * @author oyas
 */
public class QuantityType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
            BigDecimalType.INSTANCE.sqlType(),
            StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return QuantitativeAmount.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner) throws HibernateException, SQLException {
        assert names.length == 2;
        BigDecimal amount = BigDecimalType.INSTANCE.nullSafeGet(rs, names[0], si);
        String unit = StringType.INSTANCE.nullSafeGet(rs, names[1], si);
        return amount == null && unit == null
                ? null
                : QuantitativeAmount.of(amount, unit);
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object value, int i, SessionImplementor si) throws HibernateException, SQLException {
        if( value == null ){
            BigDecimalType.INSTANCE.nullSafeSet(ps, null, i, si);
            StringType.INSTANCE.nullSafeSet(ps, null, i + 1, si);
        } else {
            QuantitativeAmount q = (QuantitativeAmount)value;
            BigDecimalType.INSTANCE.nullSafeSet(ps, q.getAmount(), i, si);
            StringType.INSTANCE.nullSafeSet(ps, q.getUnitName().toString(), i + 1, si);
        }
        
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable srlzbl, Object o) throws HibernateException {
        return srlzbl;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }

}
