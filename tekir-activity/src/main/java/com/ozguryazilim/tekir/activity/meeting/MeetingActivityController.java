package com.ozguryazilim.tekir.activity.meeting;

import com.ozguryazilim.tekir.activity.AbstractActivityController;
import com.ozguryazilim.tekir.activity.ActivityController;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.MeetingActivity;
import com.ozguryazilim.telve.data.RepositoryBase;

/**
 *
 * @author oyas
 */
@ActivityController(activity = MeetingActivity.class, editor=ActivityPages.Meeting.MeetingActivityEditor.class, viewer = ActivityPages.Meeting.MeetingActivityFragment.class)
public class MeetingActivityController extends AbstractActivityController<MeetingActivity>{

    @Override
    protected RepositoryBase<MeetingActivity, MeetingActivity> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected MeetingActivity createNewEntity() {
        return new MeetingActivity();
    }
    
}
