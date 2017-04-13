/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.task;

import com.ozguryazilim.tekir.activity.ActivityCalendarSource;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.TaskActivity;
import com.ozguryazilim.telve.calendar.annotations.CalendarEventSource;

/**
 *
 * @author oyas
 */
@CalendarEventSource(hasDialog = false, creatable = false, permission = "")
public class TaskCalendarSource extends ActivityCalendarSource{

    @Override
    protected Class<? extends Activity> getActivityClass() {
        return TaskActivity.class;
    }
    
}
