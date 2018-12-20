package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.calendar.AbstractCalendarEventSource;
import com.ozguryazilim.telve.calendar.CalendarEventModel;
import com.ozguryazilim.telve.calendar.CalendarFilterModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

/**
 *
 * @author oyas
 */

public abstract class ActivityCalendarSource extends AbstractCalendarEventSource{

    @Inject
    private ActivityRepository repository;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private NavigationParameterContext navigationParameterContext;
    
    @Inject
    private Identity identity;
    
    @Inject
    private CalendarFilterModel filterModel;
    
    @Override
    public void process(String eventId) {
        navigationParameterContext.addPageParameter("eid", eventId);
        viewNavigationHandler.navigateTo(ActivityPages.ActivityView.class);
        
        //FacesContext fc = FacesContext.getCurrentInstance();
        //NavigationHandler nh = fc.getApplication().getNavigationHandler();
        //nh.handleNavigation(fc, null, "/activites/activityView.xhtml" + "?faces-redirect=true&eid=" + eventId);
    }

    @Override
    public List<CalendarEventModel> getEvents(Date start, Date end) {
        
        List<CalendarEventModel> result = new ArrayList<>();

        List<Activity> ls = repository.findForCalendarSource(identity.getLoginName(), start, end, getActivityClass(), filterModel.getShowClosedEvents());
        
        for( Activity act : ls ){
            result.add(createCalendarEvent(act));
        }
        
        return result;
    }

    protected abstract Class<? extends Activity> getActivityClass();

    public CalendarEventModel createCalendarEvent(Activity act) {
        CalendarEventModel event = new CalendarEventModel();

        event.setAllDay(Boolean.FALSE);
        event.setEditable(Boolean.FALSE);

        event.setStart(act.getDueDate());
        //FIXME: Bitiş Duration üzerinden hesaplanmalı.
        event.setEnd(act.getDueDate());
        event.setId(act.getId().toString());

        event.setTitle(act.getSubject());
        event.setDescription( act.getBody());
        return event;
    }
}
