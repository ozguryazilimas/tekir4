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
                .addFilter(new StringFilter<>(Feed_.type, "general.label.type"))
                .addFilter(new StringFilter<>(Feed_.feeder, "general.label.feed.feeder"))
                .addFilter(new StringFilter<>(Feed_.user, "general.label.feed.user"))  
                .addFilter(new DateFilter<>(Feed_.date, "general.label.feed.date",FilterOperand.All, DateValueType.LastMonth))
                .addFilter(new StringFilter<>(Feed_.subject, "general.label.feed.subject"))
                .addFilter(new StringFilter<>(Feed_.body, "general.label.feed.body"));
                
        queryDefinition
                .addColumn(new TextColumn<>(Feed_.type, "general.label.feed.type"), true)
                .addColumn(new TextColumn<>(Feed_.feeder, "general.label.feed.feeder"), true)
                .addColumn(new TextColumn<>(Feed_.user, "general.label.feed.user"), true)
                .addColumn(new DateColumn<>(Feed_.date, "general.label.feed.date"), true)
                .addColumn(new TextColumn<>(Feed_.subject, "general.label.feed.subject"), true)
                .addColumn(new FormattedMessageColumn<>(Feed_.body, "general.label.feed.body"), true);
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
