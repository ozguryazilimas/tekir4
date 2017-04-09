/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.task;

import com.ozguryazilim.tekir.activity.AbstractActivityController;
import com.ozguryazilim.tekir.activity.ActivityController;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.ActivityDirection;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.TaskActivity;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.Date;

/**
 *
 * @author oyas
*/
@ActivityController(activity = TaskActivity.class, editor=ActivityPages.Task.TaskActivityEditor.class, viewer = ActivityPages.Task.TaskActivityFragment.class)
public class TaskActivityController extends AbstractActivityController<TaskActivity>{

    @Override
    protected RepositoryBase<TaskActivity, TaskActivity> getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TaskActivity createNewEntity() {
        TaskActivity result = new TaskActivity();
        result.setDirection(ActivityDirection.NONE);
        result.setStatus(ActivityStatus.DRAFT);
        result.setDueDate(new Date());
        return result;
    }
    
}
