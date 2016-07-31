/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.ViewModel;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * VoucherBase'i miras alan modeller için repository taımları
 * 
 * @author Hakan Uygun
 */
public abstract class VoucherRepositoryBase<E extends VoucherBase, R extends ViewModel> extends RepositoryBase<E, R> implements CriteriaSupport<E>{
    
}
