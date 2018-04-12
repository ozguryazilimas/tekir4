package com.ozguryazilim.tekir.activity.email;

import com.ozguryazilim.tekir.activity.AbstractActivityController;
import com.ozguryazilim.tekir.activity.ActivityController;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.EMailActivity;
import com.ozguryazilim.telve.data.RepositoryBase;

/**
 * EPosta activite contol sınıfı
 * 
 * @author Hakan Uygun
 */
@ActivityController(activity = EMailActivity.class, editor=ActivityPages.EMail.EMailActivityEditor.class, viewer = ActivityPages.EMail.EMailActivityFragment.class)
public class EMailActivityController extends AbstractActivityController<EMailActivity>{

    @Override
    protected RepositoryBase<EMailActivity, EMailActivity> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EMailActivity createNewEntity() {
        return new EMailActivity();
    }
    
}
