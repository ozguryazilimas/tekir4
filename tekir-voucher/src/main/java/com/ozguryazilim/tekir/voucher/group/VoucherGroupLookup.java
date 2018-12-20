package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Lookup(dialogPage = VoucherPages.Group.VoucherGroupLookup.class)
public class VoucherGroupLookup extends LookupTableControllerBase<VoucherGroup, VoucherGroup> {
	
    @Inject
    private Identity identity;
    
    @Inject
    private VoucherGroupRepository repository;
    
    @Override
    protected void buildModel(LookupTableModel<VoucherGroup> model) {
    	model.addColumn("topic", "general.label.Topic");
        model.addColumn("groupNo", "voucher.label.Group");
        model.addColumn("info", "general.label.Info");
    }

    @Override
    protected RepositoryBase<VoucherGroup, VoucherGroup> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return VoucherGroup_.groupNo.getName();
    }
    
}
