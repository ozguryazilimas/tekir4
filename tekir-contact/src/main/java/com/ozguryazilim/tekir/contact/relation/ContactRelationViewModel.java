package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;

/**
 * View Model Class
 *
 * @author
 */
public class ContactRelationViewModel implements ViewModel, Serializable {

    private Long id;
    private String code;
    private String name;
    private Boolean revers = Boolean.FALSE;
    private Integer weigth;
    private String targetRoles; //target contact filtresi
            

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Boolean getRevers() {
        return revers;
    }

    public void setRevers(Boolean revers) {
        this.revers = revers;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public String getTargetRoles() {
        return targetRoles;
    }

    public void setTargetRoles(String targetRoles) {
        this.targetRoles = targetRoles;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContactRelationViewModel)) {
            return false;
        }
        ContactRelationViewModel other = (ContactRelationViewModel) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
