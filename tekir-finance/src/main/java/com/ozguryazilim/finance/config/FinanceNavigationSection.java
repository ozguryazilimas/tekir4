package com.ozguryazilim.finance.config;

import com.ozguryazilim.telve.nav.AbstractNavigationSection;

/**
 * Cari Dekontları için menü grubu
 * @author Hakan Uygun
 */
public class FinanceNavigationSection extends AbstractNavigationSection{

    @Override
    public Integer getOrder() {
        return 60;
    }
    
}
