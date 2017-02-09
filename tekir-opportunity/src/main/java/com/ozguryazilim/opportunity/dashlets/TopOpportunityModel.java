/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.opportunity.dashlets;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Account Txn üserinde summary almak için model.
 * 
 * @author Hakan Uygun
 */
public class TopOpportunityModel implements Serializable{

	private Long id;
	private String topic;
	private Long contactId;
	private String contactName;
	private Date date;
	private BigDecimal budget;
	private Currency currency;
	private BigDecimal localBudget;
	private Integer probability;    

	public TopOpportunityModel(Long id, String topic, Long contactId, String contactName, Date date, BigDecimal budget, Currency currency, BigDecimal localBudget, Integer probability) {
		this.id = id;
		this.topic = topic;
		this.contactId = contactId;
		this.contactName = contactName;
		this.date = date;
		this.budget = budget;
		this.currency = currency;
		this.localBudget = localBudget;
		this.probability = probability;
		this.currency = currency; 
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public Long getContactId() {
		return contactId;
	}


	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public BigDecimal getBudget() {
		return budget;
	}


	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}


	public BigDecimal getLocalBudget() {
		return localBudget;
	}


	public void setLocalBudget(BigDecimal localBudget) {
		this.localBudget = localBudget;
	}


	public Integer getProbability() {
		return probability;
	}


	public void setProbability(Integer probability) {
		this.probability = probability;
	}



}
