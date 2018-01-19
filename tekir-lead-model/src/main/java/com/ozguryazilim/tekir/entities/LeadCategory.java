package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ozguryazilim.telve.entities.TreeNodeEntityBase;

@Entity
@Table(name = "TLE_LEAD_CATEGORY")
public class LeadCategory extends TreeNodeEntityBase<LeadCategory> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
	@Column(name = "ID")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
