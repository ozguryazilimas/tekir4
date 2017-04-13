/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.meeting;

import com.ozguryazilim.tekir.activity.ActivityCalendarSource;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.MeetingActivity;
import com.ozguryazilim.telve.calendar.annotations.CalendarEventSource;

/**
 *
 * @author oyas
 */
@CalendarEventSource(hasDialog = false, creatable = false, permission = "")
public class MeetingCalendarSource extends ActivityCalendarSource{

    @Override
    protected Class<? extends Activity> getActivityClass() {
        return MeetingActivity.class;
    }
    
}
