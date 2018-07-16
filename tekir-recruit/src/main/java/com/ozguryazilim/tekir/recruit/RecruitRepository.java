/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit;

 

import com.ozguryazilim.tekir.entities.JobAdvert;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.telve.data.RepositoryBase;

/**
 *
 * @author deniz
 */
@Repository
@Dependent
public abstract class RecruitRepository extends
        RepositoryBase<JobAdvert, RecruitViewModel>
        implements 
        CriteriaSupport<JobAdvert>{
 
    
}
