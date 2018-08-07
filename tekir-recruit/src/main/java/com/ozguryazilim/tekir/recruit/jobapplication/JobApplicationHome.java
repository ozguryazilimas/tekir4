/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author yusuf
 */
@FormEdit( feature = JobApplicationFeature.class )
public class JobApplicationHome extends FormBase<JobApplication, Long> {
    
    @Inject
    private JobApplicationRepository repository;

    @Override
    protected RepositoryBase<JobApplication, JobApplicationViewModel> getRepository() {
        return repository;
    }
      
}
