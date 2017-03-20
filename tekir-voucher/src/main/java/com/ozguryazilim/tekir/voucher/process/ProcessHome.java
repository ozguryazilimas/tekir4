/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

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

/**
 * Process View Conttroller
 * @author oyas
 */
@FormEdit(feature=ProcessFeature.class)
public class ProcessHome extends FormBase<Process, Long>{

	@Inject
	private AccountTxnRepository accountTxnRepository;

	@Inject
	private ProcessRepository repository;

	@Inject
	private ProcessService processService;

	@Inject
	private FeatureLinkController featureController;

	private List<AccountTxn> txnList;
	private DiagramModel diagramModel;
	private List<String> milestoneList;

	@Override
	protected RepositoryBase<Process, ?> getRepository() {
		return repository;
	}

	public boolean onAfterLoad() {

		setTxnList(null);
		setDiagramModel(null);
		refreshTxn();
		return super.onAfterLoad();
	}

	protected void refreshTxn(){    	
		txnList = accountTxnRepository.findByProcessId(getEntity().getProcessNo());
		txnList.sort((AccountTxn t, AccountTxn t1) -> {
			return t.getDate().compareTo(t1.getDate());
		});

		initDiagramModel();
		connectDiagramModel();
	}

	private void initDiagramModel(){
		diagramModel = new DefaultDiagramModel();
		((DefaultDiagramModel) diagramModel).setMaxConnections(-1);	

		milestoneList = new ArrayList<>();
		if(getEntity().getType().toString().equals("SALES")){
			milestoneList = Arrays.asList("Opportunity","Quote","Sales Order","Sales Invoice","Received Payment");
		}
		else if(getEntity().getType().toString().equals("PURCHASE")){
			milestoneList = Arrays.asList("Purchase Order","Purchase Invoice","Payment");
		}

		for(int i = 0; i < milestoneList.size();i++){
			String xPoint = Integer.toString(i*(15))+"em";
			String yPoint = "0em";
			String label = milestoneList.get(i);
			Element e = new Element(label,xPoint,yPoint);				
			e.setDraggable(false);
			e.setStyleClass("ui-diagram-unchecked");
			EndPoint currentLeftPoint = new BlankEndPoint(EndPointAnchor.LEFT); 
			e.addEndPoint(currentLeftPoint);					

			EndPoint currentRightPoint = new BlankEndPoint(EndPointAnchor.RIGHT);				
			e.addEndPoint(currentRightPoint);		

			diagramModel.addElement(e);
		}
	}

	private void connectDiagramModel(){		
		if(!txnList.isEmpty()){
			EndPoint prevRightPoint = null;
			for(int i = 0; i < milestoneList.size();i++){
				String currentClassName = milestoneList.get(i).replaceAll("\\s+","").concat("Feature");
				boolean milestoneExists = txnList.stream().anyMatch(item -> currentClassName.equals(item.getFeature().getFeature()));

				if(milestoneExists){
					Element currentElement = diagramModel.getElements().get(i);
					currentElement.setStyleClass("ui-diagram-checked");			
					EndPoint currentLeftPoint = currentElement.getEndPoints().get(0);

					if(prevRightPoint != null){
						Connection con = new Connection(prevRightPoint,currentLeftPoint);
						con.setDetachable(false);
						diagramModel.connect(con);
					}
					prevRightPoint = currentElement.getEndPoints().get(1);
				}
			}
		}
	}
	
	public boolean showDelete(){
		return txnList.isEmpty();
	}

	public FeaturePointer getFeaturePointer() {
		FeaturePointer result = new FeaturePointer();
		result.setBusinessKey(getEntity().getTopic());
		result.setFeature(getFeatureClass().getSimpleName());
		result.setPrimaryKey(getEntity().getId());
		return result;
	}


	public List<AccountTxn> getTxnList() {
		if(txnList == null){
			refreshTxn();
		}
		return txnList;
	}

	public void setTxnList(List<AccountTxn> txnList) {
		this.txnList = txnList;
	}

	public DiagramModel getDiagramModel() {
		if(diagramModel == null){
			refreshTxn();
		}
		return diagramModel;
	}

	public void setDiagramModel(DiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}

}
