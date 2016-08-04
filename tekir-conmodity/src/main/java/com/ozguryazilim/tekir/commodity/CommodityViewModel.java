package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;

/**
 * View Model Class
 *
 * @author
 */
public class CommodityViewModel implements ViewModel, Serializable {

    private Long id;
    private String code;
    private String name;
    private String info;
    private Boolean active;

    public CommodityViewModel(Long id, String code, String name, String info, Boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.info = info;
        this.active = active;
    }

    
    
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommodityViewModel)) {
            return false;
        }
        CommodityViewModel other = (CommodityViewModel) obj;
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
