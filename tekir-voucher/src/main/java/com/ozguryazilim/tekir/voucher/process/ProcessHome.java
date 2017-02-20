/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

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


	@Override
	protected RepositoryBase<Process, ?> getRepository() {
		return repository;
	}

	public boolean onAfterLoad() {

		setTxnList(null);
		setDiagramModel(null);
		return super.onAfterLoad();
	}

	protected void refreshTxn(){    	
		txnList = accountTxnRepository.findByProcessId(getEntity().getProcessNo());
		txnList.sort((AccountTxn t, AccountTxn t1) -> {
			return t.getDate().compareTo(t1.getDate());
		});

		buildDiagramModel();
	}

	private void buildDiagramModel(){
		diagramModel = new DefaultDiagramModel();
		((DefaultDiagramModel) diagramModel).setMaxConnections(-1);		

		if(!txnList.isEmpty()){
			for(int i = 0; i < txnList.size();i++){
				String xPoint = Integer.toString(i*(20))+"em";
				String yPoint = "0em";
				String label = txnList.get(i).getFeature().getFeature().replace("Feature", "");
				Element e = new Element(label,xPoint,yPoint);				
				e.setDraggable(false);
				
				EndPoint currentStartPoint = new BlankEndPoint(EndPointAnchor.LEFT); 
				e.addEndPoint(currentStartPoint);					
				
				EndPoint currentEndPoint = new BlankEndPoint(EndPointAnchor.RIGHT);				
				e.addEndPoint(currentEndPoint);		

				diagramModel.addElement(e);

				if(i != 0){
					Element prevElement = diagramModel.getElements().get(i-1);
					EndPoint prevEndPoint = prevElement.getEndPoints().get(1);
					currentStartPoint = e.getEndPoints().get(0);
					Connection con = new Connection(prevEndPoint,currentStartPoint);
					con.setDetachable(false);
					diagramModel.connect(con);
				}				
			}
		}
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
