/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.phone;

import com.ozguryazilim.tekir.activity.AbstractActivityController;
import com.ozguryazilim.tekir.entities.PhoneActivity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.tekir.activity.ActivityController;
import com.ozguryazilim.tekir.activity.config.ActivityPages;

/**
 *
 * @author oyas
 */
@ActivityController(activity = PhoneActivity.class, editor=ActivityPages.Phone.PhoneActivityEditor.class, viewer = ActivityPages.Phone.PhoneActivityFragment.class)
public class PhoneActivityController extends AbstractActivityController<PhoneActivity>{

    @Override
    protected RepositoryBase<PhoneActivity, PhoneActivity> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PhoneActivity createNewEntity() {
        return new PhoneActivity();
    }
    
}
