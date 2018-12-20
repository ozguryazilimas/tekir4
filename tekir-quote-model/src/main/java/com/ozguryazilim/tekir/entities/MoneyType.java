package com.ozguryazilim.tekir.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.money.MonetaryAmount;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.javamoney.moneta.Money;

/**
 *
 * @author oyas
 */
public class MoneyType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
            BigDecimalType.INSTANCE.sqlType(),
            StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
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
        return  o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object o) throws HibernateException, SQLException {
        assert names.length == 2;
        BigDecimal amount = BigDecimalType.INSTANCE.nullSafeGet(rs, names[0], si);
        String currency = StringType.INSTANCE.nullSafeGet(rs, names[1], si);
        return amount == null && currency == null
                ? null
                : Money.of(amount, currency);
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int i, SessionImplementor si) throws HibernateException, SQLException {
        if( o == null ){
            BigDecimalType.INSTANCE.nullSafeSet(ps, null, i, si);
            StringType.INSTANCE.nullSafeSet(ps, null, i + 1, si);
        } else {
            MonetaryAmount q = (MonetaryAmount)o;
            BigDecimalType.INSTANCE.nullSafeSet(ps, new BigDecimal(q.getNumber().toString()), i, si);
            StringType.INSTANCE.nullSafeSet(ps, q.getCurrency().getCurrencyCode(), i + 1, si);
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
