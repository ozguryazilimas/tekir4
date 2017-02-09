package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.entities.CommodityCategory;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.entities.UnitSetDefinition;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

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
	private CommodityCategory category;
	private UnitSetDefinition unitSet;
	private String defaultUnit;
	private BigDecimal price;
	private TaxDefinition tax1;
	private TaxDefinition tax2;
	private TaxDefinition tax3;
	private Currency defaultCurrency;


	public CommodityViewModel(Long id, String code, String name, String info, Boolean active, Long categoryId, String categoryName, 
			Long unitSetId, String unitSetName, String defaultUnit, BigDecimal price, Currency defaultCurrency,
			Long tax1Id, String tax1Name, Long tax2Id, String tax2Name, Long tax3Id, String tax3Name){
		this.id = id;
		this.code = code;
		this.name = name;
		this.info = info;
		this.active = active;
		
		this.category = new CommodityCategory();
		category.setId(categoryId);
		category.setName(categoryName);
		
		this.price = price;
		
		this.unitSet = new UnitSetDefinition();
		unitSet.setId(unitSetId);
		unitSet.setName(unitSetName);
		
		this.defaultUnit = defaultUnit;
		
		this.tax1 = new TaxDefinition();
		tax1.setId(tax1Id);
		tax1.setName(tax1Name);
		
		this.tax2 = new TaxDefinition();
		tax2.setId(tax2Id);
		tax2.setName(tax2Name);
		
		this.tax3 = new TaxDefinition();
		tax3.setId(tax3Id);
		tax3.setName(tax3Name);
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



	public CommodityCategory getCategory() {
		return category;
	}



	public void setCategory(CommodityCategory category) {
		this.category = category;
	}



	public UnitSetDefinition getUnitSet() {
		return unitSet;
	}



	public void setUnitSet(UnitSetDefinition unitSet) {
		this.unitSet = unitSet;
	}



	public String getDefaultUnit() {
		return defaultUnit;
	}



	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public TaxDefinition getTax1() {
		return tax1;
	}

	public void setTax1(TaxDefinition tax1) {
		this.tax1 = tax1;
	}

	public TaxDefinition getTax2() {
		return tax2;
	}

	public void setTax2(TaxDefinition tax2) {
		this.tax2 = tax2;
	}

	public TaxDefinition getTax3() {
		return tax3;
	}

	public void setTax3(TaxDefinition tax3) {
		this.tax3 = tax3;
	}

	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

}
