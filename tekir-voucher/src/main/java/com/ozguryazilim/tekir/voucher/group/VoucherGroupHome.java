/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureLinkController;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;

/**
 * Voucher Group View Controller
 * @author oyas
 */
@FormEdit(feature=VoucherGroupFeature.class)
public class VoucherGroupHome extends FormBase<VoucherGroup, Long>{

	@Inject
	private VoucherGroupRepository repository;

	@Inject
	private VoucherGroupService voucherGroupService;

	@Inject
	private FeatureLinkController featureController;

	@Override
	protected RepositoryBase<VoucherGroup, ?> getRepository() {
		return repository;
	}	

	public FeaturePointer getFeaturePointer() {
		FeaturePointer result = new FeaturePointer();
		result.setBusinessKey(getEntity().getTopic());
		result.setFeature(getFeatureClass().getSimpleName());
		result.setPrimaryKey(getEntity().getId());
		return result;
	}


}
