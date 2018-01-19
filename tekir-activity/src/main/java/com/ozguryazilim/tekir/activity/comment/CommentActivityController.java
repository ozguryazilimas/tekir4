/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.comment;

import com.ozguryazilim.tekir.activity.AbstractActivityController;
import com.ozguryazilim.tekir.activity.ActivityController;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.ActivityDirection;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.CommentActivity;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.Date;

/**
 *
 * @author oyas
 */
@ActivityController(activity = CommentActivity.class, editor=ActivityPages.Comment.CommentActivityEditor.class, viewer = ActivityPages.Comment.CommentActivityFragment.class)
public class CommentActivityController extends AbstractActivityController<CommentActivity>{
    
    @Override
    protected RepositoryBase<CommentActivity, CommentActivity> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CommentActivity createNewEntity() {
        CommentActivity result = new CommentActivity();
        result.setDirection(ActivityDirection.NONE);
        result.setStatus(ActivityStatus.DRAFT);
        result.setDueDate(new Date());
        return result;
    }
}
