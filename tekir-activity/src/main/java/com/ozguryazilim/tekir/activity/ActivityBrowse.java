/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.ActivityPriority;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.Activity_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.EnumColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.EnumFilter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;

import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse(feature = ActivityFeature.class)
public class ActivityBrowse extends BrowseBase<Activity, Activity> {

    @Inject
    private ActivityRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<Activity, Activity> queryDefinition) {
    	EnumFilter statusFilter = new EnumFilter(Activity_.status, ActivityStatus.OPEN, "general.label.Status", "activity.status.");
    	statusFilter.setOperand(FilterOperand.All);
    	
    	EnumFilter priorityFilter = new EnumFilter(Activity_.priority, ActivityPriority.HIGH, "general.label.Priority", "activity.priority.");
		priorityFilter.setOperand(FilterOperand.All);
    	
        queryDefinition        		
        		.addFilter(new StringFilter<>(Activity_.subject, "activity.label.Subject"))
        		.addFilter(statusFilter)
                .addFilter(priorityFilter)
                .addFilter(new SubStringFilter<>(Activity_.person, Contact_.name, "general.label.Person"))
                .addFilter(new SubStringFilter<>(Activity_.corporation, Contact_.name, "general.label.Corporation"))
        		.addFilter(new DateFilter<>(Activity_.date, "general.label.Date", FilterOperand.All, DateValueType.LastMonth))
        		.addFilter(new DateFilter<>(Activity_.dueDate, "activity.label.DueDate", FilterOperand.All, DateValueType.NextMonth));
        
        queryDefinition
                .addColumn(new TextColumn<>(Activity_.subject, "activity.label.Subject"), true)
                .addColumn(new TextColumn<>(Activity_.body, "activity.label.Body"), true)
                .addColumn(new SubTextColumn<>(Activity_.person, Contact_.name, "general.label.Person"), true)
                .addColumn(new SubTextColumn<>(Activity_.corporation, Contact_.name, "general.label.Corporation"), false)
                .addColumn(new EnumColumn<>(Activity_.priority, "general.label.Priority", "activity.priority."),true)
                .addColumn(new EnumColumn<>(Activity_.status, "general.label.Status", "activity.status."),true)
                .addColumn(new DateColumn<>(Activity_.date, "general.label.Date"), false)
        		.addColumn(new TextColumn<>(Activity_.assignee, "activity.label.Assignee"), false)
                .addColumn(new DateColumn<>(Activity_.date, "general.label.Date"), false)
                .addColumn(new DateColumn<>(Activity_.dueDate, "activity.label.DueDate"), false);


    }

    @Override
    protected RepositoryBase<Activity, Activity> getRepository() {
        return repository;
    }

}
