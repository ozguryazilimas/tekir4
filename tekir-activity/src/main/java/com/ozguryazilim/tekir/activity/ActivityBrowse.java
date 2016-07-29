/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Activity_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.TextColumn;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
@Browse(browsePage = ActivityPages.ActivityBrowse.class, editPage = ViewConfig.class, viewContainerPage = ViewConfig.class)
public class ActivityBrowse extends BrowseBase<Activity, Activity> {

    @Inject
    private ActivityRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<Activity, Activity> queryDefinition) {
        //queryDefinition
        //        .addFilter(new StringFilter<>(Contact_.code, "general.label.Code"))
        //        .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"));

        queryDefinition
                .addColumn(new TextColumn<>(Activity_.subject, "activity.label.Subject"), true)
                .addColumn(new TextColumn<>(Activity_.body, "activity.label.Body"), true);
                //.addColumn(new SubTextColumn<>(Contact_.primaryMobile, ContactPhone_.address, "contact.label.PrimaryMobile"), true)
                //.addColumn(new SubTextColumn<>(Contact_.primaryPhone, ContactPhone_.address, "contact.label.PrimaryPhone"), true)
                //.addColumn(new SubTextColumn<>(Contact_.primaryEmail, ContactEMail_.address, "contact.label.PrimaryEmail"), true)
                //.addColumn(new TextColumn<>(Contact_.info, "general.label.Info"), true);
    }

    @Override
    protected RepositoryBase<Activity, Activity> getRepository() {
        return repository;
    }

}
