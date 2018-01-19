/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.telve.quick.QuickRecord;
import com.ozguryazilim.telve.quick.QuickRecordBase;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@QuickRecord(page = ActivityPages.ActivityQuickPanel.class, showonMenu = false)
public class ActivityQuickRecord extends QuickRecordBase{

    @Inject
    private ActivityHome activityHome;
    
    
    public void closeSuccess(){
        activityHome.closeSuccess();
        activityHome.close();
        save();
    }
    
    public void closeFaild(){
        activityHome.closeFaild();
        activityHome.close();
        save();
    }
    
    public void closePlanned(){
        activityHome.save();
        activityHome.close();
        save();
    }
    
    @Override
    protected boolean doSave() {
        return true;
    }
    
}
