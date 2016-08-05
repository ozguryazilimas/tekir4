package com.ozguryazilim.tekir.core.unitset;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;

/**
 * View Model Class
 * 
 * @author
 */
public class UnitSetDefinitionViewModel implements ViewModel, Serializable {

	private Long id;
	private String code;
	private String name;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UnitSetDefinitionViewModel)) {
			return false;
		}
		UnitSetDefinitionViewModel other = (UnitSetDefinitionViewModel) obj;
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