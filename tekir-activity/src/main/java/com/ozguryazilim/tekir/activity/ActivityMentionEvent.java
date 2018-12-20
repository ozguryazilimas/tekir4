package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity kaydedilmeden hemen önce ek mention yapmak isteyenler için bu CDI eventini fırlatır.
 * 
 * @see ProcessService
 * 
 * @author Hakan Uygun
 */
public class ActivityMentionEvent {
    
    private Activity activity;
    private List<FeaturePointer> mentionList = new ArrayList<>();

    public ActivityMentionEvent(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
    
    /**
     * Activity mentionlarına yeni bir feature ekler.
     * 
     * @param featurePointer 
     */
    public void addMention( FeaturePointer featurePointer ){
        mentionList.add(featurePointer);
    }

    public List<FeaturePointer> getMentionList() {
        return mentionList;
    }
    
    
}
