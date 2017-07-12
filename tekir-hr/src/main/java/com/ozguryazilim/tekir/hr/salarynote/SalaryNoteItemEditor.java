/**
 * 
 */
package com.ozguryazilim.tekir.hr.salarynote;

import java.io.Serializable;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.SalaryNoteItem;

/**
 * @author oktay
 *
 */
@SessionScoped
@Named
public class SalaryNoteItemEditor implements Serializable {
	private SalaryNoteItem item;
	private SalaryNoteItemEditorListener listener;
	
	public void openDialog( SalaryNoteItem item, SalaryNoteItemEditorListener listener ){
	        
	        this.item = item;
	        this.listener = listener;
	        
	        Map<String, Object> options = new HashMap<>();
	        
	        decorateDialog(options);
	        
	        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
	}
	
	public void closeDialog() {
	        //Aslında burada kendini çağıran VoucherHome instance'a bir mesaj fırlatmak lazım.
	        //Ya da en azından callback yapmak lazım Bunun için de buraya parametre olarak bean ismi ya da belli bir interface'i implemente eden biş ialabiliriz sanki.
	        listener.saveItem(item);
	        RequestContext.getCurrentInstance().closeDialog(null);
	}

    /**
     * Dialogu hiç bir şey seçmeden kapatır.
     */
	public void cancelDialog() {
	        RequestContext.getCurrentInstance().closeDialog(null);
	}
	
    protected void decorateDialog(Map<String, Object> options){
        options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("contentHeight", 450);
    }
    
    protected String getDialogName() {
        return "/employee/salaryNoteItemEditor";
    }

	public SalaryNoteItem getItem() {
		return item;
	}

	public void setItem(SalaryNoteItem item) {
		this.item = item;
	}

	public SalaryNoteItemEditorListener getListener() {
		return listener;
	}

	public void setListener(SalaryNoteItemEditorListener listener) {
		this.listener = listener;
	}

    public Employee getEmployee() {
        return item.getEmployee();
    }

    public void setEmployee(Employee employee) {
        item.setEmployee(employee);
    }

}
