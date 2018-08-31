/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.applicant.information;

import com.ozguryazilim.tekir.entities.ApplicantEducation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * ApplicantInformation editorler için işaretleyici
 *
 * @author yusuf
 */
@Stereotype
@SessionScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@Documented
public @interface ApplicantInformationEditor {

    Class<? extends ViewConfig> page();
}
