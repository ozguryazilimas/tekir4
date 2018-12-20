package com.ozguryazilim.tekir.activity.phone;

import com.ozguryazilim.tekir.activity.ActivityCalendarSource;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.PhoneActivity;
import com.ozguryazilim.telve.calendar.annotations.CalendarEventSource;

/**
 *
 * @author oyas
 */
@CalendarEventSource(hasDialog = false, creatable = false, permission = "")
public class PhoneCalendarSource extends ActivityCalendarSource{

    @Override
    protected Class<? extends Activity> getActivityClass() {
        return PhoneActivity.class;
    }
    
}
