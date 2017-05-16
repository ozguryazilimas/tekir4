package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Feed;
import com.ozguryazilim.tekir.entities.Feed_;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.FormattedMessageColumn;
import com.ozguryazilim.telve.query.columns.MessageColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import javax.inject.Inject;

/**
 * Brwose Control Class
 *
 * @author
 */
@Browse(feature = FeedFeature.class)
public class FeedBrowse extends BrowseBase<Feed, Feed> {

    @Inject
    private FeedRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<Feed, Feed> queryDefinition) {
        
        queryDefinition
                .addFilter(new StringFilter<>(Feed_.type, "general.label.Type"))
                .addFilter(new StringFilter<>(Feed_.feeder, "general.label.Feeder"))
                .addFilter(new StringFilter<>(Feed_.user, "general.label.User"))  
                .addFilter(new DateFilter<>(Feed_.date, "general.label.Date",FilterOperand.All, DateValueType.LastMonth))
                .addFilter(new StringFilter<>(Feed_.subject, "general.label.Subject"))
                .addFilter(new StringFilter<>(Feed_.body, "general.label.Body"));
                
        queryDefinition
                .addColumn(new TextColumn<>(Feed_.type, "general.label.Type"), true)
                .addColumn(new TextColumn<>(Feed_.feeder, "general.label.Feeder"), true)
                .addColumn(new TextColumn<>(Feed_.user, "general.label.User"), true)
                .addColumn(new DateColumn<>(Feed_.date, "general.label.Date"), true)
                .addColumn(new TextColumn<>(Feed_.subject, "general.label.Subject"), true)
                .addColumn(new FormattedMessageColumn<>(Feed_.body, "general.label.Body"), true);
                //.addColumn(new MessageColumn<>(Feed_.body,"general.label.feed.body"),true);
    }

    @Override
    protected RepositoryBase<Feed, Feed> getRepository() {
        return repository;

    }
    
     public Feed getFeed() {
        if (getSelectedItem() != null) {
            return repository.findBy(getSelectedItem().getId());
        } else {
            return null;
        }
    }
    
}
