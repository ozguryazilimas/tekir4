/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.ViewModel;
import com.ozguryazilim.telve.forms.BrowseBase;

/**
 * Voucher Tabanlı veriler için temel Browse sınıfı
 *
 * @author Hakan Uygun
 * @param <E>
 * @param <V>
 */
public abstract class VoucherBrowseBase<E extends VoucherBase, V extends ViewModel> extends BrowseBase<E, V> {

    @Inject
    private Identity identity;

    @Override
    protected RepositoryBase<E, V> getRepository() {

        VoucherRepositoryBase<E, V> repository = getVoucherRepository();

        if (identity.isPermitted(getPermissionDomain() + ":select:*")) {
            //Her tülü yetkili zaten dolayısı ile group felan ile uğraşmayalım
        } else if (identity.isPermitted(getPermissionDomain() + ":select:$group")) {
            List<String> ls = identity.getGroupsMembers();
            if (ls.isEmpty()) {
                ls.add("NONE");
            }
            repository.setOwnerFilter(identity.getGroupsMembers());
        } else if (identity.isPermitted(getPermissionDomain() + ":select:$owner")) {
            List<String> ls = new ArrayList<>();
            ls.add(identity.getLoginName());
            repository.setOwnerFilter(ls);
        }

        return repository;
    }
    
    public abstract VoucherFormBase<E> getHome();

    public abstract VoucherRepositoryBase<E, V> getVoucherRepository();

}
