/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit;


import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author deniz
 */
@FormEdit( feature = RecruitFeature.class )
public class RecruitHome extends FormBase<JobAdvert, Long> {
    
    @Inject
    private RecruitRepository repository;

    @Override
    protected RepositoryBase<JobAdvert, RecruitViewModel> getRepository() {
        return repository;
    }
      
}
